package com.q.a.hocnhatngumina.adapters;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.q.a.hocnhatngumina.R;
import com.q.a.hocnhatngumina.models.LuyenNghe;
import com.q.a.hocnhatngumina.models.NguPhap;
import com.q.a.hocnhatngumina.models.ViDu;
import com.q.a.hocnhatngumina.utils.DanhSachBaiHoc;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang on 10/13/2016.
 */
public class LuyenNgheAdapter extends AbstractBaseAdapter {

    private int mLession;
    public LuyenNgheAdapter(Context mContext, int mLession) {
        this(mContext, R.layout.item_luyen_nghe, new Integer[]{R.id.bt_play, R.id.bt_setting, R.id.tv_status, R.id.tv_time_length, R.id.tvNoiDung, R.id.llContentDynamic});
        this.mLession = mLession;
    }

    public LuyenNgheAdapter(Context mContext, int mLession, ListViewOnItemClickListener mListViewOnItemClickListener) {
        this(mContext, R.layout.item_luyen_nghe, new Integer[]{R.id.bt_play, R.id.bt_setting, R.id.tv_status, R.id.tv_time_length, R.id.tvNoiDung, R.id.llContentDynamic}, mListViewOnItemClickListener);
        this.mLession = mLession;
    }

    public LuyenNgheAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds) {
        super(mContext, mLayoutItemId, mColumIds);
    }

    public LuyenNgheAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds, ListViewOnItemClickListener mListViewOnItemClickListener) {
        super(mContext, mLayoutItemId, mColumIds, mListViewOnItemClickListener);
    }

    @Override
    public void isShowLoading() {

        isShowLoading = true;
    }

    @Override
    public void changeDataViewHoler(ChildViewHoler vh, final int position) {

        LuyenNghe nn = (LuyenNghe)mDataSource.get(position);
        String text = "";
        LinearLayout linearLayout = (LinearLayout)vh.getItem(5);
        for (int i = 0; i < nn.getNoiDungs().size(); i++) {
            text += (i+ 1) + ") ";
            String cau = (i+ 1) + ") ";
            for (int k = 0; k < nn.getNoiDungs().get(i).size(); k++) {
                ViDu vd = nn.getNoiDungs().get(i).get(k);
                text += "<br/>" + vd.getVietCau() + "<br/>";
                text += "<font color='blue'><small>" + vd.getDichCau() + "</small></font><br/>";
                cau += "<br/>" + vd.getVietCau() + "<br/>";
                cau += "<font color='blue'><small>" + vd.getDichCau() + "</small></font><br/>";

            }
            int id = (position + 1) * (i+1);
            View view = vh.parentView.findViewById(id);
            if (view == null) {
                View content = LayoutInflater.from(mContext).inflate(R.layout.item_child_luyen_nghe, null);
                final TextView textView = (TextView)content.findViewById(R.id.tvCau);
                textView.setText(Html.fromHtml(cau));
                Button imageButton = (Button)content.findViewById(R.id.bt_show_answer);
                imageButton.setText("Cau " + (i + 1));
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(mContext, textView.getText().toString(), Toast.LENGTH_SHORT).show();
                        textView.setVisibility(View.VISIBLE);
                    }
                });
                content.setId(id);
//                TextView tv = new TextView(mContext);
//                LinearLayout.LayoutParams tvLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                tvLayoutParams.setMargins(16, 16, 16, 16);
//                tv.setLayoutParams(tvLayoutParams);
//                tv.setText(Html.fromHtml(cau));
//                Button button = new Button(mContext);
//                button.setText("Show");
//                LinearLayout child = new LinearLayout(mContext);
//                child.setOrientation(LinearLayout.VERTICAL);
//                child.addView(tv);
//                child.addView(button);
//                child.setId(id);
                linearLayout.addView(content);

            }

        }
        ((TextView)vh.getItem(4)).setText(Html.fromHtml(text));
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
                JSONArray luyenNghes = noiDung.getJSONArray("luyen_nghe");
                LuyenNghe luyenNghe = new LuyenNghe();
                for (int i = 0; i < luyenNghes.length(); i++) {
                    JSONObject oi = luyenNghes.getJSONObject(i);
                    LuyenNghe nn = new LuyenNghe();
                    nn.setAudio(oi.getString("audio"));
                    JSONArray nds = oi.getJSONArray("noi_dung");
                    List<List<ViDu>> lls = new ArrayList<>();
                    for (int k = 0; k < nds.length(); k++) {
                        JSONArray caus = nds.getJSONObject(k).getJSONArray("cau");
                        List<ViDu> vds = new ArrayList<>();

                        for (int m = 0; m < caus.length(); m++) {

                            JSONObject mi = caus.getJSONObject(m);
                            ViDu vd = new ViDu();
                            vd.setVietCau(mi.getString("viet_cau"));
                            vd.setDichCau(mi.getString("dich_cau"));
                            vds.add(vd);
                        }
                        lls.add(vds);
                    }
                    nn.setNoiDungs(lls);
                    mDataSource.add(nn);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
