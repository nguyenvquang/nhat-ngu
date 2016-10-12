package com.q.a.hocnhatngumina;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.q.a.hocnhatngumina.service.MediaPauseReceiver;
import com.q.a.hocnhatngumina.service.MediaPlayReceiver;
import com.q.a.hocnhatngumina.service.MediaPowerReceiver;
import com.q.a.hocnhatngumina.utils.Constants;

/**
 * Created by Quang on 10/11/2016.
 */
public class PlayMediaService extends IntentService implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {

    public PlayMediaService() {
        super("PlayMediaService");
    }

    public static final  int NOTIFICATTON_ID = 100001;

    private String mURL = "";
    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
//        Toast.makeText(getApplicationContext(), "Service start", Toast.LENGTH_SHORT).show();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnBufferingUpdateListener(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        mURL = intent.getExtras().getString(Constants.URL_FILE_MEDIA);

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
        notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
        notification.defaults |= Notification.DEFAULT_SOUND; // Sound
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATTON_ID, notification);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

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

    }
}
