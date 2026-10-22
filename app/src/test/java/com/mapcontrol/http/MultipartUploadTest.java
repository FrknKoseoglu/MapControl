package com.mapcontrol.http;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Random;
import static org.junit.Assert.*;

public class MultipartUploadTest {
    @Rule public TemporaryFolder temporary = new TemporaryFolder();
    private byte[] body(String name, byte[] file) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(("--test-boundary\r\nContent-Disposition: form-data; name=\"file\"; filename=\"" + name + "\"\r\nContent-Type: application/octet-stream\r\n\r\n").getBytes(StandardCharsets.UTF_8));
        out.write(file);
        out.write("\r\n--test-boundary--\r\n".getBytes(StandardCharsets.UTF_8));
        return out.toByteArray();
    }
    private File upload(File directory, String name, byte[] content) throws Exception {
        byte[] request = body(name, content);
        return MultipartUpload.receive(new ByteArrayInputStream(request), request.length,
                "multipart/form-data; boundary=\"test-boundary\"", directory);
    }
    @Test public void preservesTrailingCrLfBytes() throws Exception {
        byte[] file = "ABC\r\n".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(file, Files.readAllBytes(upload(temporary.newFolder(), "test.txt", file).toPath()));
    }
    @Test public void preservesBinaryAndBoundaryLikeDataAcrossBuffers() throws Exception {
        byte[] content = new byte[131123];
        new Random(17).nextBytes(content);
        byte[] lookalike = "\r\n--test-boundaryXYZ\r\n".getBytes(StandardCharsets.UTF_8);
        System.arraycopy(lookalike, 0, content, 65530, lookalike.length);
        assertArrayEquals(content, Files.readAllBytes(upload(temporary.newFolder(), "binary.bin", content).toPath()));
    }
    @Test public void acceptsUnicodeAndApostrophesWithoutAlteringThem() throws Exception {
        assertEquals("Sürücü'nün sesi.mp3", upload(temporary.newFolder(), "Sürücü'nün sesi.mp3", new byte[]{1}).getName());
    }
    @Test public void preservesExistingFileOnNameCollision() throws Exception {
        File directory = temporary.newFolder();
        File first = upload(directory, "sound.mp3", new byte[]{1});
        File second = upload(directory, "sound.mp3", new byte[]{2});
        assertEquals("sound (1).mp3", second.getName());
        assertArrayEquals(new byte[]{1}, Files.readAllBytes(first.toPath()));
        assertArrayEquals(new byte[]{2}, Files.readAllBytes(second.toPath()));
    }
    @Test public void rejectsTraversalBeforeWritingAndCleansPartialFiles() throws Exception {
        File directory = temporary.newFolder();
        assertThrows(IOException.class, () -> upload(directory, "../outside.txt", new byte[]{1}));
        assertEquals(0, directory.list().length);
        byte[] request = body("broken.txt", new byte[]{1, 2, 3});
        assertThrows(IOException.class, () -> MultipartUpload.receive(
                new ByteArrayInputStream(request, 0, request.length - 9), request.length,
                "multipart/form-data; boundary=test-boundary", directory));
        assertEquals(0, directory.list().length);
    }
    @Test public void capsDeclaredLengthBeforeAllocatingOrReading() throws Exception {
        assertThrows(IOException.class, () -> MultipartUpload.receive(new ByteArrayInputStream(new byte[0]),
                Long.MAX_VALUE, "multipart/form-data; boundary=test-boundary", temporary.getRoot()));
    }
    @Test public void consumesExactlyDeclaredBody() throws Exception {
        byte[] request = body("file.txt", new byte[]{1});
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        stream.write(request); stream.write(42);
        ByteArrayInputStream input = new ByteArrayInputStream(stream.toByteArray());
        MultipartUpload.receive(input, request.length, "multipart/form-data; boundary=test-boundary", temporary.newFolder());
        assertEquals(42, input.read());
    }
    @Test public void rejectsInvalidBoundaryAndMissingFilename() throws Exception {
        assertThrows(IOException.class, () -> MultipartUpload.boundary("multipart/form-data; boundary=bad\r\nvalue"));
        byte[] request = "--test-boundary\r\nContent-Disposition: form-data; name=\"file\"\r\n\r\na\r\n--test-boundary--\r\n".getBytes(StandardCharsets.UTF_8);
        assertThrows(IOException.class, () -> MultipartUpload.receive(new ByteArrayInputStream(request), request.length,
                "multipart/form-data; boundary=test-boundary", temporary.newFolder()));
    }
}
