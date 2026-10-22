package com.mapcontrol.nav;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Shared numeric units for EN/TR/DE/RU notifications; never infers values from word fragments. */
final class NavigationQuantities {
    private static final String BOUNDARY = "(?<![\\p{L}\\p{N}.,+\\-−])";
    private static final String NUMBER = "([0-9]+(?:[.,][0-9]+)?)\\s*";
    private static final String HOURS = "hours?|hrs?|h|saat|sa|stunden?|std|час(?:а|ов)?|ч";
    private static final String MINUTES = "minutes?|minuten?|mins?|min|dakika|dak|dk|минут(?:а|ы|у)?|мин";
    private static final int FLAGS = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.UNICODE_CHARACTER_CLASS;
    private static final Pattern DURATION = Pattern.compile(
            "(?:" + BOUNDARY + "|\\G)" + NUMBER + "(" + HOURS + "|" + MINUTES + ")(?![\\p{L}])\\.?", FLAGS);
    private static final Pattern HOUR_UNIT = Pattern.compile("^(?:" + HOURS + ")$", FLAGS);
    private static final Pattern DISTANCE = Pattern.compile(
            BOUNDARY + NUMBER + "(km|км|ft|mi|m|м)(?![\\p{L}\\p{N}])", FLAGS);

    private NavigationQuantities() { }

    static boolean hasQuantity(String value) {
        return value != null && (DURATION.matcher(value).find() || DISTANCE.matcher(value).find());
    }

    static int durationMinutes(String value) {
        if (value == null) return 0;
        Matcher matcher = DURATION.matcher(value);
        double minutes = 0;
        while (matcher.find()) {
            double number = number(matcher.group(1));
            if (!Double.isFinite(number)) return 0;
            minutes += number * (HOUR_UNIT.matcher(matcher.group(2)).matches() ? 60d : 1d);
            if (minutes >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
        }
        return (int) minutes;
    }

    static String durationLabel(String value) {
        if (value == null) return null;
        Matcher matcher = DURATION.matcher(value);
        int first = -1;
        int last = -1;
        while (matcher.find()) {
            if (first < 0) first = matcher.start();
            last = matcher.end();
        }
        return first < 0 ? null : value.substring(first, last).trim();
    }

    static String distanceLabel(String value) {
        if (value == null) return null;
        Matcher matcher = DISTANCE.matcher(value);
        return matcher.find() ? matcher.group().trim() : null;
    }

    static int segmentMeters(String value) {
        if (value == null) return 0;
        Matcher matcher = DISTANCE.matcher(value.trim());
        if (!matcher.lookingAt()) return 0;
        double meters = number(matcher.group(1)) * meterFactor(matcher.group(2));
        if (!Double.isFinite(meters)) return Integer.MAX_VALUE;
        return (int) Math.min(Integer.MAX_VALUE, Math.round(meters));
    }

    static String toMetric(String value) {
        if (value == null) return null;
        Matcher matcher = DISTANCE.matcher(value);
        StringBuffer out = new StringBuffer();
        while (matcher.find()) {
            String unit = matcher.group(2).toLowerCase(Locale.ROOT);
            String replacement = matcher.group();
            if ("ft".equals(unit) || "mi".equals(unit)) {
                double meters = number(matcher.group(1)) * meterFactor(unit);
                if (Double.isFinite(meters)) replacement = meters < 1000d
                        ? Math.round(meters) + " m" : String.format(Locale.GERMANY, "%.1f km", meters / 1000d);
            } else if ("км".equals(unit) || "м".equals(unit)) {
                replacement = matcher.group(1) + ("км".equals(unit) ? " km" : " m");
            }
            matcher.appendReplacement(out, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(out);
        return out.toString();
    }

    private static double number(String value) {
        try { return Double.parseDouble(value.replace(',', '.')); }
        catch (NumberFormatException invalid) { return Double.NaN; }
    }

    private static double meterFactor(String unit) {
        if ("km".equalsIgnoreCase(unit) || "км".equalsIgnoreCase(unit)) return 1000d;
        if ("ft".equalsIgnoreCase(unit)) return 0.3048d;
        if ("mi".equalsIgnoreCase(unit)) return 1609.344d;
        return 1d;
    }
}
