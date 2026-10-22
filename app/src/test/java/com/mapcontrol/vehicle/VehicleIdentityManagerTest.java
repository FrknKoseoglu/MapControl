package com.mapcontrol.vehicle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VehicleIdentityManagerTest {

    @Test
    public void testVinYearDecoding() {
        assertEquals("2023", VehicleIdentityManager.decodeVinYearChar('P'));
        assertEquals("2024", VehicleIdentityManager.decodeVinYearChar('R'));
        assertEquals("2025", VehicleIdentityManager.decodeVinYearChar('S'));
        assertEquals("2026", VehicleIdentityManager.decodeVinYearChar('T'));
        assertEquals("2027", VehicleIdentityManager.decodeVinYearChar('V'));
        assertEquals("2028", VehicleIdentityManager.decodeVinYearChar('W'));

        // Case insensitivity
        assertEquals("2025", VehicleIdentityManager.decodeVinYearChar('s'));
        assertEquals("2024", VehicleIdentityManager.decodeVinYearChar('r'));

        // Invalid letters in ISO 3779 (I, O, Q, U, Z)
        assertNull(VehicleIdentityManager.decodeVinYearChar('O'));
        assertNull(VehicleIdentityManager.decodeVinYearChar('I'));
        assertNull(VehicleIdentityManager.decodeVinYearChar('Q'));
        assertNull(VehicleIdentityManager.decodeVinYearChar('U'));
        assertNull(VehicleIdentityManager.decodeVinYearChar('Z'));
    }

    @Test
    public void testVinValidation() {
        // Valid 17 char VIN
        assertTrue(VehicleIdentityManager.isValidVin("LVVDB21B8SD123456"));

        // Invalid VINs
        assertFalse(VehicleIdentityManager.isValidVin(null));
        assertFalse(VehicleIdentityManager.isValidVin(""));
        assertFalse(VehicleIdentityManager.isValidVin("SHORTVIN"));
        // Letter 'O' is invalid in VIN standard
        assertFalse(VehicleIdentityManager.isValidVin("LVVDB21B8OD123456"));
    }

    @Test
    public void testParseVinFromIntArray() {
        int[] ascii = {'L', 'V', 'V', 'D', 'B', '2', '1', 'B', '8', 'S', 'D', '1', '2', '3', '4', '5', '6'};
        String parsed = VehicleIdentityManager.parseVinFromIntArray(ascii);
        assertEquals("LVVDB21B8SD123456", parsed);

        assertEquals("2025", VehicleIdentityManager.decodeModelYear(parsed, null));
    }

    @Test
    public void testModelYearFallbackFromBuildString() {
        // Build display string with YYMMDD date format (_250408_)
        String buildDisplay = "G7PH_CHERY_T19C_INT_CS_SOP3_03_13310_250408_R";
        assertEquals("2025", VehicleIdentityManager.decodeModelYear(null, buildDisplay));

        // Build display with 4 digit year
        String fingerprint = "DesaySV/g7ph_t22_int/msmnile_gvmq:11/RQ3A.210805.001.A1/eng.ici2.20241215.040659:user/dev-keys";
        assertEquals("2024", VehicleIdentityManager.decodeModelYear(null, fingerprint));
    }
}
