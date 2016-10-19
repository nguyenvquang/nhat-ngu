package com.q.a.hocnhatngumina.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.q.a.hocnhatngumina.R;
import com.q.a.hocnhatngumina.models.ItemMenu;

/**
 * Created by Nguyen Van Quang on 10/7/2016.
 */
public class MenuAdapter extends AbstractBaseAdapter {

    public MenuAdapter(Context mContext) {
        this(mContext, R.layout.item_menu, new Integer[]{R.id.icon, R.id.title});
    }

    public MenuAdapter(Context mContext, ListViewOnItemClickListener mListViewOnItemClickListener) {
        this(mContext, R.layout.item_menu, new Integer[]{R.id.icon, R.id.title}, mListViewOnItemClickListener);
    }

    public MenuAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds) {
        super(mContext, mLayoutItemId, mColumIds);
    }

    public MenuAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds, ListViewOnItemClickListener mListViewOnItemClickListener) {
        super(mContext, mLayoutItemId, mColumIds, mListViewOnItemClickListener);
    }

    @Override
    public void isShowLoading() {

    }

    @Override
    public void changeDataViewHoler(final ChildViewHoler vh, final int position) {
        vh.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListViewOnItemClickListener != null) {
                    mListViewOnItemClickListener.onItemClick(position, v);
                }
            }
        });
        ItemMenu item = (ItemMenu)mDataSource.get(position);
        ImageView iv = (ImageView)vh.getItem(0);
        iv.setImageResource(item.getIcon());
        TextView tv = (TextView)vh.getItem(1);
        tv.setText(item.getTitle());
    }

    @Override
    public void loadData() {
        mDataSource.add(new ItemMenu(R.drawable.ic_menu_bang_chu_cai, "Bảng chữ cái"));
        mDataSource.add(new ItemMenu(R.drawable.ic_menu_bai_hoc_mina, "Bài học cơ bản"));
        mDataSource.add(new ItemMenu(R.drawable.ic_menu_bo_thu_co_ban, "Các bộ thủ cơ bản"));
        mDataSource.add(new ItemMenu(R.drawable.ic_menu_kanji_co_ban, "Chữ Kanji cơ bản"));
        mDataSource.add(new ItemMenu(R.drawable.ic_menu_mau_cau, "Mẫu câu hay dùng"));
        mDataSource.add(new ItemMenu(R.drawable.ic_menu_ghi_nho, "Ghi nhớ"));
        mDataSource.add(new ItemMenu(R.drawable.ic_menu_van_hoa_nhat, "Văn hóa Nhật Bản"));
        mDataSource.add(new ItemMenu(R.drawable.ic_menu_jlpt, "Kỳ thi JLPT"));
        mDataSource.add(new ItemMenu(R.drawable.ic_menu_thong_bao, "Thông báo"));
    }
}
