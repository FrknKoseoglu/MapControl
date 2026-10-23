package com.desaysv.vehiclesetting;
import android.content.*;
import android.database.*;
import android.net.Uri;
/** Read-only fixture observation; no writable provider endpoints. */
public final class StateProvider extends ContentProvider {
    public boolean onCreate(){return true;}
    public Cursor query(Uri u,String[] p,String s,String[] a,String o){
        MatrixCursor c=new MatrixCursor(new String[]{"mode","closes","token"});
        SharedPreferences prefs=getContext().getSharedPreferences("fixture",0);
        c.addRow(new Object[]{prefs.getInt("mode",-1),prefs.getInt("closes",0),prefs.getInt("token",0)});return c;
    }
    public String getType(Uri u){return "vnd.android.cursor.item/sportfixture";}
    public Uri insert(Uri u,ContentValues v){throw new UnsupportedOperationException();}
    public int update(Uri u,ContentValues v,String s,String[] a){throw new UnsupportedOperationException();}
    public int delete(Uri u,String s,String[] a){throw new UnsupportedOperationException();}
}
