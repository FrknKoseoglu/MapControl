package com.mapcontrol.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class DisplayTaskParserTest {
    @Test public void onlyReturnsTheRequestedDisplay() {
        String dump = "  Display #0 (activities from top to bottom):\n"
                + "    mResumedActivity: ActivityRecord{ab u0 org.main/.Main t1}\n"
                + "  Display #2 (activities from top to bottom):\n"
                + "    mResumedActivity: ActivityRecord{ac u0 ru.yandex.yandexmaps/.Main t2}\n";
        assertEquals("ru.yandex.yandexmaps", DisplayTaskParser.foregroundPackage(dump, 2));
        assertEquals("org.main", DisplayTaskParser.foregroundPackage(dump, 0));
        assertNull(DisplayTaskParser.foregroundPackage(dump, 3));
    }
    @Test public void supportsOemTaskDisplayFields() {
        String dump = "  Stack #12: displayId=4\n    mFocusedActivity: ActivityRecord{a u0 com.maps/.Main t3}\n";
        assertEquals("com.maps", DisplayTaskParser.foregroundPackage(dump, 4));
    }
    @Test public void neverAssignsAGlobalForegroundAppToTheCluster() {
        String dump = "  Display #2:\n    empty\n  topResumedActivity=ActivityRecord{a u0 com.main/.Main t1}\n";
        assertNull(DisplayTaskParser.foregroundPackage(dump, 2));
        assertTrue(DisplayTaskParser.containsDisplay(dump, 2));
        assertFalse(DisplayTaskParser.containsDisplay("Permission Denial", 2));
    }
}
