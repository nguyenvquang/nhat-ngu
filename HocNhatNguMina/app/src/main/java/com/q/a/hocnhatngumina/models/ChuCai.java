package com.q.a.hocnhatngumina.models;

import android.database.Cursor;

import com.q.a.hocnhatngumina.database.ChuCaiTb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Van Quang on 7/30/2016.
 */
public class ChuCai implements Serializable {

    private int id;
    private int code;
    private String hiragana;
    private String katakana;
    private String romaji;
    private String audioName;
    private String audioLink;
    private int hasExample;
    private String imageHiragana;
    private String imageKatakana;
    private Cursor cursor;
    private List<ViDuAbc> viduAbcList;

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

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public int getHasExample() {
        return hasExample;
    }

    public void setHasExample(int hasExample) {
        this.hasExample = hasExample;
    }

    public ChuCai() {
        this(-1, -1, "null", "null", "null", "null", "null", 0, "null", "null");
      viduAbcList = new ArrayList<>();
    }

    public String getImageHiragana() {
        return imageHiragana;
    }

    public void setImageHiragana(String imageHiragana) {
        this.imageHiragana = imageHiragana;
    }

    public String getImageKatakana() {
        return imageKatakana;
    }

    public void setImageKatakana(String imageKatakana) {
        this.imageKatakana = imageKatakana;
    }

    public ChuCai(int id, int code, String hiragana, String katakana, String romaji, String audioName, String audioLink, int hasExample, String imageHiragana, String imageKatakana) {
        this.id = id;
        this.code = code;
        this.hiragana = hiragana;
        this.katakana = katakana;
        this.romaji = romaji;
        this.audioName = audioName;
        this.audioLink = audioLink;
        this.hasExample = hasExample;
        this.imageHiragana = imageHiragana;
        this.imageKatakana = imageKatakana;
        viduAbcList = new ArrayList<>();
    }

    public void setCursor(Cursor c) {
        this.cursor = c;
        if (cursor != null) {
            setId(cursor.getInt(cursor.getColumnIndex(ChuCaiTb.COL_ID)));
            setCode(cursor.getInt(cursor.getColumnIndex(ChuCaiTb.COL_CODE)));
            setHiragana(cursor.getString(cursor.getColumnIndex(ChuCaiTb.COL_HIRAGANA)));
            setKatakana(cursor.getString(cursor.getColumnIndex(ChuCaiTb.COL_KATAKANA)));
            setRomaji(cursor.getString(cursor.getColumnIndex(ChuCaiTb.COL_ROMAJI)));
            setAudioLink(cursor.getString(cursor.getColumnIndex(ChuCaiTb.COL_AUDIO_LINK)));
            setAudioName(cursor.getString(cursor.getColumnIndex(ChuCaiTb.COL_AUDIO_NAME)));
        }

    }

    public List<ViDuAbc> getViduAbcList() {
        return viduAbcList;
    }

    public void setViduAbcList(Cursor c) {
        if (c != null && c.moveToFirst()) {
            do {
                ViDuAbc vd = new ViDuAbc();
                vd.setmCursor(c);
                viduAbcList.add(vd);
            } while (c.moveToNext());
        }

    }

    public void addVidu(ViDuAbc abc) {
        viduAbcList.add(abc);
    }
}
