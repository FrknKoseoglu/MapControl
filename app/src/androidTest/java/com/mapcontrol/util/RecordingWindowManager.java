package com.mapcontrol.util;

import android.view.View;
import android.view.WindowManager;
import java.lang.reflect.Proxy;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/** Test-only WindowManager: records ownership without creating a real display/window. */
public final class RecordingWindowManager {
    public final AtomicInteger adds = new AtomicInteger();
    public final AtomicInteger removes = new AtomicInteger();
    public final CopyOnWriteArrayList<View> removed = new CopyOnWriteArrayList<>();
    public final CountDownLatch firstRemoval = new CountDownLatch(1);
    public final WindowManager manager = (WindowManager) Proxy.newProxyInstance(
            WindowManager.class.getClassLoader(), new Class<?>[]{WindowManager.class}, (proxy, method, args) -> {
                if ("addView".equals(method.getName())) adds.incrementAndGet();
                if ("removeViewImmediate".equals(method.getName()) || "removeView".equals(method.getName())) {
                    removes.incrementAndGet();
                    removed.add((View) args[0]);
                    firstRemoval.countDown();
                }
                if (method.getReturnType() == boolean.class) return false;
                if (method.getReturnType() == int.class) return 0;
                return null;
            });
}
