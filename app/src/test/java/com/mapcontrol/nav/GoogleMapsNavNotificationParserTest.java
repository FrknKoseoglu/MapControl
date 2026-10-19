package com.mapcontrol.nav;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GoogleMapsNavNotificationParserTest {

    @Test
    public void charSequenceExtrasAreKept() {
        assertNull(GoogleMapsNavNotificationParser.normalizeExtra(null));
        assertNull(GoogleMapsNavNotificationParser.normalizeExtra("   "));
        assertEquals("200 m", GoogleMapsNavNotificationParser.normalizeExtra(new StringBuilder(" 200 m ")));
    }

    @Test
    public void missingSubTextIsInactive() {
        GoogleMapsNavSnapshot snap = GoogleMapsNavNotificationParser.parseFields(
                "200 m", "Turn left - Main", null, false);
        assertFalse(snap.active);
        assertNull(snap.formatSummaryLine());
    }

    @Test
    public void convertsFeetAndMilesAndStripsClockLabels() {
        GoogleMapsNavSnapshot snap = GoogleMapsNavNotificationParser.parseFields(
                "500 ft",
                "Turn left TVS: - Oak",
                "1.2 mi · 12 min ETA",
                true);
        assertTrue(snap.active);
        assertTrue(snap.hadIcon);
        assertEquals("152 m", snap.distanceTitle);
        assertEquals("Turn left", snap.maneuverHint);
        assertEquals("1,9 km · 12 min", snap.routeSummary);
    }

    @Test
    public void ignoresPromptsWithoutDistanceOrDuration() {
        GoogleMapsNavSnapshot signIn = GoogleMapsNavNotificationParser.parseFields(
                "Sign in to Google", "Account - prompt", "Open Maps", false);
        assertFalse(signIn.active);

        GoogleMapsNavSnapshot store = GoogleMapsNavNotificationParser.parseFields(
                "Store", "Ready to install - Store", "Open the app", false);
        assertFalse(store.active);
    }

    @Test
    public void shortSubTextStillActive() {
        GoogleMapsNavSnapshot snap = GoogleMapsNavNotificationParser.parseFields(
                "80 m", null, "Arrive", false);
        assertTrue(snap.active);
        assertEquals("80 m", snap.distanceTitle);
        assertEquals("Arrive", snap.routeSummary);
        assertNull(snap.maneuverHint);
    }

    @Test
    public void snapshotExposesClusterCardFields() {
        GoogleMapsNavSnapshot snap = GoogleMapsNavNotificationParser.parseFields(
                "500 ft - Head south-west",
                "Vega AVM - 12:26 ETA",
                "1 hr 34 min · 89 mi · 12:26 ETA",
                true);
        assertTrue(snap.active);
        assertEquals("152 m", snap.formatManeuverDistanceLine());
        assertEquals("Head south-west", snap.formatManeuverDirectionLine());
        assertEquals("143,2 km", snap.etaDistanceLabel());
        assertEquals("1 hr 34 min", snap.etaDurationLabel());
        assertEquals("12:26", snap.etaArrivalLabel());
    }

    @Test
    public void segmentDistanceUsesMetersForVdBus() {
        GoogleMapsNavSnapshot meters = GoogleMapsNavNotificationParser.parseFields(
                "80 m - Turn left", null, "2 min", false);
        GoogleMapsNavSnapshot kilometers = GoogleMapsNavNotificationParser.parseFields(
                "1,2 km - Continue", null, "3 min", false);
        GoogleMapsNavSnapshot feet = GoogleMapsNavNotificationParser.parseFields(
                "500 ft - Turn right", null, "2 min", false);
        assertEquals(80, meters.segmentRemainMeters());
        assertEquals(1200, kilometers.segmentRemainMeters());
        assertEquals(152, feet.segmentRemainMeters());
    }

    @Test
    public void missingSegmentDistanceDoesNotInventAManeuverDistance() {
        assertEquals(0, GoogleMapsNavSnapshot.inactive().segmentRemainMeters());
        GoogleMapsNavSnapshot withoutDistance = GoogleMapsNavNotificationParser.parseFields(
                "Continue", null, "2 min", false);
        assertEquals(0, withoutDistance.segmentRemainMeters());
    }

    @Test
    public void oversizedSegmentDistanceDoesNotOverflow() {
        GoogleMapsNavSnapshot snapshot = GoogleMapsNavNotificationParser.parseFields(
                "999999999 km", null, "2 min", false);
        assertEquals(Integer.MAX_VALUE, snapshot.segmentRemainMeters());
    }

    @Test public void remainingDistanceInMetersIsShownOnTheClusterCard() {
        GoogleMapsNavSnapshot snapshot = GoogleMapsNavNotificationParser.parseFields(
                "80 m", null, "500 m · 2 dk · 14:00", false);
        assertEquals("500 m", snapshot.etaDistanceLabel());
    }
}
