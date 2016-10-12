package com.q.a.hocnhatngumina.adapters;

import android.app.Activity;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import com.q.a.hocnhatngumina.R;
import com.q.a.hocnhatngumina.models.TuVung;
import com.q.a.hocnhatngumina.models.ViDu;
import com.q.a.hocnhatngumina.utils.DanhSachBaiHoc;
import com.q.a.hocnhatngumina.utils.TextFormat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by Nguyen Van Quang on 8/13/2016.
 */
public class TuVungAdapter extends AbstractBaseAdapter {

    private int mLession;
    private int mClickPosition = -1;
    private TextToSpeech mTextToSpeech;

    public TuVungAdapter(Context mContext, int mLession) {
        this(mContext, R.layout.tu_vung_item, new Integer[]{
                R.id.tvId, R.id.tvKanji, R.id.tvHiragana, R.id.tvKatakana,
                R.id.tvRomaji, R.id.tvMean, R.id.tvBoKanji, R.id.tvGhiChu,
                R.id.tvViDu, R.id.bt_speak
        });
        this.mLession = mLession;
        mTextToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                mTextToSpeech.setLanguage(Locale.JAPANESE);
            }
        });
    }

    public TuVungAdapter(Context mContext, int mLession, ListViewOnItemClickListener mListViewOnItemClickListener) {
        this(mContext, R.layout.tu_vung_item, new Integer[]{
                R.id.tvId, R.id.tvKanji, R.id.tvHiragana, R.id.tvKatakana,
                R.id.tvRomaji, R.id.tvMean, R.id.tvBoKanji, R.id.tvGhiChu,
                R.id.tvViDu, R.id.bt_speak
        }, mListViewOnItemClickListener);
        this.mLession = mLession;
        mTextToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                mTextToSpeech.setLanguage(Locale.JAPANESE);
            }
        });
    }

    public TuVungAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds) {
        super(mContext, mLayoutItemId, mColumIds);
    }

    public TuVungAdapter(Context mContext, int mLayoutItemId, Integer[] mColumIds, ListViewOnItemClickListener mListViewOnItemClickListener) {
        super(mContext, mLayoutItemId, mColumIds, mListViewOnItemClickListener);
    }

    @Override
    public void isShowLoading() {
        isShowLoading = true;

    }

    @Override
    public void changeDataViewHoler(final ChildViewHoler vh, final int position) {

        TuVung tv = new TuVung();
        if (mCursor == null) {
            tv = (TuVung)mDataSource.get(position);
        } else {
            mCursor.moveToPosition(position);
            tv = new TuVung(mCursor);
        }
        if (tv.getKatakana().equals("null"))
        {
            vh.getItem(3).setVisibility(View.GONE);
        }
        else {
            vh.getItem(3).setVisibility(View.VISIBLE);
        }

        if (tv.getKanji().equals("null")) {
            vh.getItem(1).setVisibility(View.GONE);
        } else {
            vh.getItem(1).setVisibility(View.VISIBLE);
        }

        if (tv.getHiragana().equals("null")) {
            vh.getItem(2).setVisibility(View.GONE);
        } else {
            vh.getItem(2).setVisibility(View.VISIBLE);
        }

        if (tv.getRomaji().equals("null") || tv.getRomaji().length() == 0) {
            vh.getItem(4).setVisibility(View.GONE);
        } else {
            vh.getItem(4).setVisibility(View.VISIBLE);
        }

        if (tv.getViDus().size() == 0) {
            vh.getItem(8).setVisibility(View.GONE);
        } else {
            vh.getItem(8).setVisibility(View.VISIBLE);
        }

        if (tv.getBoKanji().equals("null") || tv.getBoKanji().length() == 0) {
            vh.getItem(6).setVisibility(View.GONE);
        } else {
            vh.getItem(6).setVisibility(View.VISIBLE);
        }

        if (tv.getGhiChu().equals("null") || tv.getGhiChu().length() == 0) {
            vh.getItem(7).setVisibility(View.GONE);
        } else {
            vh.getItem(7).setVisibility(View.GONE);
        }



        ((TextView)vh.getItem(1)).setText(TextFormat.textColor(tv.getKanji(), "blue"));
        ((TextView)vh.getItem(2)).setText(TextFormat.textColorDefault(tv.getHiragana()));
        ((TextView)vh.getItem(3)).setText(TextFormat.textColorDefault(tv.getKatakana()));
        ((TextView)vh.getItem(4)).setText(TextFormat.textColorDefault("[ " + tv.getRomaji() + " ]"));
        ((TextView)vh.getItem(5)).setText(TextFormat.textColorItalicBold(tv.getMeanVI()));
        ((TextView)vh.getItem(0)).setText(TextFormat.textUnderline((position + 1 ) + ""));
        ((TextView)vh.getItem(6)).setText(TextFormat.textColorDefault(tv.getBoKanji()));
        ((TextView)vh.getItem(7)).setText(TextFormat.textColorDefault("ghi chu"));
        String viDu = "EX:\n\n";
        if (tv.getViDus().size() > 0) {
            for (int k = 0; k < tv.getViDus().size(); k++) {
                viDu += tv.getViDus().get(k).getVietCau() + "\n";
                viDu += "( " + tv.getViDus().get(k).getDichCau() + " )" + "\n\n";

            }
            ((TextView)vh.getItem(8)).setText(viDu);
        }

        final TuVung finalTv = tv;
        (vh.getItem(4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListViewOnItemClickListener != null) {
//                    mListViewOnItemClickListener.onItemClick(position, vh.parentView);
                    mTextToSpeech.speak(finalTv.getKanji(), TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

    }

    @Override
    public void loadData() {
//        DbHelper db = new DbHelper(mContext);
//        mCursor = db.open().rawQuery("select * from " + TbTuVung.TABLE_NAME, null);
//        ((Activity)mContext).runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                notifyDataSetChanged();
//            }
//        });
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
                JSONObject root = new JSONObject(data);
                JSONObject noiDung = root.getJSONObject("noi_dung");
                JSONArray tuVungs = noiDung.getJSONArray("tu_vung");
                for (int i = 0; i < tuVungs.length(); i++) {
                    JSONObject oi = tuVungs.getJSONObject(i);
                    TuVung tv = new TuVung();
                    tv.setCode(mLession);
                    tv.setId(i);
                    tv.setKanji(oi.getString("kanji").length() == 0 ? "null" : oi.getString("kanji"));
                    tv.setHiragana(oi.getString("hiragana").length() == 0 ? "null":oi.getString("hiragana") );
                    tv.setKatakana(oi.getString("katakana").length() == 0 ? "null" : oi.getString("katakana"));
                    tv.setMeanVI(oi.getString("giai_thich").length() == 0 ? "null" : oi.getString("giai_thich"));
                    tv.setAudioName(oi.getString("audio"));
                    tv.setRomaji("null");
                    tv.setTenBoKanji(oi.getString("bo_kanji"));
                    tv.setBoKanji(oi.getString("bo_kanji"));
                    JSONArray viDus = oi.getJSONArray("vi_du");
                    for (int k = 0; k < viDus.length(); k++) {
                        JSONObject ok = viDus.getJSONObject(k);
                        ViDu vd = new ViDu();
                        vd.setVietCau(ok.getString("viet_cau"));
                        vd.setDichCau(ok.getString("dich_cau"));
                        tv.getViDus().add(vd);
                    }
                    mDataSource.add(tv);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
