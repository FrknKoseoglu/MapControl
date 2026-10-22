package com.mapcontrol.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import java.util.concurrent.atomic.AtomicBoolean;

/** Owns the closing window until removal; callbacks never consult a newer overlay's fields. */
public final class OverlayDismissal {
    private static final Handler MAIN = new Handler(Looper.getMainLooper());
    private OverlayDismissal() { }

    public static void dismiss(WindowManager manager, View view, long durationMs) {
        if (manager == null || view == null) return;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            MAIN.post(() -> dismiss(manager, view, durationMs));
            return;
        }
        AtomicBoolean removed = new AtomicBoolean();
        Runnable[] fallback = new Runnable[1];
        Runnable remove = () -> {
            if (!removed.compareAndSet(false, true)) return;
            if (fallback[0] != null) MAIN.removeCallbacks(fallback[0]);
            view.animate().setListener(null).withEndAction(null).cancel();
            try {
                manager.removeViewImmediate(view);
            } catch (IllegalArgumentException alreadyDetached) {
                // WindowManager has already removed this exact view.
            }
        };
        view.animate().cancel();
        if (!view.isAttachedToWindow() || durationMs <= 0) {
            remove.run();
            return;
        }
        // Detached/cancelled animations are not guaranteed to deliver an end callback.
        fallback[0] = remove;
        MAIN.postDelayed(remove, durationMs + 100L);
        try {
            view.animate().alpha(0f).setDuration(durationMs).setListener(new AnimatorListenerAdapter() {
                @Override public void onAnimationEnd(Animator animation) { remove.run(); }
                @Override public void onAnimationCancel(Animator animation) { remove.run(); }
            }).start();
        } catch (RuntimeException unavailableAnimation) {
            remove.run();
        }
    }
}
