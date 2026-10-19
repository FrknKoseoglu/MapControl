package com.mapcontrol.vehicle;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.SystemClock;

/** Uses the IVI's existing fixes; never enables GPS or changes a vehicle setting. */
public final class VehicleLocationProvider {
    private VehicleLocationProvider() { }
    public static Location lastKnown(Context context, long maxAgeMs, float maxAccuracy) {
        if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) return null;
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (manager == null) return null;
        Location newest = null;
        for (String provider : new String[]{LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER, LocationManager.PASSIVE_PROVIDER}) {
            try {
                Location fix = manager.getLastKnownLocation(provider);
                if (fix == null || !fix.hasAccuracy()) continue;
                long age = fix.getElapsedRealtimeNanos() > 0
                        ? (SystemClock.elapsedRealtimeNanos() - fix.getElapsedRealtimeNanos()) / 1000000L
                        : System.currentTimeMillis() - fix.getTime();
                if (!LocationSamplePolicy.usable(fix.getLatitude(), fix.getLongitude(), age,
                        fix.getAccuracy(), maxAgeMs, maxAccuracy)) continue;
                if (newest == null || fix.getElapsedRealtimeNanos() > newest.getElapsedRealtimeNanos()) newest = fix;
            } catch (RuntimeException unavailableProvider) {
                // OEMs can omit a provider; coarse-only grants can deny GPS while network still works.
            }
        }
        return newest;
    }
}
