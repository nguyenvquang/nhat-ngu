package com.q.a.hocnhatngumina.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.q.a.hocnhatngumina.R;
import com.q.a.hocnhatngumina.utils.Constants;

public class MediaService extends Service  implements MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {

    private String mURL = "";
    private MediaPlayer mPlayer;
    public static final  int NOTIFICATTON_ID = 100011101;

    public MediaService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.setOnSeekCompleteListener(this);
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnErrorListener(this);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "start sv", Toast.LENGTH_SHORT).show();
        mURL = intent.getExtras().getString(Constants.URL_FILE_MEDIA);

        try {
            mPlayer.setDataSource(mURL);
            mPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher);


        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.item_play_media);


        PendingIntent playPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, new Intent(getApplicationContext(), MediaPlayReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.bt_play, playPendingIntent);

        PendingIntent powerPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, new Intent(getApplicationContext(), MediaPowerReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.bt_power, powerPendingIntent);

        PendingIntent pausePendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 2, new Intent(getApplicationContext(), MediaPauseReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.bt_pause, pausePendingIntent);


        mBuilder.setContent(remoteViews);

        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;
//        notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
//        notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
//        notification.defaults |= Notification.DEFAULT_SOUND; // Sound

        NotificationManager notificationManager = (NotificationManager)getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATTON_ID, notification);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
//        Toast.makeText(getApplicationContext(), "stop sv", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        if (mPlayer.isPlaying()) mPlayer.stop();
        mPlayer.release();
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
        mediaPlayer.release();
    }

}
