package com.mapcontrol.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/** Bounded HTTP/1.x headers; never consumes the first body byte. */
public final class HttpRequest {
    public static final int MAX_HEADER_BYTES = 16 * 1024;
    public final String method;
    public final String path;
    public final String query;
    public final String rawHeaders;
    private final Map<String, String> headers;

    private HttpRequest(String method, String path, String query, String raw, Map<String, String> headers) {
        this.method = method; this.path = path; this.query = query;
        this.rawHeaders = raw; this.headers = headers;
    }

    public String header(String name) { return headers.get(name.toLowerCase(Locale.ROOT)); }

    public long contentLength() throws IOException {
        String value = header("content-length");
        if (value == null || !value.matches("[0-9]+")) throw new IOException("Content-Length gerekli");
        try { return Long.parseLong(value); }
        catch (NumberFormatException error) { throw new IOException("Geçersiz Content-Length", error); }
    }

    public static HttpRequest read(InputStream input) throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        int ending = 0;
        while (ending != 0x0d0a0d0a) {
            int value = input.read();
            if (value < 0) throw new IOException("Eksik HTTP başlığı");
            if (bytes.size() >= MAX_HEADER_BYTES) throw new IOException("HTTP başlığı çok büyük");
            bytes.write(value);
            ending = (ending << 8) | value;
        }
        String raw = new String(bytes.toByteArray(), StandardCharsets.ISO_8859_1);
        String[] lines = raw.split("\r\n");
        String[] request = lines[0].split(" ", -1);
        if (request.length != 3 || !request[0].matches("[A-Z]+")
                || !("HTTP/1.1".equals(request[2]) || "HTTP/1.0".equals(request[2]))) {
            throw new IOException("Geçersiz HTTP isteği");
        }
        Map<String, String> headers = new LinkedHashMap<>();
        for (int i = 1; i < lines.length; i++) {
            int colon = lines[i].indexOf(':');
            if (colon <= 0 || i > 80) throw new IOException("Geçersiz HTTP başlığı");
            String key = lines[i].substring(0, colon).toLowerCase(Locale.ROOT);
            if (!key.matches("[a-z0-9!#$%&'*+.^_`|~-]+") || headers.containsKey(key)) {
                throw new IOException("Tekrarlanan veya geçersiz HTTP başlığı");
            }
            headers.put(key, lines[i].substring(colon + 1).trim());
        }
        if (headers.containsKey("transfer-encoding")) throw new IOException("Transfer-Encoding desteklenmiyor");
        if ("HTTP/1.1".equals(request[2]) && !headers.containsKey("host")) throw new IOException("Host gerekli");
        try {
            URI uri = URI.create(request[1]);
            if (uri.getFragment() != null || uri.getRawPath() == null || !uri.getRawPath().startsWith("/")
                    || (uri.isAbsolute() && !"http".equalsIgnoreCase(uri.getScheme()))) {
                throw new IllegalArgumentException();
            }
            return new HttpRequest(request[0], uri.getRawPath(),
                    uri.getRawQuery() == null ? "" : uri.getRawQuery(), raw, headers);
        } catch (IllegalArgumentException error) { throw new IOException("Geçersiz HTTP adresi", error); }
    }
}
