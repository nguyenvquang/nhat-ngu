package com.q.a.hocnhatngumina.models;

import android.database.Cursor;

import com.q.a.hocnhatngumina.database.BaiHocTb;

/**
 * Created by Nguyen Van Quang on 8/4/2016.
 */
public class BaiHoc {

    private int id;
    private String lessionName;
    private String hiragana;
    private String katakana;
    private String meanVI;
    private String meanEN;
    private String romaji;
    private String kanji;
    private String meanJP;
    private int code;

    public BaiHoc() {
    }

    public BaiHoc(Cursor c) {
        if (c != null) {
            setId(c.getInt(c.getColumnIndex(BaiHocTb.COL_ID)));
            setCode(c.getInt(c.getColumnIndex(BaiHocTb.COL_CODE)));
            setKanji(c.getString(c.getColumnIndex(BaiHocTb.COL_KANJI)));
            setRomaji(c.getString(c.getColumnIndex(BaiHocTb.COL_ROMAJI)));
            setMeanVI(c.getString(c.getColumnIndex(BaiHocTb.COL_MEAN_VI)));
            setMeanEN(c.getString(c.getColumnIndex(BaiHocTb.COL_MEAN_EN)));
            setMeanJP(c.getString(c.getColumnIndex(BaiHocTb.COL_MEAN_JP)));
            setLessionName(c.getString(c.getColumnIndex(BaiHocTb.COL_LESSION_NAME)));
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMeanJP() {
        return meanJP;
    }

    public void setMeanJP(String meanJP) {
        this.meanJP = meanJP;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLessionName() {
        return lessionName;
    }

    public void setLessionName(String lessionName) {
        this.lessionName = lessionName;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getKatakana() {
        return katakana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
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


}
