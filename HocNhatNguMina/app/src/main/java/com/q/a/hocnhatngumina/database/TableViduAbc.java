package com.q.a.hocnhatngumina.database;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Nguyen Van Quang on 8/8/2016.
 */
public class TableViduAbc {

    public static final String TABLE_NAME = "vi_du_abc";
    public static final String COL_ID = "_id";
    public static final String COL_CODE = "code";
    public static final String COL_HIRAGANA = "hiragana";
    public static final String COL_KANJI = "kanji";
    public static final String COL_ROMAJI = "romaji";
    public static final String COL_MEAN_VI = "mean_vi";
    public static final String COL_MEAN_EN = "mean_en";
    public static final String COL_MEAN_JP = "mean_jp";


    public static String[] getColumns() {
        return new String[]{
                COL_ID, COL_CODE, COL_KANJI, COL_HIRAGANA, COL_ROMAJI, COL_MEAN_VI, COL_MEAN_EN, COL_MEAN_JP
        };
    }

    public static Cursor getExampleByCode(Context context, int code) {
        DbHelper db = new DbHelper(context);
        return db.open().rawQuery("select * from " + TABLE_NAME + " where code=" + code, null);
    }

}
