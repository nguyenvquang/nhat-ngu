package com.q.a.hocnhatngumina.models;

import android.database.Cursor;

import com.q.a.hocnhatngumina.database.TbTuVung;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Van Quang on 8/13/2016.
 */
public class TuVung implements Serializable {
    private int id = 0;
    private int code = 0;
    private int lession = 0;
    private String kanji = "null";
    private String hiragana = "null";
    private String katakana = "null";
    private String meanVI = "null";
    private String meanEN = "null";
    private String meanJP = "null";
    private String audioName = "null";
    private List<ViDu> viDus = new ArrayList<>();
    private String ghiChu = "null";

    public TuVung() {
    }

    public TuVung(Cursor cursor) {
       setCursor(cursor);
    }

    public String getAudioLink() {
        return audioLink;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    private String audioLink = "null";
    private String image = "null";
    private String imageLink = "null";
    private String boKanji = "null";
    private String tenBoKanji = "null";
    private int hasExample = 0;
    private String romaji;

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

    public int getLession() {
        return lession;
    }

    public void setLession(int lession) {
        this.lession = lession;
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

    public String getMeanJP() {
        return meanJP;
    }

    public void setMeanJP(String meanJP) {
        this.meanJP = meanJP;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getBoKanji() {
        return boKanji;
    }

    public void setBoKanji(String boKanji) {
        this.boKanji = boKanji;
    }

    public String getTenBoKanji() {
        return tenBoKanji;
    }

    public void setTenBoKanji(String tenBoKanji) {
        this.tenBoKanji = tenBoKanji;
    }

    public int getHasExample() {
        return hasExample;
    }

    public void setHasExample(int hasExample) {
        this.hasExample = hasExample;
    }

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public void setCursor(Cursor c) {
        if (c != null) {
                setId(c.getInt(c.getColumnIndex(TbTuVung.COL_ID)));
                setCode(c.getInt(c.getColumnIndex(TbTuVung.COL_CODE)));
                setLession(c.getInt(c.getColumnIndex(TbTuVung.COL_LESSION)));
                setKanji(c.getString(c.getColumnIndex(TbTuVung.COL_KANJI)));
                setHiragana(c.getString(c.getColumnIndex(TbTuVung.COL_HIRAGANA)));
                setKatakana(c.getString(c.getColumnIndex(TbTuVung.COL_KATAKANA)));
                setRomaji(c.getString(c.getColumnIndex(TbTuVung.COL_ROMAJI)));
                setMeanVI(c.getString(c.getColumnIndex(TbTuVung.COL_MEAN_VI)));
                setMeanEN(c.getString(c.getColumnIndex(TbTuVung.COL_MEAN_EN)));
                setMeanJP(c.getString(c.getColumnIndex(TbTuVung.COL_MEAN_JP)));
                setBoKanji(c.getString(c.getColumnIndex(TbTuVung.COL_BO_KANJI)));
                setTenBoKanji(c.getString(c.getColumnIndex(TbTuVung.COL_TEN_BO_KANJI)));
                setAudioName(c.getString(c.getColumnIndex(TbTuVung.COL_AUDIO_NAME)));
               setAudioLink(c.getString(c.getColumnIndex(TbTuVung.COL_AUDIO_LINK)));
               setImage(c.getString(c.getColumnIndex(TbTuVung.COL_IMAGE)));
               setImageLink(c.getString(c.getColumnIndex(TbTuVung.COL_IMAGE_LINK)));
               setHasExample(c.getInt(c.getColumnIndex(TbTuVung.COL_HAS_EXAMPLE)));
        }
    }

    public List<ViDu> getViDus() {
        return viDus;
    }

    public void setViDus(List<ViDu> viDus) {
        this.viDus = viDus;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "{" +
                "'id'=" + id +
                ", 'code'=" + code +
                ", 'lession'=" + lession +
                ", 'kanji'='" + kanji + '\'' +
                ", 'hiragana'='" + hiragana + '\'' +
                ", 'katakana'='" + katakana + '\'' +
                ", 'meanVI'='" + meanVI + '\'' +
                ", 'meanEN'='" + meanEN + '\'' +
                ", 'meanJP'='" + meanJP + '\'' +
                ", 'audioName'='" + audioName + '\'' +
                ", 'viDus'=" + viDus +
                ", 'ghiChu'='" + ghiChu + '\'' +
                ", 'audioLink'='" + audioLink + '\'' +
                ", 'image'='" + image + '\'' +
                ", 'imageLink'='" + imageLink + '\'' +
                ", 'boKanji'='" + boKanji + '\'' +
                ", 'tenBoKanji'='" + tenBoKanji + '\'' +
                ", 'hasExample'=" + hasExample +
                ", 'romaji'='" + romaji + '\'' +
                '}';
    }
}
