package com.q.a.hocnhatngumina.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.q.a.hocnhatngumina.R;
import com.q.a.hocnhatngumina.TuVungListFragment;
import com.q.a.hocnhatngumina.database.BaiHocTb;
import com.q.a.hocnhatngumina.database.DbHelper;
import com.q.a.hocnhatngumina.models.BaiHoc;
import com.q.a.hocnhatngumina.utils.Constants;

/**
 * Created by Nguyen Van Quang on 8/4/2016.
 */
public class DanhSachBaiHocAdapter extends AbstractBaseAdapter {

    public DanhSachBaiHocAdapter(Context mContext){
        this(mContext, R.layout.ls_item_1, new Integer[]{R.id.tv_1, R.id.tv_2, R.id.cb_1, null});
    }

    public DanhSachBaiHocAdapter(Context mContext, ListViewOnItemClickListener mListViewOnItemClickListener){
        this(mContext, R.layout.ls_item_1, new Integer[]{R.id.tv_1, R.id.tv_2, R.id.cb_1}, mListViewOnItemClickListener);
    }


    public DanhSachBaiHocAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds) {
        super(mContext, mLayoutItemId, mColumIds);
    }

    public DanhSachBaiHocAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds, ListViewOnItemClickListener mListViewOnItemClickListener) {
        super(mContext, mLayoutItemId, mColumIds, mListViewOnItemClickListener);
    }

    @Override
    public void isShowLoading() {
        isShowLoading = true;
    }

    @Override
    public void changeDataViewHoler(ChildViewHoler vh, final int position) {
        mCursor.moveToPosition(position);
        BaiHoc bh = new BaiHoc(mCursor);
        ((TextView)vh.getItem(0)).setText(bh.getLessionName());
        ((TextView)vh.getItem(1)).setText(bh.getCode() + "");
        vh.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListViewOnItemClickListener != null) {
                           mListViewOnItemClickListener.onItemClick(position, v);
//                    final TuVungListFragment tuVungListFragment  = new TuVungListFragment();
//                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
//                    SharedPreferences.Editor editor = sp.edit();
//                    editor.putInt(Constants.BAI_HOC_SO, position + 1);
//                    editor.commit();
//                    ((Activity)mContext).runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            notifyDataSetChanged();
//                            ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, tuVungListFragment).commit();
//
//                        }
//                    });

                }
            }
        });

    }

    @Override
    public void loadData() {
//        mDataSource = DbQuery.getLessionList(mContext);
            DbHelper dbb = new DbHelper(mContext);
            mCursor = dbb.open().query(BaiHocTb.TABLE_NAME, BaiHocTb.getColumns(), null, null, null, null, null);

//            ((Activity)mContext).runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    notifyDataSetChanged();
//                }
//            });
//
    }

    @Override
    public void updateUI() {
        super.updateUI();
        notifyDataSetChanged();
    }
}
