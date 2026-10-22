package com.mapcontrol.http;

import org.junit.Test;
import static org.junit.Assert.*;

public class WebSessionTest {
    @Test public void onlyIvISharedKeyAuthenticatesAndRestartInvalidatesIt() {
        WebSession first = new WebSession();
        String key = first.accessUrl("http://ivi:7462").split("#key=")[1];
        assertTrue(first.accepts(key));
        assertFalse(first.accepts(null));
        assertFalse(first.accepts(""));
        assertFalse(new WebSession().accepts(key));
    }
    @Test public void rejectsCrossOriginAndOpaqueBrowserRequests() {
        assertTrue(WebSession.sameOrigin(null, "192.168.1.2:7462"));
        assertTrue(WebSession.sameOrigin("http://192.168.1.2:7462", "192.168.1.2:7462"));
        assertFalse(WebSession.sameOrigin("http://attacker.test", "192.168.1.2:7462"));
        assertFalse(WebSession.sameOrigin("http://192.168.1.2:7463", "192.168.1.2:7462"));
        assertFalse(WebSession.sameOrigin("null", "192.168.1.2:7462"));
    }
}
