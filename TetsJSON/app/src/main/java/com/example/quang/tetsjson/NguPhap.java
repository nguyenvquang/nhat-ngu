package com.example.quang.tetsjson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang on 10/10/2016.
 */
public class NguPhap {

    private String tieuDe;
    private List<String> yNghias;
    private List<String> cachDungs;
    private List<String> chuYs;
    private List<ViDu> viDus;

    public NguPhap() {
        tieuDe = "";
        yNghias = new ArrayList<>();
        cachDungs = new ArrayList<>();
        chuYs = new ArrayList<>();
        viDus = new ArrayList<>();
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public List<String> getyNghias() {
        return yNghias;
    }

    public void setyNghias(List<String> yNghias) {
        this.yNghias = yNghias;
    }

    public List<String> getCachDungs() {
        return cachDungs;
    }

    public void setCachDungs(List<String> cachDungs) {
        this.cachDungs = cachDungs;
    }

    public List<String> getChuYs() {
        return chuYs;
    }

    public void setChuYs(List<String> chuYs) {
        this.chuYs = chuYs;
    }

    public List<ViDu> getViDus() {
        return viDus;
    }

    public void setViDus(List<ViDu> viDus) {
        this.viDus = viDus;
    }
}
