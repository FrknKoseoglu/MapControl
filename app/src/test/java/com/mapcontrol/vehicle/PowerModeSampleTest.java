package com.mapcontrol.vehicle;

import org.junit.Test;
import static org.junit.Assert.*;

public class PowerModeSampleTest {
    @Test public void missingOrInvalidReadCannotBecomePowerOff() {
        assertEquals(-1, PowerModeSample.read(null));
        assertEquals(-1, PowerModeSample.read(new int[0]));
        assertEquals(-1, PowerModeSample.read(new int[]{255}));
        assertEquals(-1, PowerModeSample.read(new int[]{-1}));
    }
    @Test public void preservesOemOffStandbyAndOnValues() {
        for (int mode = 0; mode <= 2; mode++) assertEquals(mode, PowerModeSample.read(new int[]{mode}));
    }
}
