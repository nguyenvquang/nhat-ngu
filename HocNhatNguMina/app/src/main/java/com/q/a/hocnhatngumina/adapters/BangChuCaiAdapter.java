package com.q.a.hocnhatngumina.adapters;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.q.a.hocnhatngumina.R;
import com.q.a.hocnhatngumina.models.ChuCai;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nguyen Van Quang on 7/30/2016.
 */
public class BangChuCaiAdapter extends AbstractBaseAdapter implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener,
        SeekBar.OnSeekBarChangeListener, MediaPlayer.OnSeekCompleteListener {

    private boolean isHiragana;
    private List<List<ChuCai>> mData = new ArrayList<>();
    private int[] mItemSize = new int[0];
    private MediaPlayer mMediaPlayer;
    private TextToSpeech mTextToSpeech;


    public BangChuCaiAdapter(Context mContext, boolean isHiragana) {
        super(mContext, R.layout.row_item_bang_chu_cai, new Integer[]{
                R.id.card_1_tv_1, R.id.card_1_tv_2,
                R.id.card_2_tv_1, R.id.card_2_tv_2,
                R.id.card_3_tv_1, R.id.card_3_tv_2,
                R.id.card_4_tv_1, R.id.card_4_tv_2,
                R.id.card_5_tv_1, R.id.card_5_tv_2,
                R.id.card_1, R.id.card_2,R.id.card_3, R.id.card_4, R.id.card_5,
                R.id.ll_card_1, R.id.ll_card_2, R.id.ll_card_3, R.id.ll_card_4, R.id.ll_card_5
        });
        this.isHiragana = isHiragana;
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnSeekCompleteListener(this);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.reset();
        mTextToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                mTextToSpeech.setLanguage(Locale.JAPANESE);
            }
        });
    }

    public BangChuCaiAdapter(Context mContext, boolean isHiragana,  ListViewOnItemClickListener mListViewOnItemClickListener) {
        super(mContext, R.layout.row_item_bang_chu_cai, new Integer[]{
                R.id.card_1_tv_1, R.id.card_1_tv_2,
                R.id.card_2_tv_1, R.id.card_2_tv_2,
                R.id.card_3_tv_1, R.id.card_3_tv_2,
                R.id.card_4_tv_1, R.id.card_4_tv_2,
                R.id.card_5_tv_1, R.id.card_5_tv_2,
                R.id.card_1, R.id.card_2, R.id.card_3, R.id.card_4, R.id.card_5,
                R.id.ll_card_1, R.id.ll_card_2, R.id.ll_card_3, R.id.ll_card_4, R.id.ll_card_5
        }, mListViewOnItemClickListener);
        this.isHiragana = isHiragana;
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnSeekCompleteListener(this);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.reset();
        mTextToSpeech = new TextToSpeech(mContext, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                mTextToSpeech.setLanguage(Locale.JAPANESE);
            }
        });
    }


    @Override
    public void isShowLoading() {
    }



    @Override
    public void changeDataViewHoler(ChildViewHoler vh, final int position) {

        final List<ChuCai> ls = mData.get(position);
        for(int i = 0; i < ls.size(); i++) {
            ChuCai cc = ls.get(i);
            if (isHiragana)
            ((TextView)vh.getItem(i * 2)).setText(cc.getHiragana());
            else ((TextView)vh.getItem(i * 2)).setText(cc.getKatakana());
            ((TextView)vh.getItem(i*2 + 1)).setText(cc.getRomaji());
        }
        if (mItemSize[position] == 5) {
            vh.getItem(10).setVisibility(View.VISIBLE);
            vh.getItem(11).setVisibility(View.VISIBLE);
            vh.getItem(12).setVisibility(View.VISIBLE);
            vh.getItem(13).setVisibility(View.VISIBLE);
            vh.getItem(14).setVisibility(View.VISIBLE);
        } else if (mItemSize[position] == 3) {
            vh.getItem(10).setVisibility(View.VISIBLE);
            vh.getItem(11).setVisibility(View.VISIBLE);
            vh.getItem(12).setVisibility(View.VISIBLE);
            vh.getItem(13).setVisibility(View.GONE);
            vh.getItem(14).setVisibility(View.GONE);
        } else if (mItemSize[position] == 2) {
            vh.getItem(10).setVisibility(View.VISIBLE);
            vh.getItem(11).setVisibility(View.VISIBLE);
            vh.getItem(12).setVisibility(View.GONE);
            vh.getItem(13).setVisibility(View.GONE);
            vh.getItem(14).setVisibility(View.GONE);
        } else if (mItemSize[position] == 1) {
            vh.getItem(10).setVisibility(View.VISIBLE);
            vh.getItem(11).setVisibility(View.GONE);
            vh.getItem(12).setVisibility(View.GONE);
            vh.getItem(13).setVisibility(View.GONE);
            vh.getItem(14).setVisibility(View.GONE);
        }

        vh.getItem(15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextToSpeech.speak(ls.get(0).getHiragana(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        vh.getItem(16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextToSpeech.speak(ls.get(1).getHiragana(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        vh.getItem(17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextToSpeech.speak(ls.get(2).getHiragana(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        vh.getItem(18).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextToSpeech.speak(ls.get(3).getHiragana(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        vh.getItem(19).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextToSpeech.speak(ls.get(4).getHiragana(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }

    @Override
    public void loadData() {

        try {
            InputStream is = mContext.getAssets().open("data/bang_chu_cai.txt");
            InputStreamReader isr = new InputStreamReader(is);
            String jsonText = "";
            int length = 0;
            char[] buffer = new char[4096];
            while ((length = isr.read(buffer)) > 0) {
                jsonText += String.copyValueOf(buffer, 0, length);
                buffer = new char[4096];
            }
            isr.close();
            is.close();
            Log.i("ccc", jsonText);
            final String finalJsonText = jsonText;
            JSONObject jsonObject = new JSONObject(jsonText);
            JSONArray a1 = jsonObject.getJSONArray("json");
            mItemSize = new int[a1.length()];
            for (int i = 0; i < a1.length(); i++) {
                JSONObject oi = a1.getJSONObject(i);
                JSONArray a2 = oi.getJSONArray("items");
                List<ChuCai> ls = new ArrayList<>();
                for (int k = 0; k < a2.length(); k++) {
                    JSONObject ki = a2.getJSONObject(k);
                    ChuCai cc = new ChuCai();
                    cc.setHiragana(ki.getString("hiragana"));
                    cc.setKatakana(ki.getString("katakana"));
                    cc.setRomaji(ki.getString("romaji"));
                    cc.setAudioName(ki.getString("audio_name"));
                    cc.setAudioLink(ki.getString("audio_link"));
                    ls.add(cc);
                }
                mData.add(ls);
                mItemSize[i] = ls.size();

//                Log.i("ccs", "" + ls.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((Activity)mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapterSize = mData.size();
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {

    }
}
