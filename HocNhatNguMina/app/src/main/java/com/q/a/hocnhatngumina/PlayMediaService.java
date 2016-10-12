package com.q.a.hocnhatngumina;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
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

/**
 * Created by Quang on 10/11/2016.
 */
public class PlayMediaService extends IntentService {

    public PlayMediaService() {
        super("PlayMediaService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "Service start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onHandleIntent(Intent intent) {



        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher);
        final RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.item_play_media);
        remoteViews.setOnClickFillInIntent(R.id.bt_play, new Intent(getApplicationContext(), PlayClickReceiver.class));
        mBuilder.setContent(remoteViews);
        Notification  notification = mBuilder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;
        notification.defaults |= Notification.DEFAULT_LIGHTS; // LED
        notification.defaults |= Notification.DEFAULT_VIBRATE; //Vibration
        notification.defaults |= Notification.DEFAULT_SOUND; // Sound
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(100, notification);

    }

    private void playClick() {
        Toast.makeText(getApplicationContext(), "as", Toast.LENGTH_SHORT).show();
    }
}
