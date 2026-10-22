package com.mapcontrol.manager;

import android.content.Context;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.ByteArrayOutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import static org.junit.Assert.*;

/** Socket-level checks on Android, with no VDBus commands or vehicle setting changes. */
@RunWith(AndroidJUnit4.class)
public class WebServerIntegrationTest {
    private WebServerManager server;
    private String key;

    @Before public void start() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        server = new WebServerManager(context);
        server.startServer();
        String url = server.getAccessUrl("127.0.0.1");
        assertTrue("Test requires the app's HTTP port to be free", url.contains("#key="));
        key = url.substring(url.indexOf("#key=") + 5);
    }

    @After public void stop() { if (server != null) server.stopServer(); }

    @Test public void welcomeAndHeadDoNotExposeKey() throws Exception {
        String get = request("GET / HTTP/1.1\r\nHost: localhost:7462\r\n\r\n");
        assertTrue(get.startsWith("HTTP/1.1 200"));
        assertFalse(get.contains(key));
        String head = request("HEAD / HTTP/1.1\r\nHost: localhost:7462\r\n\r\n");
        assertTrue(head.startsWith("HTTP/1.1 200"));
        assertEquals("HEAD must contain no HTML body", head.indexOf("\r\n\r\n") + 4, head.length());
    }

    @Test public void everyManagementRouteRequiresSession() throws Exception {
        String[] routes = {"GET /files", "GET /apps", "POST /install?file=test.apk",
                "DELETE /files?file=test", "POST /upload", "POST /app/delete?package=test",
                "POST /app/launch?package=test", "POST /action/open-maps", "POST /action/keyboard"};
        for (String route : routes) {
            assertTrue(route, request(route + " HTTP/1.1\r\nHost: localhost:7462\r\n\r\n")
                    .startsWith("HTTP/1.1 401"));
        }
    }

    @Test public void originAndSessionRotationAreEnforced() throws Exception {
        String authorized = "GET /apps HTTP/1.1\r\nHost: localhost:7462\r\nX-MapControl-Key: " + key + "\r\n";
        assertTrue(request(authorized + "Origin: http://localhost:7462\r\n\r\n").startsWith("HTTP/1.1 200"));
        assertTrue(request(authorized + "Origin: https://example.com\r\n\r\n").startsWith("HTTP/1.1 403"));
        server.stopServer();
        server.startServer();
        assertTrue(request(authorized + "\r\n").startsWith("HTTP/1.1 401"));
    }

    @Test public void malformedAndOversizedRequestsAreRejectedBeforeBody() throws Exception {
        assertTrue(request("POST /upload HTTP/1.1\r\nHost: localhost:7462\r\nContent-Length: 1\r\n"
                + "Content-Length: 2\r\n\r\n").startsWith("HTTP/1.1 400"));
        assertTrue(request("POST /upload HTTP/1.1\r\nHost: localhost:7462\r\nX-MapControl-Key: " + key
                + "\r\nContent-Length: 1073741825\r\n\r\n").startsWith("HTTP/1.1 413"));
    }

    @Test public void stopClosesClientsWithIncompleteHeaders() throws Exception {
        try (Socket client = new Socket("127.0.0.1", 7462)) {
            client.setSoTimeout(3000);
            client.getOutputStream().write("GET / HTTP/1.1\r\n".getBytes(StandardCharsets.US_ASCII));
            server.stopServer();
            try { assertEquals(-1, client.getInputStream().read()); }
            catch (java.net.SocketException closed) { /* reset also confirms closure */ }
        }
    }

    private String request(String request) throws Exception {
        try (Socket socket = new Socket("127.0.0.1", 7462)) {
            socket.setSoTimeout(5000);
            socket.getOutputStream().write(request.getBytes(StandardCharsets.UTF_8));
            ByteArrayOutputStream response = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            for (int count; (count = socket.getInputStream().read(buffer)) != -1;) response.write(buffer, 0, count);
            return new String(response.toByteArray(), StandardCharsets.UTF_8);
        }
    }
}
