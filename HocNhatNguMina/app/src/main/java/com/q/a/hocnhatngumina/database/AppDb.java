package com.q.a.hocnhatngumina.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Nguyen Van Quang on 10/16/2016.
 */

public class AppDb extends SQLiteOpenHelper {

    private static final String DB_NAME= "DB_NAME";
    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_BAI_HOC = "CREATE TABLE `tb_bai_hoc` (\n" +
            "\t`_id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`bai_hoc`\tINTEGER,\n" +
            "\t`tu_vung`\tTEXT,\n" +
            "\t`ngu_phap`\tTEXT,\n" +
            "\t`hoi_thoai`\tTEXT,\n" +
            "\t`luyen_nghe`\tTEXT,\n" +
            "\t`bai_tap`\tTEXT,\n" +
            "\t`luyen_doc`\tTEXT,\n" +
            "\t`kanji`\tTEXT,\n" +
            "\t`tham_khao`\tTEXT,\n" +
            "\t`kiem_tra`\tTEXT,\n" +
            "\t`da_qua`\tBLOB\n" +
            ");";
    public static final String TB_BAI_HOC = "tb_bai_hoc";
    public static final int COL_ID = 0;
    public static final int COL_BAI_HOC = 1;
    public static final int COL_TU_VUNG = 2;
    public static final int COL_NGU_PHAP = 3;
    public static final int COL_HOI_THOAI = 4;
    public static final int COL_LUYEN_NGHE = 5;
    public static final int COL_BAI_TAP = 6;
    public static final int COL_LUYEN_DOC = 7;
    public static final int COL_KANJI = 8;
    public static final int COL_THAM_KHAO = 9;
    public static final int COL_KIEM_TRA = 10;
    public static final int COL_DA_QUA = 11;

    public AppDb(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("cre", "true");
        sqLiteDatabase.execSQL(CREATE_TABLE_BAI_HOC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertTbBaiHoc(ContentValues contentValues) {
        this.getWritableDatabase().insert(TB_BAI_HOC, null, contentValues);
    }

    public void updateTbBaiHoc(int baiHoc, ContentValues contentValues ) {
        this.getWritableDatabase().update(TB_BAI_HOC, contentValues, COL_BAI_HOC + "=" + baiHoc, null);
    }

    public Cursor queryTbBaiHoc(int baiHoc) {
        return this.getReadableDatabase().rawQuery("select * from " + TB_BAI_HOC, null);
    }
}
