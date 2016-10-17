package com.q.a.hocnhatngumina.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang on 10/13/2016.
 */
public class LuyenNghe {
    private String audio;
    private List<List<ViDu>> noiDungs = new ArrayList<>();
    private List<Boolean> hienThiCauTraLoi = new ArrayList<>();


    public List<Boolean> getHienThiCauTraLoi() {
        return hienThiCauTraLoi;
    }

    public LuyenNghe() {

    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public List<List<ViDu>> getNoiDungs() {
        return noiDungs;
    }

    public void setNoiDungs(List<List<ViDu>> noiDungs) {
        this.noiDungs = noiDungs;
    }


}
