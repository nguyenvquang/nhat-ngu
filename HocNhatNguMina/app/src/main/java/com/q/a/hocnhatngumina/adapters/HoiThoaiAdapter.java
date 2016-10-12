package com.q.a.hocnhatngumina.adapters;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.q.a.hocnhatngumina.R;
import com.q.a.hocnhatngumina.models.HoiThoai;
import com.q.a.hocnhatngumina.models.ViDu;
import com.q.a.hocnhatngumina.utils.DanhSachBaiHoc;
import com.q.a.hocnhatngumina.utils.TextFormat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Nguyen Van Quang on 10/10/2016.
 */
public class HoiThoaiAdapter extends AbstractBaseAdapter {

    private int mLession;
    private boolean mShowMean = false;

    public HoiThoaiAdapter(Context mContext, int mLession) {
        this(mContext, R.layout.item_hoi_thoai, new Integer[]{R.id.tvNhanVat, R.id.tvNoiDung});
        this.mLession = mLession;
    }

    public HoiThoaiAdapter(Context mContext, ListViewOnItemClickListener mListViewOnItemClickListener) {
        this(mContext, R.layout.item_hoi_thoai, new Integer[]{R.id.tvNhanVat, R.id.tvNoiDung}, mListViewOnItemClickListener);
    }

    public HoiThoaiAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds) {
        super(mContext, mLayoutItemId, mColumIds);
    }

    public HoiThoaiAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds, ListViewOnItemClickListener mListViewOnItemClickListener) {
        super(mContext, mLayoutItemId, mColumIds, mListViewOnItemClickListener);
    }

    @Override
    public void isShowLoading() {
        isShowLoading = true;
    }

    @Override
    public void changeDataViewHoler(ChildViewHoler vh, int position) {

        HoiThoai ht = (HoiThoai)mDataSource.get(position);
        ((TextView)vh.getItem(0)).setText(TextFormat.textColor(ht.getNhanVat(), "red"));
        String text = "";
        for (int k = 0; k < ht.getNoiDungs().size(); k++) {
            text += "<br/>" + ht.getNoiDungs().get(k).getVietCau() + "<br/>";
            if (mShowMean)
            text += "<font color='blue'><small>( "+ ht.getNoiDungs().get(k).getDichCau() + " )</small></font>" + "<br/>";
        }
        ((TextView)vh.getItem(1)).setText(TextFormat.textColorDefault(text));
    }

    @Override
    public void loadData() {

        read(mLession);
    }

    public void setmShowMean() {
        if (mShowMean) mShowMean = false;
        else  mShowMean = true;
    }

    private void read(int baiHocSo) {
        String name = DanhSachBaiHoc.layTenBaiHoc(baiHocSo);
        if (name != null) {
            try {
                InputStream inputStream = mContext.getAssets().open("data/data.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                int length = 0;
                char[] buffer = new char[4096];
                String data = "";
                while ((length = inputStreamReader.read(buffer)) > -1) {
                    String cp = String.copyValueOf(buffer, 0, length);
                    data += cp;
                    buffer = new char[4096];
                }
                inputStreamReader.close();
                JSONObject rootJsonObject = new JSONObject(data);
                JSONObject noiDung = rootJsonObject.getJSONObject("noi_dung");
                JSONArray hoiThoais = noiDung.getJSONArray("hoi_thoai");
                for (int i = 0; i < hoiThoais.length(); i++) {
                    JSONObject oi = hoiThoais.getJSONObject(i);

                    HoiThoai ht = new HoiThoai();

                    ht.setNhanVat(oi.getString("nhan_vat"));

                    JSONArray nds = oi.getJSONArray("noi_dung");
                    for (int k = 0; k < nds.length(); k++) {
                        JSONObject ok = nds.getJSONObject(k);
                        ViDu vd = new ViDu();
                        vd.setVietCau(ok.getString("viet_cau"));
                        vd.setDichCau(ok.getString("dich_cau"));
                        ht.getNoiDungs().add(vd);
                    }
                    mDataSource.add(ht);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
