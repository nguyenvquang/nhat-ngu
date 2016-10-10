package com.q.a.hocnhatngumina.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.q.a.hocnhatngumina.models.Link;

/**
 * Created by Nguyen Van Quang on 8/14/2016.
 */
public class TbLink {

    public static final String TABLE_NAME = "link";
    public static final String COL_ID = "_id";
    public static final String COL_LESSION = "lession";
    public static final String COL_TYPE = "type";
    public static final String COL_URL_LINK = "url_link";
    public static final String COL_NAME = "name";
    public static final String COL_STATUS = "status";

    public static Link getItem(Context context, int lession, int type) {
        DbHelper db = new DbHelper(context);
        if (db.isHasDatabaseFile()) {
            Cursor c = db.open().rawQuery("select * from " + TABLE_NAME + " where " + COL_LESSION + "=" + lession + " AND " + COL_TYPE + "=" + type, null);
            if (c != null && c.moveToFirst()) {
                Link link = new Link();
                link.setCursor(c);
                return link;
            }
        }
        return null;
    }

    public static void update(Context context, int lession, int type,  String name, int status) {
        DbHelper db = new DbHelper(context);
        if (db.isHasDatabaseFile()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_NAME, name);
            contentValues.put(COL_STATUS, status);
            db.open().update(TABLE_NAME, contentValues, COL_LESSION + "=" + lession + " and " + COL_TYPE + "=" + type, null);
        }
    }
}
