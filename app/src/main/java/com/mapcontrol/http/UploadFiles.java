package com.mapcontrol.http;

import java.io.File;
import java.io.IOException;

public final class UploadFiles {
    private UploadFiles() { }
    public static File resolve(File directory, String name) throws IOException {
        if (name == null || name.isEmpty() || name.equals(".") || name.equals("..")
                || name.length() > 240 || name.contains("/") || name.contains("\\")) {
            throw new IOException("Geçersiz dosya adı");
        }
        for (int i = 0; i < name.length(); i++) {
            if (Character.isISOControl(name.charAt(i))) throw new IOException("Geçersiz dosya adı");
        }
        File root = directory.getCanonicalFile();
        File target = new File(root, name).getCanonicalFile();
        if (!root.equals(target.getParentFile())) throw new IOException("Geçersiz dosya yolu");
        return target;
    }
}
