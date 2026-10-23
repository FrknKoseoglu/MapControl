package com.mapcontrol.manager;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import java.util.List;

/** Matches the archived OEM dialog's three resource IDs; never sends global BACK or broadcasts. */
final class SportAnimationWindow {
    static final String PACKAGE = "com.desaysv.vehiclesetting";
    static final String BACK = PACKAGE + ":id/btn_back";
    static final String VIDEO = PACKAGE + ":id/view_video";
    static final String ITEMS = PACKAGE + ":id/layout_items";

    interface Node {
        String packageName();
        String resourceId();
        boolean visible();
        boolean enabled();
        boolean clickable();
        int windowId();
        int childCount();
        Node child(int index);
        Node parent();
        boolean click();
        void recycle();
    }

    static SportAnimationController.Dialog find(AccessibilityService service) {
        return findInWindows(service.getWindows());
    }

    static SportAnimationController.Dialog findInWindows(List<AccessibilityWindowInfo> windows) {
        if (windows == null) return null;
        try {
            for (AccessibilityWindowInfo window : windows) {
                // Include focused TYPE_SYSTEM windows: OEM DriveModeDialog uses type 2008.
                if (window == null || !window.isFocused()) continue;
                AccessibilityNodeInfo root = window.getRoot();
                if (root == null) continue;
                Node node = new AndroidNode(root);
                try {
                    Node back = findBack(node, new int[]{256}, 0);
                    if (back != null) return new SportAnimationController.Dialog() {
                        public int windowId() { return back.windowId(); }
                        public boolean close() { return back.click(); }
                        public void recycle() { back.recycle(); }
                    };
                } finally { node.recycle(); }
            }
            return null;
        } finally {
            for (AccessibilityWindowInfo window : windows) if (window != null) window.recycle();
        }
    }

    static Node findBack(Node root, int[] remaining, int depth) {
        if (root == null || depth > 24 || remaining[0]-- <= 0
                || !PACKAGE.equals(root.packageName())) return null;
        if (BACK.equals(root.resourceId()) && root.visible() && root.enabled() && root.clickable()) {
            Node parent = root.parent();
            if (parent != null) {
                try {
                    // Both markers must be within the back button's OWN parent and window.
                    if (PACKAGE.equals(parent.packageName()) && parent.windowId() == root.windowId()
                            && contains(parent, VIDEO, root.windowId(), new int[]{256}, 0)
                            && contains(parent, ITEMS, root.windowId(), new int[]{256}, 0)) {
                        // Return a newly obtained child, never the caller-owned root.
                        for (int i = 0; i < parent.childCount(); i++) {
                            Node child = parent.child(i);
                            if (child == null) continue;
                            if (BACK.equals(child.resourceId()) && child.windowId() == root.windowId()
                                    && PACKAGE.equals(child.packageName()) && child.visible()
                                    && child.enabled() && child.clickable()) return child;
                            child.recycle();
                        }
                    }
                } finally { parent.recycle(); }
            }
        }
        for (int i = 0; i < root.childCount(); i++) {
            Node child = root.child(i);
            if (child == null) continue;
            try {
                Node back = findBack(child, remaining, depth + 1);
                if (back != null) return back;
            } finally { child.recycle(); }
        }
        return null;
    }

    private static boolean contains(Node node, String id, int window, int[] remaining, int depth) {
        if (node == null || depth > 24 || remaining[0]-- <= 0
                || node.windowId() != window || !PACKAGE.equals(node.packageName())) return false;
        if (id.equals(node.resourceId())) return true;
        for (int i = 0; i < node.childCount(); i++) {
            Node child = node.child(i);
            if (child == null) continue;
            try { if (contains(child, id, window, remaining, depth + 1)) return true; }
            finally { child.recycle(); }
        }
        return false;
    }

    private static final class AndroidNode implements Node {
        private final AccessibilityNodeInfo node;
        AndroidNode(AccessibilityNodeInfo node) { this.node = node; }
        public String packageName() { return string(node.getPackageName()); }
        public String resourceId() { return node.getViewIdResourceName(); }
        public boolean visible() { return node.isVisibleToUser(); }
        public boolean enabled() { return node.isEnabled(); }
        public boolean clickable() { return node.isClickable(); }
        public int windowId() { return node.getWindowId(); }
        public int childCount() { return node.getChildCount(); }
        public Node child(int i) { AccessibilityNodeInfo n = node.getChild(i); return n == null ? null : new AndroidNode(n); }
        public Node parent() { AccessibilityNodeInfo n = node.getParent(); return n == null ? null : new AndroidNode(n); }
        public boolean click() { return node.performAction(AccessibilityNodeInfo.ACTION_CLICK); }
        public void recycle() { node.recycle(); }
        private static String string(CharSequence text) { return text == null ? null : text.toString(); }
    }
}
