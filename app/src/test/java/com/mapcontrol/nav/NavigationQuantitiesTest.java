package com.mapcontrol.nav;

import org.junit.Test;
import static org.junit.Assert.*;

public class NavigationQuantitiesTest {
    @Test public void compactAndSpacedDurationsAgree() {
        for (String text : new String[]{"1 hr 20 min", "1hr 20min", "1hr20min", "1h20min"})
            assertEquals(text, 80, GoogleMapsClusterNavVDBusPublisher.parseDurationMinutes(text));
        for (String text : new String[]{"1 sa 10 dk", "1sa10dk", "1 saat 10 dakika"})
            assertEquals(text, 70, NavigationQuantities.durationMinutes(text));
    }

    @Test public void russianAndGermanDurationsAreRecognized() {
        for (String text : new String[]{"1 ч 20 мин", "1ч20мин", "1 час 20 минут", "1 Std. 20 Min.", "1 Stunde 20 Minuten"})
            assertEquals(text, 80, NavigationQuantities.durationMinutes(text));
    }

    @Test public void decimalsAndUnicodeSpacesAreSupported() {
        assertEquals(90, NavigationQuantities.durationMinutes("1,5 hr"));
        assertEquals(90, NavigationQuantities.durationMinutes("1.5 h"));
        assertEquals(65, NavigationQuantities.durationMinutes("1\u00a0Std.\u202f5\u00a0Min."));
    }

    @Test public void wordsAndInvalidNumbersDoNotBecomeDurations() {
        for (String text : new String[]{"2 minimal", "1 safer", "12:30", "-5 min", "−5 min", "1e5 min", "5minivan"})
            assertEquals(text, 0, NavigationQuantities.durationMinutes(text));
        assertEquals(0, NavigationQuantities.durationMinutes(null));
        assertEquals(Integer.MAX_VALUE, NavigationQuantities.durationMinutes("999999999999999 hr"));
    }

    @Test public void quantityDetectionDoesNotMatchUnitFragments() {
        assertTrue(NavigationQuantities.hasQuantity("500 м"));
        assertTrue(NavigationQuantities.hasQuantity("5 км"));
        assertTrue(NavigationQuantities.hasQuantity("1 Std."));
        assertFalse(NavigationQuantities.hasQuantity("2 minimal offers"));
        assertFalse(NavigationQuantities.hasQuantity("5 milesaway"));
    }

    @Test public void distanceConversionPreservesDisplayAndVdBusUnits() {
        assertEquals("500 m - Направо", NavigationQuantities.toMetric("500 м - Направо"));
        assertEquals("1,2 km", NavigationQuantities.toMetric("1,2 км"));
        assertEquals("152 m", NavigationQuantities.toMetric("500 ft"));
        assertEquals(1200, NavigationQuantities.segmentMeters("1,2 км"));
        assertEquals(0, NavigationQuantities.segmentMeters("Continue 500 m"));
    }

    @Test public void summaryWithoutSpacesAroundSeparatorsKeepsAllFields() {
        GoogleMapsNavSnapshot snapshot = GoogleMapsNavNotificationParser.parseFields(
                "500 м - Поверните направо", "Поверните направо", "5 км•1ч20мин·12:30", false);
        assertTrue(snapshot.active);
        assertEquals(500, snapshot.segmentRemainMeters());
        assertEquals("5 km", snapshot.etaDistanceLabel());
        assertEquals("1ч20мин", snapshot.etaDurationLabel());
        assertEquals("12:30", snapshot.etaArrivalLabel());
        assertEquals(80, GoogleMapsClusterNavVDBusPublisher.parseDurationMinutes(snapshot.etaDurationLabel()));
        assertEquals("Поверните направо", snapshot.maneuverHint);
    }

    @Test public void germanSummaryRemainsActiveAndHasCorrectEta() {
        GoogleMapsNavSnapshot snapshot = GoogleMapsNavNotificationParser.parseFields(
                "500 m - Rechts abbiegen", "Rechts abbiegen", "5 km · 1 Std 20 Min · 12:30", false);
        assertTrue(snapshot.active);
        assertEquals("1 Std 20 Min", snapshot.etaDurationLabel());
        assertEquals(80, GoogleMapsClusterNavVDBusPublisher.parseDurationMinutes(snapshot.etaDurationLabel()));
    }

    @Test public void compactSummaryDoesNotConfuseDistanceAndDuration() {
        GoogleMapsNavSnapshot snapshot = new GoogleMapsNavSnapshot(true, null, "80 m", "5 km 1h20min ETA 12:30", false);
        assertEquals("5 km", snapshot.etaDistanceLabel());
        assertEquals("1h20min", snapshot.etaDurationLabel());
        assertEquals("12:30", snapshot.etaArrivalLabel());
    }

    @Test public void clockLabelsDoNotRemoveLettersInsideRoadNames() {
        GoogleMapsNavSnapshot snapshot = GoogleMapsNavNotificationParser.parseFields(
                "80 m - Beta Street", "Turn left", "2 min ETA 12:30", false);
        assertEquals("Beta Street", snapshot.formatManeuverDirectionLine());
    }

    @Test public void localizedManeuversKeepTheExistingOemCodes() {
        for (String text : new String[]{"Turn left", "Sağa dön", "Поверните направо", "Rechts abbiegen"})
            assertEquals(text, 16, GoogleMapsClusterNavManeuverMapper.mapFromNotificationText("500 m", text));
        assertEquals(14, GoogleMapsClusterNavManeuverMapper.mapFromNotificationText("500 m", "Continue"));
    }
}
