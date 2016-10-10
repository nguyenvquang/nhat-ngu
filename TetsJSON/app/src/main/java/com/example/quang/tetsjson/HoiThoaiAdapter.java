package com.example.quang.tetsjson;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quang on 10/10/2016.
 */
public class HoiThoaiAdapter extends BaseAdapter {

    private Context mContext;
    private List<HoiThoai> mData;

    public HoiThoaiAdapter(Context mContext) {
        this.mContext = mContext;
        this.mData = new ArrayList<>();
        load();

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoler vh = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_hoi_thoai, null);
            vh = new ViewHoler(view);
            view.setTag(vh);
        } else {
            vh = (ViewHoler)view.getTag();
        }
        HoiThoai ht = mData.get(i);
        vh.nhanVatTextView.setText(ht.getNhanVat());
        String text = "";
        for (int k = 0; k < ht.getNoiDungs().size(); k++) {
            text += ht.getNoiDungs().get(k).getVietCau() + "<br/>";
            text += "<font color='blue'>"+ ht.getNoiDungs().get(k).getDichCau() + "</font>" + "<br/>";
        }
        vh.noiDungTextView.setText(Html.fromHtml(text));

        return view;
    }

    private void load() {
        try {
            InputStream is = mContext.getAssets().open("bai1.txt");
            String text = "";
            char[] charRead = new char[4096];
            InputStreamReader isr = new InputStreamReader(is);
            int length = 0;

            while ((length = isr.read(charRead)) > -1) {
                String cp = String.copyValueOf(charRead, 0, length);
                text += cp;
                charRead = new char[4096];
//                Log.i("txt", cp);
            }
            isr.close();
            JSONObject rootJsonObject = new JSONObject(text);
            JSONObject noiDung = rootJsonObject.getJSONObject("noi_dung");
            JSONArray hoiThoais = noiDung.getJSONArray("hoi_thoai");
            for (int i = 0; i < hoiThoais.length(); i++) {
                JSONObject oi = hoiThoais.getJSONObject(i);

               HoiThoai ht = new HoiThoai();

                ht.setNhanVat(oi.getString("nhan_vat"));

                JSONArray nds = oi.getJSONArray("noi_dung");
//                Log.i("ss", "" + nds.length());
                for (int k = 0; k < nds.length(); k++) {
                    JSONObject ok = nds.getJSONObject(k);
                    ViDu vd = new ViDu();
                    vd.setVietCau(ok.getString("viet_cau"));
                    vd.setDichCau(ok.getString("dich_cau"));
                    ht.getNoiDungs().add(vd);
                }
                mData.add(ht);
                notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ViewHoler {
        TextView nhanVatTextView;
        TextView noiDungTextView;

        ViewHoler(View v) {
            nhanVatTextView = (TextView)v.findViewById(R.id.tvNhanVat);
            noiDungTextView = (TextView)v.findViewById(R.id.tvNoiDung);

        }
    }
}
