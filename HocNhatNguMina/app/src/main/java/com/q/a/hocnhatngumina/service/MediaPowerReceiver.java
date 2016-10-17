package com.q.a.hocnhatngumina.service;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MediaPowerReceiver extends BroadcastReceiver {
    public MediaPowerReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        Toast.makeText(context, "Power", Toast.LENGTH_SHORT).show();
        Intent statusIntent = new Intent();
        Intent svIntent1 = new Intent(context, MediaService.class);
        context.stopService(svIntent1);
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(MediaService.NOTIFICATTON_ID);
    }
}
