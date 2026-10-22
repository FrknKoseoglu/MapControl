package com.mapcontrol.vehicle;

public final class LocationSamplePolicy {
    private LocationSamplePolicy() { }
    public static boolean usable(double latitude, double longitude, long ageMs, float accuracy,
                                 long maxAgeMs, float maxAccuracy) {
        return Double.isFinite(latitude) && Double.isFinite(longitude)
                && latitude >= -90 && latitude <= 90 && longitude >= -180 && longitude <= 180
                && ageMs >= 0 && ageMs <= maxAgeMs && Float.isFinite(accuracy)
                && accuracy >= 0 && accuracy <= maxAccuracy;
    }
}
