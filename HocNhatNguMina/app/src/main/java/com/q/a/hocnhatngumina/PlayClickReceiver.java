package com.q.a.hocnhatngumina;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class PlayClickReceiver extends BroadcastReceiver {
    public PlayClickReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "Play", Toast.LENGTH_SHORT).show();
        Log.i("pl", "play");
    }
}
