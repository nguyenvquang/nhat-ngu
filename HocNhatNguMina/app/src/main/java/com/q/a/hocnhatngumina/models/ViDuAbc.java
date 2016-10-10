package com.q.a.hocnhatngumina.models;

import android.database.Cursor;

import com.q.a.hocnhatngumina.database.TableViduAbc;

import java.io.Serializable;

/**
 * Created by Nguyen Van Quang on 8/8/2016.
 */
public class ViDuAbc implements Serializable {

    private int id;
    private int code;
    private String kanji;
    private String hiragana;
    private String meanVI;
    private String meanEN;
    private String meanJP;
    private Cursor mCursor;
    private String romaji;

    public ViDuAbc() {
        this(-1, -1, "null", "null", "null", "null", "null", "null");
    }

    public ViDuAbc(int id, int code, String kanji, String hiragana, String romaji, String meanVI, String meanEN, String meanJP) {
        this.romaji = romaji;
        this.id = id;
        this.code = code;
        this.kanji = kanji;
        this.hiragana = hiragana;
        this.meanVI = meanVI;
        this.meanEN = meanEN;
        this.meanJP = meanJP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getMeanVI() {
        return meanVI;
    }

    public void setMeanVI(String meanVI) {
        this.meanVI = meanVI;
    }

    public String getMeanEN() {
        return meanEN;
    }

    public void setMeanEN(String meanEN) {
        this.meanEN = meanEN;
    }

    public String getMeanJP() {
        return meanJP;
    }

    public void setMeanJP(String meanJP) {
        this.meanJP = meanJP;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public void setmCursor(Cursor cursor) {
        this.mCursor = cursor;
        if (mCursor != null && mCursor.moveToFirst()) {
            setId(mCursor.getInt(mCursor.getColumnIndex(TableViduAbc.COL_ID)));
            setCode(mCursor.getInt(mCursor.getColumnIndex(TableViduAbc.COL_CODE)));
            setKanji(mCursor.getString(mCursor.getColumnIndex(TableViduAbc.COL_KANJI)));
            setHiragana(mCursor.getString(mCursor.getColumnIndex(TableViduAbc.COL_HIRAGANA)));
//            setRomaji(mCursor.getString(mCursor.getColumnIndex(TableViduAbc.COL_ROMAJI)));
            setMeanVI(mCursor.getString(mCursor.getColumnIndex(TableViduAbc.COL_MEAN_VI)));
            setMeanEN(mCursor.getString(mCursor.getColumnIndex(TableViduAbc.COL_MEAN_EN)));
            setMeanJP(mCursor.getString(mCursor.getColumnIndex(TableViduAbc.COL_MEAN_JP)));
        }
    }
}
