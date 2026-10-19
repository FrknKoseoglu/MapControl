package com.mapcontrol.http;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;

/** One key per explicit server start, shared only on the IVI screen. */
public final class WebSession {
    private final String key;
    public WebSession() {
        byte[] random = new byte[32];
        new SecureRandom().nextBytes(random);
        StringBuilder value = new StringBuilder(64);
        for (byte b : random) {
            value.append(Character.forDigit((b >>> 4) & 15, 16));
            value.append(Character.forDigit(b & 15, 16));
        }
        key = value.toString();
    }
    public String accessUrl(String baseUrl) { return baseUrl + "/#key=" + key; }
    public boolean accepts(String supplied) {
        return supplied != null && MessageDigest.isEqual(key.getBytes(StandardCharsets.US_ASCII),
                supplied.getBytes(StandardCharsets.US_ASCII));
    }
    public static boolean sameOrigin(String origin, String host) {
        if (origin == null) return true; // Command-line clients still require the key.
        try {
            URI uri = URI.create(origin);
            return "http".equalsIgnoreCase(uri.getScheme()) && uri.getRawAuthority() != null
                    && uri.getRawAuthority().equalsIgnoreCase(host) && uri.getRawUserInfo() == null
                    && uri.getRawQuery() == null && uri.getRawFragment() == null
                    && (uri.getRawPath() == null || uri.getRawPath().isEmpty());
        } catch (IllegalArgumentException error) { return false; }
    }
}
