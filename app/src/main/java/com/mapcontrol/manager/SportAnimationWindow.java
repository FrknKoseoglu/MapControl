package com.mapcontrol.manager;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import java.util.List;
import java.util.function.Consumer;

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

    static SportAnimationController.Dialog find(AccessibilityService service, Consumer<String> diagnostic) {
        return findInWindows(service.getWindows(),diagnostic);
    }

    static SportAnimationController.Dialog findInWindows(List<AccessibilityWindowInfo> windows) {
        return findInWindows(windows,null);
    }

    private static SportAnimationController.Dialog findInWindows(List<AccessibilityWindowInfo> windows, Consumer<String> diagnostic) {
        if (windows == null) {
            if(diagnostic!=null) diagnostic.accept("getWindows=null");
            return null;
        }
        Probe probe=new Probe();
        probe.windows=windows.size();
        try {
            for (AccessibilityWindowInfo window : windows) {
                // Include focused TYPE_SYSTEM windows: OEM DriveModeDialog uses type 2008.
                if(window!=null && probe.types.length()<160) probe.types.append(window.getId()).append(":").append(window.getType())
                        .append(window.isFocused()?"F":"-").append(window.isActive()?"A":"-").append(" ");
                if (window == null || !window.isFocused()) continue;
                probe.focused++;
                AccessibilityNodeInfo root = window.getRoot();
                if (root == null) { probe.nullRoots++; continue; }
                if(probe.packages.length()<160) probe.packages.append(root.getPackageName()).append(" ");
                Node node = new AndroidNode(root);
                try {
                    Node back = findBack(node, new int[]{256}, 0, probe);
                    if (back != null) { probe.matched=true; return new SportAnimationController.Dialog() {
                        public int windowId() { return back.windowId(); }
                        public boolean close() { return back.click(); }
                        public void recycle() { back.recycle(); }
                    }; }
                } finally { node.recycle(); }
            }
            return null;
        } finally {
            if(diagnostic!=null) diagnostic.accept(probe.toString());
            for (AccessibilityWindowInfo window : windows) if (window != null) window.recycle();
        }
    }

    private static final class Probe {
        int windows,focused,nullRoots,visited,back,video,items;
        boolean matched;
        String backState="not-found";
        final java.util.Set<String> names=new java.util.LinkedHashSet<>();
        final StringBuilder types=new StringBuilder(),packages=new StringBuilder();
        public String toString(){return "windows="+windows+",focused="+focused+",nullRoots="+nullRoots
                +",nodes="+visited+",ids="+back+"/"+video+"/"+items+",back="+backState+",match="+matched
                +",packages="+packages+",seenIds="+names+",types="+types;}
    }

    static Node findBack(Node root, int[] remaining, int depth) { return findBack(root,remaining,depth,null); }

    private static Node findBack(Node root, int[] remaining, int depth, Probe probe) {
        if(probe!=null && root!=null) {
            probe.visited++;
            String id=root.resourceId();
            if(id!=null && id.startsWith(PACKAGE+":id/") && probe.names.size()<12) {
                String name=id.substring((PACKAGE+":id/").length());
                probe.names.add(name.length()>24?name.substring(0,24):name);
            }
            if(BACK.equals(id)) {
                probe.back++;
                probe.backState="visible="+root.visible()+"/enabled="+root.enabled()+"/clickable="+root.clickable();
            }
            if(VIDEO.equals(root.resourceId()))probe.video++;
            if(ITEMS.equals(root.resourceId()))probe.items++;
        }
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
                Node back = findBack(child, remaining, depth + 1, probe);
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
