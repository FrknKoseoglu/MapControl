package com.mapcontrol.http;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import static org.junit.Assert.*;

public class HttpRequestTest {
    private ByteArrayInputStream input(String value) {
        return new ByteArrayInputStream(value.getBytes(StandardCharsets.ISO_8859_1));
    }
    @Test public void preservesBodyAndParsesQuery() throws Exception {
        ByteArrayInputStream data = input("POST /upload?x=1 HTTP/1.1\r\nHost: ivi:7462\r\nContent-Length: 3\r\n\r\nABC");
        HttpRequest request = HttpRequest.read(data);
        assertEquals("/upload", request.path);
        assertEquals("x=1", request.query);
        assertEquals(3L, request.contentLength());
        assertEquals('A', data.read());
    }
    @Test public void acceptsAbsoluteRequestTarget() throws Exception {
        assertEquals("/files", HttpRequest.read(input("GET http://ivi:7462/files HTTP/1.1\r\nHost: ivi:7462\r\n\r\n")).path);
    }
    @Test public void rejectsAmbiguousLengthsAndChunkedFraming() {
        assertThrows(IOException.class, () -> HttpRequest.read(input("POST / HTTP/1.1\r\nHost: ivi\r\nContent-Length: 1\r\ncontent-length: 2\r\n\r\n")));
        assertThrows(IOException.class, () -> HttpRequest.read(input("POST / HTTP/1.1\r\nHost: ivi\r\nTransfer-Encoding: chunked\r\n\r\n")));
    }
    @Test public void boundsUnterminatedHeaders() {
        StringBuilder large = new StringBuilder("GET / HTTP/1.1\r\nHost: ");
        for (int i = 0; i < HttpRequest.MAX_HEADER_BYTES; i++) large.append('x');
        assertThrows(IOException.class, () -> HttpRequest.read(input(large.toString())));
    }
    @Test public void rejectsMissingHostAndOverflow() throws Exception {
        assertThrows(IOException.class, () -> HttpRequest.read(input("GET / HTTP/1.1\r\n\r\n")));
        HttpRequest request = HttpRequest.read(input("POST / HTTP/1.1\r\nHost: ivi\r\nContent-Length: 9223372036854775808\r\n\r\n"));
        assertThrows(IOException.class, request::contentLength);
    }
}
