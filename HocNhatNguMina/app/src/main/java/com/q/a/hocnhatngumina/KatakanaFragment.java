package com.q.a.hocnhatngumina;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SeekBar;

import com.q.a.hocnhatngumina.adapters.AbstractBaseAdapter;
import com.q.a.hocnhatngumina.adapters.BangChuCaiAdapter;
import com.q.a.hocnhatngumina.database.ChuCaiTb;
import com.q.a.hocnhatngumina.models.ChuCai;
import com.q.a.hocnhatngumina.utils.Constants;
import com.q.a.hocnhatngumina.utils.DownloadFile;
import com.q.a.hocnhatngumina.utils.FileMn;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class KatakanaFragment extends Fragment implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener,
        SeekBar.OnSeekBarChangeListener, MediaPlayer.OnSeekCompleteListener  {

    private MediaPlayer mMediaPlayer;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnSeekCompleteListener(this);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.reset();

    }

    public KatakanaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_katakana, container, false);

        final GridView gridView = (GridView)rootView.findViewById(R.id.grid_view);
        gridView.setAdapter(new BangChuCaiAdapter(getContext(), false, new AbstractBaseAdapter.ListViewOnItemClickListener() {
            @Override
            public void onItemClick(int postion, View view) {
                if (mMediaPlayer.isPlaying()) return;
                ChuCai cc = (ChuCai)gridView.getAdapter().getItem(postion);
                String name = cc.getAudioName();
                if (!FileMn.isFileExists(Constants.PATH_AUDIO_ABC + "/" + name)) {
                    name = cc.getAudioLink().hashCode() + cc.getRomaji();
                    DownloadFile.start(getContext(), cc.getAudioLink(), Constants.PATH_AUDIO_ABC, name);
                    ChuCai chuCai = new ChuCai();
                    cc.setAudioName(name);
                    chuCai.setAudioName(name);
                    chuCai.setId(cc.getId());
                    ChuCaiTb tb = new ChuCaiTb(getContext());
                    tb.update(chuCai);
                    return;
                }
                try {
                    mMediaPlayer.reset();
                    mMediaPlayer.setDataSource(FileMn.getPathFile(Constants.PATH_AUDIO_ABC + "/" + name));
                    mMediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }));

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
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
