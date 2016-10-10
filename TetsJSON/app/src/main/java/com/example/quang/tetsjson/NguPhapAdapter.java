package com.example.quang.tetsjson;

import android.content.Context;
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
public class NguPhapAdapter extends BaseAdapter {

    private Context mContext;
    private List<NguPhap> mData;

    public NguPhapAdapter(Context mContext) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.item_ngu_phap, null);
            vh = new ViewHoler(view);
            view.setTag(vh);
        } else {
            vh = (ViewHoler)view.getTag();
        }
        NguPhap np = mData.get(i);
        if (np.getyNghias().size() == 0) {
            vh.yNghiaTextView.setVisibility(View.GONE);
        } else {
            vh.yNghiaTextView.setVisibility(View.VISIBLE);
        }

        if (np.getChuYs().size() == 0) {
            vh.chuYTextView.setVisibility(View.GONE);
        } else {
            vh.chuYTextView.setVisibility(View.VISIBLE);
        }

        if (np.getViDus().size() == 0) {
            vh.viDuTextView.setVisibility(View.GONE);
        } else {
            vh.viDuTextView.setVisibility(View.VISIBLE);
        }

        vh.tieuDeTextView.setText(np.getTieuDe());
        String text = "";
        for (int k = 0; k < np.getyNghias().size(); k++) {
            text += np.getyNghias().get(k) + "\n";
        }
        vh.yNghiaTextView.setText(text);
        text = "";
        for (int k = 0; k < np.getCachDungs().size(); k++) {
            text += np.getCachDungs().get(k) + "\n";
        }
        vh.cachDungTextView.setText(text);
        text = "";
        for (int k = 0; k < np.getChuYs().size(); k++) {
            text += np.getChuYs().get(k) + "\n";
        }
        vh.chuYTextView.setText(text);
        text = "EX:\n\n";
        for (int k = 0; k < np.getViDus().size(); k++) {
            text += np.getViDus().get(k).getVietCau() + "\n";
            text += "( " + np.getViDus().get(k).getDichCau() + " )" + "\n";
        }
        vh.viDuTextView.setText(text);
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
                Log.i("txt", cp);
            }
            isr.close();
            JSONObject rootJsonObject = new JSONObject(text);
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
                mData.add(np);
                notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class ViewHoler {
        TextView tieuDeTextView;
        TextView yNghiaTextView;
        TextView cachDungTextView;
        TextView chuYTextView;
        TextView viDuTextView;

        ViewHoler(View v) {
            tieuDeTextView = (TextView)v.findViewById(R.id.tvTieuDe);
            yNghiaTextView = (TextView)v.findViewById(R.id.tvYNghia);
            cachDungTextView = (TextView)v.findViewById(R.id.tvCachDung);
            chuYTextView = (TextView)v.findViewById(R.id.tvChuY);
            viDuTextView = (TextView)v.findViewById(R.id.tvViDu);
        }
    }
}
