package com.mapcontrol.util;

/** Thread-safe tail of the IVI log, including when the vehicle stays awake for days. */
public final class BoundedLogBuffer {
    private final int limit;
    private final StringBuilder value = new StringBuilder();
    public BoundedLogBuffer(int limit) {
        if (limit < 1) throw new IllegalArgumentException("limit");
        this.limit = limit;
    }
    public synchronized void append(String line) {
        value.append(line);
        if (value.length() > limit) {
            int cut = value.length() - limit;
            int nextLine = value.indexOf("\n", cut);
            value.delete(0, nextLine >= 0 && nextLine < value.length() - 1 ? nextLine + 1 : cut);
        }
    }
    public synchronized void clear() { value.setLength(0); }
    @Override public synchronized String toString() { return value.toString(); }
}
