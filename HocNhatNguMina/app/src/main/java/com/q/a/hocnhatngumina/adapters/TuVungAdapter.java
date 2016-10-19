package com.q.a.hocnhatngumina.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import com.q.a.hocnhatngumina.R;
import com.q.a.hocnhatngumina.database.AppDb;
import com.q.a.hocnhatngumina.models.TuVung;
import com.q.a.hocnhatngumina.utils.DanhSachBaiHoc;
import com.q.a.hocnhatngumina.utils.JsonParser;
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
    private TextToSpeech mTextToSpeech;
    private String mString = "";

    public TuVungAdapter(Context mContext, int mLession) {
        this(mContext, R.layout.tu_vung_item, new Integer[]{
                R.id.tvId, R.id.tvKanji, R.id.tvHiragana, R.id.tvKatakana,
                R.id.tvRomaji, R.id.tvMean, R.id.tvBoKanji, R.id.tvGhiChu,
                R.id.tvViDu, R.id.bt_speak
        });
        this.mLession = mLession;
        mTextToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
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
            public void onInit(int i) {
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
        (vh.getItem(9)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListViewOnItemClickListener != null) {

                }
                String text = finalTv.getKanji();
                if (finalTv.getKanji().length() == 0) text = finalTv.getHiragana();
                if (finalTv.getHiragana().length() == 0) text = finalTv.getKatakana();
                mTextToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        ((TextView)vh.getItem(1)).setText(TextFormat.textColor(mString, "blue"));

    }

    @Override
    public void loadData() {
        read(mLession);
    }

    private void read(int baiHocSo) {
        String name = DanhSachBaiHoc.layTenBaiHoc(baiHocSo);
        if (name != null) {
            try {
                String data = "";
                AppDb appDb = new AppDb(mContext);
                Cursor cursor = appDb.queryTbBaiHoc(mLession);
//                if (cursor == null || (cursor != null && cursor.moveToFirst() && cursor.getString(AppDb.COL_TU_VUNG) == null)) {
                    InputStream inputStream = mContext.getAssets().open("data/data.txt");
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    int length = 0;
                    char[] buffer = new char[4096];
                    while ((length = inputStreamReader.read(buffer)) > -1) {
                        String cp = String.copyValueOf(buffer, 0, length);
                        data += cp;
                        buffer = new char[4096];
                    }
                    inputStreamReader.close();
                    JSONObject jsonObject = new JSONObject(data);
                    JSONObject noiDung = jsonObject.getJSONObject("noi_dung");
                    JSONObject tuVungs = noiDung.getJSONObject("tu_vung");
                    JSONArray tvs = tuVungs.getJSONArray("tvs");
                    ContentValues values = new ContentValues();
                    values.put("tu_vung", tvs.toString());
                    values.put("bai_hoc", mLession);
                    appDb.getWritableDatabase().insert(AppDb.TB_BAI_HOC, null, values);
                    cursor = appDb.queryTbBaiHoc(mLession);
//                }

                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        data = cursor.getString(AppDb.COL_TU_VUNG);
                    } while (cursor.moveToNext());
                }
                mString = data;
                mDataSource = JsonParser.getTuVungList(data);

            } catch (Exception e) {
             e.printStackTrace();
            }
        }
    }

}
