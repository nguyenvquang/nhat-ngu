package com.q.a.hocnhatngumina.database;

/**
 * Created by Nguyen Van Quang on 8/4/2016.
 */
public class BaiHocTb {

    public static final String TABLE_NAME = "bai_hoc";
    public static final String COL_ID = "_id";
    public static final String COL_LESSION_NAME = "lession_name";
    public static final String COL_KANJI = "kanji";
    public static final String COL_ROMAJI = "romaji";
    public static final String COL_MEAN_VI = "mean_vi";
    public static final String COL_MEAN_EN = "mean_en";
    public static final String COL_MEAN_JP = "mean_jp";
    public static final String COL_CODE = "code";


    public static String[] getColumns() {

        return new String[]{COL_ID, COL_CODE, COL_LESSION_NAME, COL_KANJI, COL_ROMAJI, COL_MEAN_VI, COL_MEAN_EN, COL_MEAN_JP};
    }

}
