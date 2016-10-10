package com.q.a.hocnhatngumina.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Nguyen Van Quang on 8/13/2016.
 */
public class ViewPagerPageAdapter extends FragmentStatePagerAdapter {

    private int mSize;
    private PageLoad mPageLoad;

    public ViewPagerPageAdapter(FragmentManager fm, int mSize, PageLoad mPageLoad) {
        super(fm);
        this.mSize = mSize;
        this.mPageLoad = mPageLoad;
    }

    @Override
    public Fragment getItem(int position) {
        if (mPageLoad != null)
            return mPageLoad.getFragmentItem(position);
        return null;
    }

    @Override
    public int getCount() {
        return mSize;
    }

    public interface PageLoad {
        Fragment getFragmentItem(int position);
    }
}
