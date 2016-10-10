package com.q.a.hocnhatngumina.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.q.a.hocnhatngumina.models.BaiHoc;
import com.q.a.hocnhatngumina.models.ChuCai;
import com.q.a.hocnhatngumina.models.ViDuAbc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Van Quang on 8/4/2016.
 */
public class DbQuery {


    public static List<Object> getLessionList(Context context) {
        List<Object> ls = new ArrayList<>();
        try{
            DbHelper db = new DbHelper(context);
            Cursor c = db.open().rawQuery("select * from " + BaiHocTb.TABLE_NAME, null);
            if (c != null && c.moveToFirst()) {
                do {
                    BaiHoc bh = new BaiHoc();

                    bh.setId(c.getInt(c.getColumnIndex(BaiHocTb.COL_ID)));
                    bh.setCode(c.getInt(c.getColumnIndex(BaiHocTb.COL_CODE)));
//                    Log.i("pp", c.getInt(c.getColumnIndex(BaiHocTb.COL_CODE)) + "");
                    bh.setLessionName(c.getString(c.getColumnIndex(BaiHocTb.COL_LESSION_NAME)));
                    bh.setKanji(c.getString(c.getColumnIndex(BaiHocTb.COL_KANJI)));
                    bh.setRomaji(c.getString(c.getColumnIndex(BaiHocTb.COL_ROMAJI)));
                    bh.setMeanVI(c.getString(c.getColumnIndex(BaiHocTb.COL_MEAN_VI)));
                    bh.setMeanJP(c.getString(c.getColumnIndex(BaiHocTb.COL_MEAN_JP)));
                    ls.add(bh);
                }while (c.moveToNext());
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            while (true){
                ex.printStackTrace();
            }
        }
        finally {
            return ls;
        }
    }

    public static List<Object> getChuCaiList(Context context) {
        List<Object> ls = new ArrayList<>();
        try{
            DbHelper db = new DbHelper(context);
            Cursor c = db.open().rawQuery("select * from " + ChuCaiTb.TABLE_NAME, null);
            if (c != null && c.moveToFirst()) {
                do {
                    ChuCai cc = new ChuCai();
                    cc.setCursor(c);
                    int exapmple = c.getInt(c.getColumnIndex(ChuCaiTb.COL_HAS_EXAMPLE));
                   if (exapmple == 1) {
                       Cursor vd = TableViduAbc.getExampleByCode(context, 1);
                       if (vd != null && vd.moveToFirst()) {
                           do {
//                               Log.i("pp", "" + c.getInt(c.getColumnIndex(ChuCaiTb.COL_HAS_EXAMPLE)));
                               ViDuAbc abc = new ViDuAbc();
                               abc.setId(vd.getInt(0));
                               abc.setCode(vd.getInt(1));
                               abc.setKanji(vd.getString(2));
                               abc.setHiragana(vd.getString(3));
                               abc.setMeanVI(vd.getString(4));
                               abc.setMeanEN(vd.getString(5));
                               abc.setMeanJP(vd.getString(6));
                               abc.setRomaji(vd.getString(7));
                               cc.addVidu(abc);
                           }while (vd.moveToNext());
                       }
                   }

                    ls.add(cc);
                }while (c.moveToNext());
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            while (true){
                ex.printStackTrace();
            }
        }
        finally {
            return ls;
        }
    }

    public static void insert(String tabel, ContentValues values) {

    }
}
