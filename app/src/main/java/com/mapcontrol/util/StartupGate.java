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

    private StartupGate(boolean clearWelcome, boolean showWelcome, boolean playWelcome, boolean launchUi) {
        this.clearWelcome = clearWelcome;
        this.showWelcome = showWelcome;
        this.playWelcome = playWelcome;
        this.launchUi = launchUi;
    }

    public static StartupGate decide(int previousMode, int mode, boolean welcomeShown,
            boolean designEnabled, boolean uiPending) {
        if (mode < 1) {
            return new StartupGate(true, false, false, false);
        }
        boolean welcome = previousMode < 1 && !welcomeShown;
        return new StartupGate(false, welcome && designEnabled, welcome, uiPending);
    }
}
