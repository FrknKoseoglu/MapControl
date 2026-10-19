package com.mapcontrol.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Streams the web panel's single-file form with bounded memory and exact bytes. */
public final class MultipartUpload {
    public static final long MAX_UPLOAD_BYTES = 1024L * 1024L * 1024L;
    private static final Pattern BOUNDARY = Pattern.compile(
            "(?:^|;)\\s*boundary\\s*=\\s*(?:\"([^\"]+)\"|([^;\\s]+))", Pattern.CASE_INSENSITIVE);
    private static final Pattern FILENAME = Pattern.compile(
            "(?:^|;)\\s*filename\\s*=\\s*(?:\"((?:\\\\.|[^\"])*)\"|([^;\\r\\n]+))", Pattern.CASE_INSENSITIVE);
    private MultipartUpload() { }

    public static File receive(InputStream input, long length, String contentType, File directory) throws IOException {
        if (length <= 0 || length > MAX_UPLOAD_BYTES) throw new IOException("Dosya yükleme sınırı 1 GB");
        String boundary = boundary(contentType);
        if (!directory.isDirectory() && !directory.mkdirs()) throw new IOException("Download klasörü oluşturulamadı");
        if (directory.getUsableSpace() < length + 16L * 1024L * 1024L) throw new IOException("Yeterli boş alan yok");
        LimitedInput limited = new LimitedInput(input, length);
        PushbackInputStream stream = new PushbackInputStream(new BufferedInputStream(limited, 65536), 2);
        if (!("--" + boundary).equals(readLine(stream))) throw new IOException("Geçersiz multipart başlangıcı");
        String name = null;
        int headerBytes = 0;
        for (String line; !(line = readLine(stream)).isEmpty();) {
            headerBytes += line.length() + 2;
            if (headerBytes > HttpRequest.MAX_HEADER_BYTES) throw new IOException("Multipart başlığı çok büyük");
            if (line.regionMatches(true, 0, "Content-Disposition:", 0, 20)) {
                Matcher match = FILENAME.matcher(line.substring(20));
                if (match.find()) {
                    name = match.group(1) != null
                            ? match.group(1).replace("\\\"", "\"").replace("\\\\", "\\") : match.group(2).trim();
                }
            }
        }
        UploadFiles.resolve(directory, name);
        File temporary = File.createTempFile("mapcontrol-upload-", ".part", directory);
        boolean published = false;
        try {
            try (OutputStream output = new BufferedOutputStream(new FileOutputStream(temporary), 65536)) {
                copyFile(stream, output, ("\r\n--" + boundary).getBytes(StandardCharsets.US_ASCII));
            }
            if (stream.read() != -1) throw new IOException("Multipart sonunda beklenmeyen veri");
            if (temporary.length() == 0) throw new IOException("Dosya içeriği boş");
            synchronized (MultipartUpload.class) {
                String original = name;
                int dot = original.lastIndexOf('.');
                String stem = dot > 0 ? original.substring(0, dot) : original;
                String extension = dot > 0 ? original.substring(dot) : "";
                File target = UploadFiles.resolve(directory, original);
                for (int counter = 1; target.exists(); counter++) {
                    target = UploadFiles.resolve(directory, stem + " (" + counter + ")" + extension);
                }
                Files.move(temporary.toPath(), target.toPath());
                published = true;
                return target;
            }
        } finally {
            if (!published) Files.deleteIfExists(temporary.toPath());
        }
    }

    static String boundary(String contentType) throws IOException {
        if (contentType == null || contentType.indexOf('\r') >= 0 || contentType.indexOf('\n') >= 0
                || !contentType.split(";", 2)[0].trim().equalsIgnoreCase("multipart/form-data")) {
            throw new IOException("multipart/form-data gerekli");
        }
        Matcher match = BOUNDARY.matcher(contentType);
        if (!match.find()) throw new IOException("Boundary gerekli");
        String value = match.group(1) != null ? match.group(1) : match.group(2);
        if (!value.matches("[a-zA-Z0-9'()+_,./:=?-]{1,70}")) throw new IOException("Geçersiz boundary");
        return value;
    }

    private static String readLine(InputStream input) throws IOException {
        java.io.ByteArrayOutputStream line = new java.io.ByteArrayOutputStream();
        int previous = -1;
        while (line.size() <= HttpRequest.MAX_HEADER_BYTES) {
            int value = input.read();
            if (value < 0) throw new IOException("Eksik multipart başlığı");
            if (previous == '\r' && value == '\n') {
                byte[] data = line.toByteArray();
                return new String(data, 0, data.length - 1, StandardCharsets.UTF_8);
            }
            line.write(value);
            previous = value;
        }
        throw new IOException("Multipart satırı çok büyük");
    }

    private static void copyFile(PushbackInputStream input, OutputStream output, byte[] delimiter) throws IOException {
        byte[] pending = new byte[delimiter.length];
        int size = 0;
        for (int value; (value = input.read()) != -1;) {
            pending[size++] = (byte) value;
            while (!prefix(pending, size, delimiter)) {
                output.write(pending[0]);
                System.arraycopy(pending, 1, pending, 0, --size);
            }
            if (size == delimiter.length) {
                int a = input.read();
                int b = input.read();
                if (a == '-' && b == '-') {
                    int next = input.read();
                    if (next != -1 && (next != '\r' || input.read() != '\n')) throw new IOException("Geçersiz multipart sonu");
                    return;
                }
                if (a == '\r' && b == '\n') throw new IOException("Tek istekte yalnızca bir dosya yüklenebilir");
                if (b != -1) input.unread(b);
                if (a != -1) input.unread(a);
                output.write(pending[0]);
                System.arraycopy(pending, 1, pending, 0, --size);
                while (!prefix(pending, size, delimiter)) {
                    output.write(pending[0]);
                    System.arraycopy(pending, 1, pending, 0, --size);
                }
            }
        }
        throw new IOException("Eksik multipart içeriği");
    }

    private static boolean prefix(byte[] pending, int size, byte[] delimiter) {
        for (int i = 0; i < size; i++) if (pending[i] != delimiter[i]) return false;
        return true;
    }

    private static final class LimitedInput extends FilterInputStream {
        private long remaining;
        LimitedInput(InputStream input, long length) { super(input); remaining = length; }
        @Override public int read() throws IOException {
            if (remaining == 0) return -1;
            int result = in.read();
            if (result < 0) throw new IOException("Content-Length kadar veri gelmedi");
            remaining--;
            return result;
        }
        @Override public int read(byte[] buffer, int off, int count) throws IOException {
            if (remaining == 0) return -1;
            int result = in.read(buffer, off, (int) Math.min(count, remaining));
            if (result < 0) throw new IOException("Content-Length kadar veri gelmedi");
            remaining -= result;
            return result;
        }
    }
}
