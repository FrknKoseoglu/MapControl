package com.mapcontrol.vehicle;

/** One request per confirmed entry to SPORT, with a short settling window and no retries. */
public final class SportAnimationPolicy {
    public static final long SETTLE_DELAY_MS = 600;
    private int previousMode = -1;
    private long pendingAt = -1;

    public boolean observe(boolean enabled, boolean connected, int mode, long nowMs) {
        if (!enabled || !connected || !knownMode(mode)) {
            reset();
            return false;
        }
        if (previousMode == -1) {
            // Starting/reconnecting/enabling while already in SPORT must not close an unrelated dialog.
            previousMode = mode;
            return false;
        }
        if (previousMode != mode) {
            pendingAt = mode == 2 ? nowMs + SETTLE_DELAY_MS : -1;
            previousMode = mode;
        }
        if (mode == 2 && pendingAt >= 0 && nowMs >= pendingAt) {
            pendingAt = -1;
            return true;
        }
        return false;
    }

    public void reset() {
        previousMode = -1;
        pendingAt = -1;
    }

    private boolean knownMode(int mode) {
        return mode >= 0 && mode <= 5 || mode == 7;
    }
}
