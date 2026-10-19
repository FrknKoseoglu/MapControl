package com.mapcontrol.vehicle;

import org.junit.Test;
import static org.junit.Assert.*;

public class LocationSamplePolicyTest {
    @Test public void onlyRecentAccurateCoordinatesCanBeSavedAsParking() {
        assertTrue(LocationSamplePolicy.usable(41, 29, 30000, 20, 120000, 250));
        assertFalse(LocationSamplePolicy.usable(41, 29, 600000, 20, 120000, 250));
        assertFalse(LocationSamplePolicy.usable(41, 29, 30000, 2000, 120000, 250));
        assertFalse(LocationSamplePolicy.usable(41, 29, -1, 20, 120000, 250));
    }
    @Test public void rejectsInvalidCoordinatesAndMissingAccuracy() {
        assertFalse(LocationSamplePolicy.usable(Double.NaN, 29, 0, 20, 120000, 250));
        assertFalse(LocationSamplePolicy.usable(91, 29, 0, 20, 120000, 250));
        assertFalse(LocationSamplePolicy.usable(41, 181, 0, 20, 120000, 250));
        assertFalse(LocationSamplePolicy.usable(41, 29, 0, Float.NaN, 120000, 250));
    }
}
