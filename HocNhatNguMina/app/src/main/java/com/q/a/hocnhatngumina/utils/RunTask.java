package com.q.a.hocnhatngumina.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Nguyen Van Quang on 8/12/2016.
 */
public abstract class RunTask extends AsyncTask {

    private ProgressDialog mProgressDialog;
    private boolean isShowProgrogressDialog;
    private Context mContext;

    public RunTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (isShowProgrogressDialog) {
            mProgressDialog = ProgressDialog.show(mContext, null, null);
        }
    }

    @Override
    protected Object doInBackground(Object[] params) {
        load();
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        if (isShowProgrogressDialog) {
            if (mProgressDialog.isShowing()) mProgressDialog.cancel();
        }
    }

    public void start(){
        this.execute();
    }

    public void start(boolean isShowProgrogressDialog){
        this.isShowProgrogressDialog = isShowProgrogressDialog;
        this.execute();
    }

    public abstract void load();
}
