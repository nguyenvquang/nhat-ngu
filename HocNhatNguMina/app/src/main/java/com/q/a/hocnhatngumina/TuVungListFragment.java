package com.q.a.hocnhatngumina;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.q.a.hocnhatngumina.adapters.AbstractBaseAdapter;
import com.q.a.hocnhatngumina.adapters.TuVungAdapter;
import com.q.a.hocnhatngumina.database.TbLink;
import com.q.a.hocnhatngumina.models.Link;
import com.q.a.hocnhatngumina.utils.Constants;
import com.q.a.hocnhatngumina.utils.DownloadFile;
import com.q.a.hocnhatngumina.utils.FileMn;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class TuVungListFragment extends Fragment implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener,
        SeekBar.OnSeekBarChangeListener, MediaPlayer.OnSeekCompleteListener {

    private int mLessionId = 1;
    private Context mContext;
    private ImageButton mPauseButton;
    private ImageButton mPlayButton;
    private TextView mStatusPlay;
    private TextView mLengPlay;
    private SeekBar mSeekBar;
    private static MediaPlayer mMediaPlayer;
    private boolean mIsPlay;
    private Handler mHandler;
    private int mCount = 0;
    private LinearLayout mFrameLayout;
    private boolean mIsPause;
    private ListView mListView;
    private int mBaiHocSo = 1;

    public TuVungListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaiHocSo = PreferenceManager.getDefaultSharedPreferences(getContext()).getInt(Constants.BAI_HOC_SO, 1);
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnSeekCompleteListener(this);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.reset();
        mHandler = new Handler();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_tu_vung_list, container, false);
        ((BaiHocMinaActivity)getActivity()).changeTitle(getContext().getResources().getString(R.string.bai_hoc_so) + " " + mBaiHocSo);
        ListView lv = (ListView)rootView.findViewById(R.id.list_view);
        ((BaiHocMinaActivity)getActivity()).showMenu(true);
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.media_item, null);
        mPauseButton = (ImageButton)headerView.findViewById(R.id.bt_pause);
        mPlayButton = (ImageButton)headerView.findViewById(R.id.bt_play);
        setupListener();
        lv.addHeaderView(headerView);
        lv.setAdapter(new TuVungAdapter(getContext(), mBaiHocSo));
        mListView = lv;
        return rootView;
    }

    private AbstractBaseAdapter.ListViewOnItemClickListener mListViewOnItemClickListener = new AbstractBaseAdapter.ListViewOnItemClickListener() {
        @Override
        public void onItemClick(int postion, View view) {

            Toast.makeText(getContext(), "H", Toast.LENGTH_SHORT).show();
        }
    };

    TextToSpeech textToSpeech ;
    private void setupListener() {
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsPause) {
                    mMediaPlayer.start();
                    mPlayButton.setVisibility(View.GONE);
                    mPauseButton.setVisibility(View.VISIBLE);
                    mIsPause = false;
                    return;
                }
                Link link = TbLink.getItem(getContext(), mLessionId, Constants.TYPE_KOTOBA);
                if (FileMn.isFileExists(Constants.PATH_AUDIO_KOTOBA + "/" + link.getName())) {
                    mMediaPlayer.reset();
                    try {
                        mMediaPlayer.setDataSource(FileMn.getPathFile(Constants.PATH_AUDIO_KOTOBA + "/" + link.getName()));
                        mMediaPlayer.prepareAsync();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    String name = link.getUrlLink().hashCode() + "";
                    DownloadFile.start(getContext(), link.getUrlLink().trim(), Constants.PATH_AUDIO_KOTOBA, name);
                    TbLink.update(getContext(), mLessionId, Constants.TYPE_KOTOBA, name, 1);
                    return;
                }
                mPlayButton.setVisibility(View.GONE);
                mPauseButton.setVisibility(View.VISIBLE);

            }
        });

        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    mPauseButton.setVisibility(View.GONE);
                    mPlayButton.setVisibility(View.VISIBLE);
                    mIsPause = true;
                }

            }
        });
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mPauseButton.setVisibility(View.GONE);
        mPlayButton.setVisibility(View.VISIBLE);
        mIsPause = false;

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mListView = null;

    }
}
