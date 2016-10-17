package com.q.a.hocnhatngumina.models;

import java.io.Serializable;

/**
 * Created by Nguyen Van Quang on 10/9/2016.
 */
public class ViDu implements Serializable {

    private String vietCau;
    private String dichCau;

    public ViDu() {
    }

    public ViDu(String vietCau, String dichCau) {
        this.vietCau = vietCau;
        this.dichCau = dichCau;
    }

    public String getVietCau() {
        return vietCau;
    }

    public void setVietCau(String vietCau) {
        this.vietCau = vietCau;
    }

    public String getDichCau() {
        return dichCau;
    }

    public void setDichCau(String dichCau) {
        this.dichCau = dichCau;
    }

    @Override
    public String toString() {
        return "{" +
                "vietCau='" + vietCau + '\'' +
                ", dichCau='" + dichCau + '\'' +
                '}';
    }
}
