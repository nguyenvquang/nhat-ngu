package com.q.a.hocnhatngumina.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.q.a.hocnhatngumina.utils.Constants;

public class MediaPowerReceiver extends BroadcastReceiver {
    public MediaPowerReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        Toast.makeText(context, "Power", Toast.LENGTH_SHORT).show();
        Intent statusIntent = new Intent();
        statusIntent.setAction(Constants.ACTION_INTENT_MEDIA_STOP);
        context.sendBroadcast(statusIntent);
    }
}
