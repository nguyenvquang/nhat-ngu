package com.example.quang.tetsjson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang on 10/10/2016.
 */
public class HoiThoai {

    private String nhanVat;
    private List<ViDu> noiDungs;

    public HoiThoai() {
        nhanVat = "";
        noiDungs = new ArrayList<>();
    }

    public String getNhanVat() {
        return nhanVat;
    }

    public void setNhanVat(String nhanVat) {
        this.nhanVat = nhanVat;
    }

    public List<ViDu> getNoiDungs() {
        return noiDungs;
    }

    public void setNoiDungs(List<ViDu> noiDungs) {
        this.noiDungs = noiDungs;
    }
}
