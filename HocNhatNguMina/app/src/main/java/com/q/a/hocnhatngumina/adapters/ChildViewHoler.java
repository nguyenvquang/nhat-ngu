package com.q.a.hocnhatngumina.adapters;

import android.view.View;

/**
 * Created by Nguyen Van Quang on 7/30/2016.
 */
public  class ChildViewHoler {

    View parentView;
    View[] mViews;
    Integer[] mIdViews;

    public ChildViewHoler(View parentView, Integer[] mIdViews) {
        this.parentView = parentView;
        this.mIdViews = mIdViews;
        mViews = new View[mIdViews.length];
        findViews();
    }

    public void findViews() {
        for (int i = 0; i < mIdViews.length; i++) {
            mViews[i] = parentView.findViewById(mIdViews[i]);
        }
    }

    public View getItem(int postion) {
        return mViews[postion];
    }

}
