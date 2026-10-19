package com.mapcontrol.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** OEM/Android dumpsys formats. An unscoped foreground process is never a display match. */
public final class DisplayTaskParser {
    private static final Pattern DISPLAY = Pattern.compile("^\\s*Display\\s+#?(\\d+)\\b");
    private static final Pattern DISPLAY_ID = Pattern.compile("\\b(?:mDisplayId|displayId)=(\\d+)\\b");
    private static final Pattern RESUMED = Pattern.compile(
            "(?:mResumedActivity|topResumedActivity|mFocusedActivity).*?\\bu\\d+\\s+([a-zA-Z0-9_.]+)/");
    private DisplayTaskParser() { }
    public static boolean containsDisplay(String dump, int targetDisplay) {
        for (String line : dump.split("\\r?\\n")) {
            Matcher heading = DISPLAY.matcher(line);
            Matcher id = DISPLAY_ID.matcher(line);
            if (heading.find() && Integer.parseInt(heading.group(1)) == targetDisplay) return true;
            if (id.find() && Integer.parseInt(id.group(1)) == targetDisplay) return true;
        }
        return false;
    }
    public static String foregroundPackage(String dump, int targetDisplay) {
        int display = -1;
        int sectionIndent = -1;
        for (String line : dump.split("\\r?\\n")) {
            if (line.trim().isEmpty()) continue;
            int indent = 0;
            while (indent < line.length() && Character.isWhitespace(line.charAt(indent))) indent++;
            Matcher heading = DISPLAY.matcher(line);
            Matcher explicitId = DISPLAY_ID.matcher(line);
            if (heading.find()) {
                display = Integer.parseInt(heading.group(1));
                sectionIndent = indent;
            } else if (explicitId.find()) {
                display = Integer.parseInt(explicitId.group(1));
                sectionIndent = indent;
            } else if (indent <= sectionIndent) {
                display = -1;
                sectionIndent = -1;
            }
            Matcher resumed = RESUMED.matcher(line);
            if (display == targetDisplay && resumed.find()) return resumed.group(1);
        }
        return null;
    }
}
