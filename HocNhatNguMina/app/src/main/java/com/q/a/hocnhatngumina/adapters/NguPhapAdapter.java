package com.q.a.hocnhatngumina.adapters;

import android.content.Context;
import android.text.Html;
import android.widget.TextView;

import com.q.a.hocnhatngumina.R;
import com.q.a.hocnhatngumina.models.NguPhap;
import com.q.a.hocnhatngumina.utils.DanhSachBaiHoc;
import com.q.a.hocnhatngumina.utils.JsonParser;
import com.q.a.hocnhatngumina.utils.TextFormat;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Nguyen Van Quang on 10/10/2016.
 */
public class NguPhapAdapter extends AbstractBaseAdapter {

    private int mLession;

    public NguPhapAdapter(Context mContext, int mLession) {
        this(mContext, R.layout.item_ngu_phap, new Integer[]{ R.id.tvNoiDung      });
        this.mLession = mLession;
    }

    public NguPhapAdapter(Context mContext, ListViewOnItemClickListener mListViewOnItemClickListener) {
        this(mContext, R.layout.item_ngu_phap, new Integer[]{ R.id.tvNoiDung }, mListViewOnItemClickListener);
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

        String text = "";
        text += TextFormat.textSColorBold(np.getTieuDe() + "<br/>");
        if (np.getyNghias().size() > 0) {
            text += TextFormat.textSUnderline("<br/>Ý nghĩa:<br/>");
            for (int k = 0; k < np.getyNghias().size(); k++) {
                text += "<br/>" + TextFormat.textSColorDefault(np.getyNghias().get(k)) + "<br/>";
            }
        }
        if (np.getCachDungs().size() > 0) {
            text += TextFormat.textSUnderline("<br/>Cách dùng:<br/>");
            for (int k = 0; k < np.getCachDungs().size(); k++) {
                text += "<br/>" + TextFormat.textSColorDefault(np.getCachDungs().get(k)) + "<br/>";
            }
        }

       if (np.getChuYs().size() > 0) {
           text += TextFormat.textSUnderline("<br/>Chú ý:<br/>");
           for (int k = 0; k < np.getChuYs().size(); k++) {
               text += "<br/>" + TextFormat.textSColorDefault(np.getChuYs().get(k)) + "<br/>";
           }
       }
        if (np.getViDus().size() > 0) {
            text += TextFormat.textSUnderline("<br/>Ví dụ:<br/>");
            for (int k = 0; k < np.getViDus().size(); k++) {
                text += "<br/>&#8226; " + TextFormat.textSColorDefault(np.getViDus().get(k).getVietCau()) + "<br/>";
                text += "<font color='blue'><small>( " + np.getViDus().get(k).getDichCau() + " )</small></font>" + "<br/>";
            }
        }
        ((TextView)vh.getItem(0)).setText(Html.fromHtml(text));
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
                mDataSource = JsonParser.getNguPhapList(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
