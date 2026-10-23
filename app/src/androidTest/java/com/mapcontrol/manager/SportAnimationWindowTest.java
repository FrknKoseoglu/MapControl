package com.mapcontrol.manager;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SportAnimationWindowTest {
    static class Node implements SportAnimationWindow.Node {
        String pkg=SportAnimationWindow.PACKAGE,id;
        boolean visible=true,enabled=true,clickable=true;
        int window=3,clicks;
        Node parent;
        final List<Node> children=new ArrayList<>();
        Node(String id) { this.id=id; }
        Node add(Node child) { children.add(child); child.parent=this; return this; }
        public String packageName(){return pkg;}
        public String resourceId(){return id;}
        public boolean visible(){return visible;}
        public boolean enabled(){return enabled;}
        public boolean clickable(){return clickable;}
        public int windowId(){return window;}
        public int childCount(){return children.size();}
        public Node child(int i){return children.get(i);}
        public Node parent(){return parent;}
        public boolean click(){clicks++;return true;}
        public void recycle(){}
    }
    static Node fixture(){return new Node(null).add(new Node(SportAnimationWindow.VIDEO))
            .add(new Node(null).add(new Node(SportAnimationWindow.ITEMS)))
            .add(new Node(SportAnimationWindow.BACK));}
    static SportAnimationWindow.Node match(Node root){return SportAnimationWindow.findBack(root,new int[]{256},0);}
    @Test public void exactOemDialogMatchesOnlyItsOwnBackButton(){
        Node root=fixture(); SportAnimationWindow.Node close=match(root);
        assertSame(root.children.get(2),close); assertEquals(0,root.children.get(2).clicks);
        close.click(); assertEquals(1,root.children.get(2).clicks);
    }
    @Test public void sameBackIdOnAnotherOemPageOrAnotherPackageIsIgnored(){
        assertNull(match(new Node(null).add(new Node(SportAnimationWindow.BACK))));
        Node root=fixture(); root.pkg="other.app";assertNull(match(root));
    }
    @Test public void videoAndListMustBothBeInTheBackButtonsOwnContainer(){
        Node root=fixture();root.children.get(2).parent=new Node(null);assertNull(match(root));
        root=fixture();root.children.get(0).id="other_video";assertNull(match(root));
        root=fixture();root.children.get(1).children.get(0).id="other_list";assertNull(match(root));
    }
    @Test public void hiddenDisabledOrNonClickableBackIsNeverChosen(){
        Node root=fixture();root.children.get(2).visible=false;assertNull(match(root));
        root=fixture();root.children.get(2).enabled=false;assertNull(match(root));
        root=fixture();root.children.get(2).clickable=false;assertNull(match(root));
    }
    @Test public void markersFromAnotherWindowOrPackageCannotAuthorizeClick(){
        Node root=fixture();root.children.get(0).window=9;assertNull(match(root));
        root=fixture();root.children.get(0).pkg="other.app";assertNull(match(root));
    }
    @Test public void malformedCyclicTreeIsBounded(){
        Node root=new Node(null);root.add(root);assertNull(match(root));
    }
}
