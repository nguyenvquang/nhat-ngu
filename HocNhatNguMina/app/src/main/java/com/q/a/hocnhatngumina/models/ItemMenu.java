package com.q.a.hocnhatngumina.models;

/**
 * Created by Nguyen Van Quang on 10/7/2016.
 */
public class ItemMenu {

    private int icon;
    private String title;

    public ItemMenu(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
