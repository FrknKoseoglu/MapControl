package com.mapcontrol.util;

/**
 * Güç örneğinden karşılama ve ana ekran kararını üretir.
 * Mod 1'in altı karşılama kaydını siler. Ana ekran, bekleyen açılış isteği varken
 * mod 1 veya üzerindeyken açılır; kenarın tekrarını beklemez.
 */
public final class StartupGate {

    public final boolean clearWelcome;
    public final boolean showWelcome;
    public final boolean playWelcome;
    public final boolean launchUi;
    public final boolean rearmUi;

    private StartupGate(boolean clearWelcome, boolean showWelcome, boolean playWelcome, boolean launchUi, boolean rearmUi) {
        this.clearWelcome = clearWelcome;
        this.showWelcome = showWelcome;
        this.playWelcome = playWelcome;
        this.launchUi = launchUi;
        this.rearmUi = rearmUi;
    }

    public static StartupGate decide(int previousMode, int mode, boolean welcomeShown,
            boolean designEnabled, boolean uiPending) {
        return decide(previousMode, mode, welcomeShown, designEnabled, uiPending, false);
    }

    public static StartupGate decide(int previousMode, int mode, boolean welcomeShown,
            boolean designEnabled, boolean uiPending, boolean autoLaunchUi) {
        if (mode < 0 || mode > 2) return new StartupGate(false, false, false, false, false);
        if (mode < 1) {
            return new StartupGate(true, false, false, false, false);
        }
        boolean welcome = previousMode < 1 && !welcomeShown;
        boolean rearm = previousMode == 0 && autoLaunchUi;
        return new StartupGate(false, welcome && designEnabled, welcome, uiPending || rearm, rearm);
    }
}
