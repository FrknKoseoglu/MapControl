package com.mapcontrol.vehicle;

/** Missing/invalid VDBus samples cannot initiate an ignition transition. */
public final class PowerModeSample {
    private PowerModeSample() { }
    public static int read(int[] values) {
        if (values == null || values.length == 0) return -1;
        int mode = values[0];
        return mode >= 0 && mode <= 2 ? mode : -1;
    }
}
