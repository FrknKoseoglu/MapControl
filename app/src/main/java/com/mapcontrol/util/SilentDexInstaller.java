package com.mapcontrol.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Optional OEM installer asset. Standard builds fall back to PackageInstaller;
 * app_process inherits existing IVI permissions and does not grant new privileges.
 */
public final class SilentDexInstaller {

    private static final String ASSET_NAME = "mapcontrol-silent-installer.dex";
    private static final String INSTALLER_CLASS = "com.mapcontrol.adb.MapControlSilentInstaller";

    public static final class Result {
        public final boolean success;
        @NonNull
        public final String message;

        Result(boolean success, @NonNull String message) {
            this.success = success;
            this.message = message;
        }
    }

    private SilentDexInstaller() {
    }

    @NonNull
    public static Result install(@NonNull Context context, @NonNull File apkFile) {
        if (!apkFile.isFile()) {
            return new Result(false, "Dosya bulunamadı");
        }
        File dexFile = extractDex(context.getApplicationContext());
        if (dexFile == null) {
            return new Result(false, "İsteğe bağlı OEM kurulum aracı mevcut değil");
        }
        String appProcess = firstExecutable("/system/bin/app_process64", "/system/bin/app_process");
        if (appProcess == null) {
            return new Result(false, "app_process bulunamadı");
        }
        Process process = null;
        java.util.concurrent.FutureTask<String> reader = null;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    appProcess,
                    "/system/bin",
                    INSTALLER_CLASS,
                    apkFile.getAbsolutePath(),
                    "--grant-runtime");
            processBuilder.directory(new File("/system/bin"));
            processBuilder.redirectErrorStream(true);
            processBuilder.environment().put("CLASSPATH", dexFile.getAbsolutePath());
            process = processBuilder.start();
            final Process child = process;
            reader = new java.util.concurrent.FutureTask<>(() -> readAll(child.getInputStream()));
            Thread outputThread = new Thread(reader, "mapcontrol-installer-output");
            outputThread.setDaemon(true);
            outputThread.start();
            String output = reader.get(60, java.util.concurrent.TimeUnit.SECONDS).trim();
            if (!process.waitFor(2, java.util.concurrent.TimeUnit.SECONDS)) {
                return new Result(false, "OEM kurulum zaman aşımı");
            }
            int exitCode = process.exitValue();
            if (exitCode == 0 && output.contains("INSTALL_SUCCEEDED")) {
                return new Result(true, "Dex kurulum tamamlandı: " + apkFile.getName());
            }
            String lastLine = lastNonBlankLine(output);
            if (lastLine == null) {
                lastLine = "dex kurulum çıkış kodu " + exitCode;
            }
            return new Result(false, lastLine);
        } catch (InterruptedException error) {
            Thread.currentThread().interrupt();
            return new Result(false, "dex kurulum kesildi");
        } catch (java.util.concurrent.TimeoutException timeout) {
            return new Result(false, "OEM kurulum zaman aşımı");
        } catch (Exception error) {
            String detail = error.getMessage();
            if (detail == null || detail.isEmpty()) {
                detail = "dex kurulum çalışmadı";
            }
            return new Result(false, detail);
        } finally {
            if (process != null) process.destroyForcibly();
            if (reader != null) reader.cancel(true);
        }
    }

    @Nullable
    private static File extractDex(@NonNull Context context) {
        File directory = new File(context.getFilesDir(), "installer");
        if (!directory.isDirectory() && !directory.mkdirs()) {
            return null;
        }
        File dexFile = new File(directory, ASSET_NAME);
        try (InputStream input = context.getAssets().open(ASSET_NAME);
             FileOutputStream output = new FileOutputStream(dexFile)) {
            byte[] buffer = new byte[8192];
            int read;
            while ((read = input.read(buffer)) != -1) {
                output.write(buffer, 0, read);
            }
            return dexFile;
        } catch (Throwable ignored) {
            return null;
        }
    }

    @Nullable
    private static String firstExecutable(String... paths) {
        for (String path : paths) {
            File file = new File(path);
            if (file.canExecute()) {
                return path;
            }
        }
        return null;
    }

    @NonNull
    private static String readAll(@NonNull InputStream input) throws Exception {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            char[] buffer = new char[4096];
            for (int count; (count = reader.read(buffer)) != -1;) {
                if (builder.length() + count > 64 * 1024) throw new java.io.IOException("OEM kurulum çıktısı çok büyük");
                builder.append(buffer, 0, count);
            }
        }
        return builder.toString();
    }

    @Nullable
    private static String lastNonBlankLine(@NonNull String output) {
        String last = null;
        int start = 0;
        int length = output.length();
        while (start <= length) {
            int end = output.indexOf('\n', start);
            if (end < 0) {
                end = length;
            }
            String line = output.substring(start, end).trim();
            if (!line.isEmpty()) {
                last = line;
            }
            if (end == length) {
                break;
            }
            start = end + 1;
        }
        return last;
    }
}
