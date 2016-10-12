package com.q.a.hocnhatngumina.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Nguyen Van Quang on 8/4/2016.
 */
public abstract class AbstractBaseAdapter extends BaseAdapter  {
    protected Context mContext;
    private int mLayoutItemId;
    private Integer[] mColumIds;
    protected ListViewOnItemClickListener mListViewOnItemClickListener = null;
    protected boolean isShowLoading = false;
    protected Cursor mCursor = null;
    protected int mAdapterSize = 0;
    protected ArrayList mDataSource = new ArrayList();

    public AbstractBaseAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public AbstractBaseAdapter(Context mContext, ListViewOnItemClickListener mListViewOnItemClickListener) {
        this.mContext = mContext;
        this.mListViewOnItemClickListener = mListViewOnItemClickListener;
    }

    public AbstractBaseAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds) {
        this.mContext = mContext;
        this.mLayoutItemId = mLayoutItemId;
        this.mColumIds = mColumIds;
        isShowLoading();
        loadTask();
    }

    public AbstractBaseAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds, ListViewOnItemClickListener mListViewOnItemClickListener) {
        this.mContext = mContext;
        this.mLayoutItemId = mLayoutItemId;
        this.mColumIds = mColumIds;
        this.mListViewOnItemClickListener = mListViewOnItemClickListener;
        isShowLoading();
        loadTask();
    }

    @Override
    public int getCount() {
        if (mCursor != null) mAdapterSize = mCursor.getCount();
        if (mDataSource.size() > 0 && mCursor == null) {
            mAdapterSize = mDataSource.size();
        }
        return mAdapterSize;
    }

    @Override
    public Object getItem(int position) {
        if (mCursor != null)
        return mCursor.moveToPosition(position);
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            ChildViewHoler vh = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(mLayoutItemId, null);
                vh = new ChildViewHoler(convertView, mColumIds);
                convertView.setTag(vh);
            } else {
                vh = (ChildViewHoler)convertView.getTag();
            }
            changeDataViewHoler(vh, position);
        } catch (Exception e) {
            while (true) {
                e.printStackTrace();
            }
        }
        return convertView;
    }

    private void loadTask() {
        new AsyncTask<Void, Void, Void>() {
            ProgressDialog pd;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                if (isShowLoading)
                pd = ProgressDialog.show(mContext, null, null);
            }

            @Override
            protected Void doInBackground(Void... params) {
                loadData();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                updateUI();
                notifyDataSetChanged();
                if (pd != null && pd.isShowing()) pd.cancel();
            }
        }.execute();
    }

    public abstract void isShowLoading();
    public abstract void changeDataViewHoler(ChildViewHoler vh, int position);

    public abstract void loadData();

    public interface ListViewOnItemClickListener {
        void onItemClick(int postion, View view);
    }

    public void updateUI() {

    }




}
