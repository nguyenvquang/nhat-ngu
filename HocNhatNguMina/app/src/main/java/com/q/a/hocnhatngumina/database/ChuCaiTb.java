package com.q.a.hocnhatngumina.database;

import android.content.ContentValues;
import android.content.Context;

import com.q.a.hocnhatngumina.models.ChuCai;

/**
 * Created by Nguyen Van Quang on 8/6/2016.
 */
public class ChuCaiTb {

    public static final String TABLE_NAME = "chu_cai_abc";
    public static final String COL_ID = "_id";
    public static final String COL_CODE = "code";
    public static final String COL_HIRAGANA = "hiragana";
    public static final String COL_KATAKANA = "katakana";
    public static final String COL_ROMAJI = "romaji";
    public static final String COL_AUDIO_NAME = "audio_name";
    public static final String COL_AUDIO_LINK = "audio_link";
    public static final String COL_HAS_EXAMPLE = "has_example";
    public static final String COL_IMAGE_HIRAGANA_LINK = "image_hiragana_link";
    public static final String COL_IMAGE_KATAKANA_LINK = "image_katakana_link";

    private Context mContext;
    private DbHelper mDbHelper;

    public ChuCaiTb(Context mContext) {
        this.mContext = mContext;
        mDbHelper = new DbHelper(mContext);
    }

    public static String[] getColumns() {
        return new String[]{
          COL_ID, COL_CODE, COL_HIRAGANA, COL_KATAKANA, COL_ROMAJI, COL_AUDIO_NAME, COL_AUDIO_LINK, COL_HAS_EXAMPLE, COL_IMAGE_HIRAGANA_LINK, COL_IMAGE_KATAKANA_LINK
        };
    }

    public void insert(ChuCai chuCai) {

    }

    public void update(ChuCai cc) {
        ContentValues contentValues = new ContentValues();
       if (cc.getCode() > -1) {
           contentValues.put(COL_CODE, cc.getCode());
       }
        if (!cc.getHiragana().equals("null")) contentValues.put(COL_HIRAGANA, cc.getHiragana());
        if (!cc.getKatakana().equals("null")) contentValues.put(COL_KATAKANA, cc.getKatakana());
        if (!cc.getAudioLink().equals("null")) contentValues.put(COL_AUDIO_LINK, cc.getAudioLink());
        if (!cc.getAudioName().equals("null")) contentValues.put(COL_AUDIO_NAME, cc.getAudioName());
        if (!cc.getRomaji().equals("null")) contentValues.put(COL_ROMAJI, cc.getRomaji());
        if (!cc.getImageHiragana().equals("null")) contentValues.put(COL_IMAGE_HIRAGANA_LINK, cc.getImageHiragana());
        if (!cc.getImageKatakana().equals("null")) contentValues.put(COL_IMAGE_KATAKANA_LINK, cc.getImageKatakana());
        if (cc.getHasExample() == 1) contentValues.put(COL_HAS_EXAMPLE, cc.getHasExample());
        mDbHelper.open().update(TABLE_NAME, contentValues, COL_ID + "=" + cc.getId(), null);
    }
}
