package com.q.a.hocnhatngumina.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.q.a.hocnhatngumina.R;
import com.q.a.hocnhatngumina.models.NguPhap;
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
public class NguPhapAdapter extends AbstractBaseAdapter {

    private int mLession;

    public NguPhapAdapter(Context mContext, int mLession) {
        this(mContext, R.layout.item_ngu_phap, new Integer[]{
                R.id.tvTieuDe, R.id.tvYNghia, R.id.tvCachDung, R.id.tvChuY, R.id.tvViDu
        });
        this.mLession = mLession;
    }

    public NguPhapAdapter(Context mContext, ListViewOnItemClickListener mListViewOnItemClickListener) {
        this(mContext, R.layout.item_ngu_phap, new Integer[]{
                R.id.tvTieuDe, R.id.tvYNghia, R.id.tvCachDung, R.id.tvChuY, R.id.tvViDu
        }, mListViewOnItemClickListener);
    }

    public NguPhapAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds) {
        super(mContext, mLayoutItemId, mColumIds);
    }

    public NguPhapAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds, ListViewOnItemClickListener mListViewOnItemClickListener) {
        super(mContext, mLayoutItemId, mColumIds, mListViewOnItemClickListener);
    }

    @Override
    public void isShowLoading() {

        isShowLoading = true;
    }

    @Override
    public void changeDataViewHoler(ChildViewHoler vh, int position) {
        NguPhap np = (NguPhap)mDataSource.get(position);
        if (np.getyNghias().size() == 0) {
            vh.getItem(1).setVisibility(View.GONE);
        } else {
            vh.getItem(1).setVisibility(View.VISIBLE);
        }

        if (np.getChuYs().size() == 0) {
            vh.getItem(3).setVisibility(View.GONE);
        } else {
            vh.getItem(3).setVisibility(View.VISIBLE);
        }

        if (np.getViDus().size() == 0) {
            vh.getItem(4).setVisibility(View.GONE);
        } else {
            vh.getItem(4).setVisibility(View.VISIBLE);
        }
        ((TextView)vh.getItem(0)).setText(TextFormat.textColorDefault(np.getTieuDe()));
        String text = "";
        for (int k = 0; k < np.getyNghias().size(); k++) {
            text += np.getyNghias().get(k) + "<br/>";
        }
        ((TextView)vh.getItem(1)).setText(TextFormat.textColorDefault(text));
        text = "";
        for (int k = 0; k < np.getCachDungs().size(); k++) {
            text += np.getCachDungs().get(k) + "<br/>";
        }
        ((TextView)vh.getItem(2)).setText(TextFormat.textColorDefault(text));
        text = "";
        for (int k = 0; k < np.getChuYs().size(); k++) {
            text += np.getChuYs().get(k) + "<br/>";
        }
        ((TextView)vh.getItem(3)).setText(TextFormat.textColorDefault(text));
        text = "EX:\n\n";
        for (int k = 0; k < np.getViDus().size(); k++) {
            text += np.getViDus().get(k).getVietCau() + "<br/>";
            text += "<font color='blue'>( " + np.getViDus().get(k).getDichCau() + " )</font>" + "<br/>";
        }
        ((TextView)vh.getItem(4)).setText(TextFormat.textColorDefault(text));
    }

    @Override
    public void loadData() {

        read(mLession);
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
                JSONArray nguPhaps = noiDung.getJSONArray("ngu_phap");
                for (int i = 0; i < nguPhaps.length(); i++) {
                    JSONObject oi = nguPhaps.getJSONObject(i);
                    NguPhap np = new NguPhap();
                    np.setTieuDe(oi.getString("tieu_de"));
                    JSONArray yNghias = oi.getJSONArray("y_nghia");
                    for (int k = 0; k < yNghias.length(); k++) {
                        JSONObject ok = yNghias.getJSONObject(k);
                        np.getyNghias().add(ok.getString("yn"));
                    }
                    JSONArray cachDungs = oi.getJSONArray("cach_dung");
                    for (int k = 0; k < cachDungs.length(); k++) {
                        JSONObject ok = cachDungs.getJSONObject(k);
                        np.getCachDungs().add(ok.getString("cd"));
                    }
                    JSONArray chuYs = oi.getJSONArray("chu_y");
                    for (int k = 0; k < chuYs.length(); k++) {
                        JSONObject ok = chuYs.getJSONObject(k);
                        np.getChuYs().add(ok.getString("cy"));
                    }
                    JSONArray viDus = oi.getJSONArray("vi_du");
                    for (int k = 0; k < viDus.length(); k++) {
                        JSONObject ok = viDus.getJSONObject(k);
                        ViDu vd = new ViDu();
                        vd.setVietCau(ok.getString("viet_cau"));
                        vd.setDichCau(ok.getString("dich_cau"));
                        np.getViDus().add(vd);
                    }

                    mDataSource.add(np);
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
