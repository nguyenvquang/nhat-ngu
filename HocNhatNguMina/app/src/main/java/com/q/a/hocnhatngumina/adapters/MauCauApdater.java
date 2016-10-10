package com.q.a.hocnhatngumina.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.q.a.hocnhatngumina.R;

/**
 * Created by Nguyen Van Quang on 8/18/2016.
 */
public class MauCauApdater extends BaseAdapter {

    private Context mContext;
    private String[] mData;
    private ListView mListView;
    private static boolean[] mVisibility;

    public MauCauApdater(Context mContext, String[] mData, ListView mListView) {
        this.mContext = mContext;
        this.mData = mData;
        this.mListView = mListView;
        mVisibility = new boolean[mData.length];
    }

    @Override
    public int getCount() {
        return mData.length;
    }

    @Override
    public Object getItem(int position) {
        return mData[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHoler vh = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.ls_item_1, null);
            vh = new ViewHoler(convertView);
            vh.tv1 = (TextView)convertView.findViewById(R.id.tv_1);
            vh.tv2 = (TextView)convertView.findViewById(R.id.tv_2);
            vh.cb = (CheckBox)convertView.findViewById(R.id.cb_1);
            convertView.setTag(vh);

        } else {
            vh = (ViewHoler)convertView.getTag();
        }

        vh.tv1.setText(mData[position]);
        final ViewHoler finalVh = vh;
        if (!mVisibility[position]) {
            vh.tv2.setVisibility(View.VISIBLE);
        } else {
            vh.tv2.setVisibility(View.GONE);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mVisibility[position]) {
                    finalVh.tv2.setVisibility(View.GONE);
                    mVisibility[position] = true;
                    Toast.makeText(mContext,mVisibility[position] + ": "+ position + ":" + mListView.getFirstVisiblePosition() + ":" + mListView.getLastVisiblePosition(), Toast.LENGTH_SHORT).show();
                }


//
            }
        });
        return convertView;
    }

    static class ViewHoler {
        TextView tv1;
        TextView tv2;
        CheckBox cb;

        ViewHoler(View v) {
//            tv1 = (TextView)v.findViewById(R.id.tv_1);
//            tv2 = (TextView)v.findViewById(R.id.tv_2);
//            cb = (CheckBox)v.findViewById(R.id.cb_1);
        }
    }
}
