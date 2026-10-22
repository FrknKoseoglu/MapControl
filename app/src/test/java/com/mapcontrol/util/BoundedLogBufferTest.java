package com.mapcontrol.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoundedLogBufferTest {
    @Test public void keepsRecentEventsWithinTheMemoryBudget() {
        BoundedLogBuffer log = new BoundedLogBuffer(64);
        for (int i = 0; i < 10000; i++) log.append("vehicle-event-" + i + "\n");
        assertTrue(log.toString().length() <= 64);
        assertTrue(log.toString().endsWith("vehicle-event-9999\n"));
        log.clear();
        assertEquals("", log.toString());
    }
}
