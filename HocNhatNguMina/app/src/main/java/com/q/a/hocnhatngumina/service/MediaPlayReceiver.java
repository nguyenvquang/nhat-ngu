package com.q.a.hocnhatngumina.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.q.a.hocnhatngumina.utils.Constants;

public class MediaPlayReceiver extends BroadcastReceiver {
    public MediaPlayReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Intent statusIntent = new Intent();
        statusIntent.setAction(Constants.ACTION_INTENT_MEDIA_PLAY);
        context.sendBroadcast(statusIntent);
    }
}
