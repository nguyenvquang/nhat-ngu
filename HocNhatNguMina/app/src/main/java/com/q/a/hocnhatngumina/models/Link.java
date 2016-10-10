package com.q.a.hocnhatngumina.models;

import android.database.Cursor;

import com.q.a.hocnhatngumina.database.TbLink;

/**
 * Created by Nguyen Van Quang on 8/14/2016.
 */
public class Link {

    private int id = 0;
    private int type =0;
    private int lession = 0;
    private String urlLink = "null";
    private int status = 0;
    private String name = "null";


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLession() {
        return lession;
    }

    public void setLession(int lession) {
        this.lession = lession;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCursor(Cursor c) {
        if (c != null && c.moveToFirst()) {
            setId(c.getInt(c.getColumnIndex(TbLink.COL_ID)));
            setLession(c.getInt(c.getColumnIndex(TbLink.COL_LESSION)));
            setType(c.getInt(c.getColumnIndex(TbLink.COL_TYPE)));
            setUrlLink(c.getString(c.getColumnIndex(TbLink.COL_URL_LINK)));
            setName(c.getString(c.getColumnIndex(TbLink.COL_NAME)));
            setStatus(c.getInt(c.getColumnIndex(TbLink.COL_STATUS)));
        }
    }
}
