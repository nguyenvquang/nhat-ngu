package com.q.a.hocnhatngumina.database;

import android.content.Context;
import android.database.Cursor;

import com.q.a.hocnhatngumina.models.TuVung;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Van Quang on 8/13/2016.
 */
public class TbTuVung {

    public static final String TABLE_NAME = "tu_vung";
    public static final String COL_ID = "_id";
    public static final String COL_LESSION = "lession";
    public static final String COL_KATAKANA = "katakana";
    public static final String COL_HIRAGANA = "hiragana";
    public static final String COL_KANJI = "kanji";
    public static final String COL_ROMAJI = "romaji";
    public static final String COL_MEAN_VI = "mean_vi";
    public static final String COL_MEAN_EN = "mean_en";
    public static final String COL_MEAN_JP = "mean_jp";
    public static final String COL_CODE = "code";
    public static final String COL_AUDIO_NAME = "audio_name";
    public static final String COL_AUDIO_LINK = "audio_link";
    public static final String COL_HAS_EXAMPLE = "has_example";
    public static final String COL_IMAGE = "image";
    public static final String COL_IMAGE_LINK = "image_link";
    public static final String COL_BO_KANJI = "bo_kanji";
    public static final String COL_TEN_BO_KANJI = "ten_bo_kanji";

    public static List<Object> getList(Context context, int lession) {
        List<Object> ls = new ArrayList<>();
        try{
            DbHelper db = new DbHelper(context);
            Cursor c = db.open().rawQuery("select * from " + TABLE_NAME, null);
            if (c != null && c.moveToFirst()) {
                do {
                    TuVung tv = new TuVung();
                    tv.setCursor(c);
//                    Log.i("pp", "" + c.getInt(0));
//                    int exapmple = c.getInt(c.getColumnIndex(ChuCaiTb.COL_HAS_EXAMPLE));
//                    if (exapmple == 1) {
//                        Cursor vd = TableViduAbc.getExampleByCode(context, 1);
//                        if (vd != null && vd.moveToFirst()) {
//                            do {
////                               Log.i("pp", "" + c.getInt(c.getColumnIndex(ChuCaiTb.COL_HAS_EXAMPLE)));
//                                ViDuAbc abc = new ViDuAbc();
//                                abc.setId(vd.getInt(0));
//                                abc.setCode(vd.getInt(1));
//                                abc.setKanji(vd.getString(2));
//                                abc.setHiragana(vd.getString(3));
//                                abc.setMeanVI(vd.getString(4));
//                                abc.setMeanEN(vd.getString(5));
//                                abc.setMeanJP(vd.getString(6));
//                                abc.setRomaji(vd.getString(7));
//                                cc.addVidu(abc);
//                            }while (vd.moveToNext());
//                        }
//                    }

                    ls.add(tv);
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

}
