package com.q.a.hocnhatngumina;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.q.a.hocnhatngumina.service.MediaPauseReceiver;
import com.q.a.hocnhatngumina.service.MediaPlayReceiver;
import com.q.a.hocnhatngumina.service.MediaPowerReceiver;
import com.q.a.hocnhatngumina.utils.Constants;

import java.io.IOException;

/**
 * Created by Quang on 10/11/2016.
 */
public class PlayMediaService extends IntentService
        implements MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {

    public PlayMediaService() {
        super("PlayMediaService");
    }

    public static final  int NOTIFICATTON_ID = 100001;

    private String mURL = "";
    private MediaPlayer mPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
//
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnSeekCompleteListener(this);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnErrorListener(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        mURL = intent.getExtras().getString(Constants.URL_FILE_MEDIA);
        String state = intent.getExtras().getString(Constants.SERVICE_STATE);
        Log.i("state", state);
        if (state.equals("stop")) {
            if (mPlayer.isPlaying()) mPlayer.stop();
            if (mPlayer != null) mPlayer.release();
            return;
        }

        try {
            mPlayer.setDataSource(mURL);
            mPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher);

        final RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.item_play_media);
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(getApplicationContext(), MediaPlayReceiver.class), 0);
        remoteViews.setOnClickPendingIntent(R.id.bt_play, playPendingIntent);
        PendingIntent powerPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, new Intent(getApplicationContext(), MediaPowerReceiver.class), 0);
        remoteViews.setOnClickPendingIntent(R.id.bt_power, powerPendingIntent);
        PendingIntent pausePendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 2, new Intent(getApplicationContext(), MediaPauseReceiver.class), 0);
        remoteViews.setOnClickPendingIntent(R.id.bt_pause, pausePendingIntent);

        mBuilder.setContent(remoteViews);
        Notification  notification = mBuilder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
//        notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
//        notification.defaults |= Notification.DEFAULT_SOUND; // Sound
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATTON_ID, notification);

    }



    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }


    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mp.release();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        mp.start();
    }

    @Override
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        mediaPlayer.stop();
    }


    @Override
    public void onDestroy() {
//        Toast.makeText(getApplicationContext(), "Service destroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
