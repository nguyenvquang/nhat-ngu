package com.q.a.hocnhatngumina.utils;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;

import com.q.a.hocnhatngumina.MainActivity;
import com.q.a.hocnhatngumina.R;

/**
 * Created by Nguyen Van Quang on 8/7/2016.
 */
public class DownloadFile {

    private DownloadManager mDownloadManager;
    private IntentFilter mDowloadFilter;
    private long mDowloadID = -1;
    private BroadcastReceiver mDownloadReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (id == mDowloadID) {
                DownloadManager.Query query = new DownloadManager.Query();
                Cursor c = mDownloadManager.query(query);
                if (c != null && c.moveToFirst()) {
                    int statusIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    int status = c.getInt(statusIndex);
                    switch (status) {
                        case DownloadManager.STATUS_SUCCESSFUL:
                            Log.i("DOWNLOAD STATUS", "successful");
//                            Toast.makeText(context, "Download successfull", Toast.LENGTH_SHORT).show();
                            break;
                        case DownloadManager.STATUS_FAILED:
                            Log.i("DOWNLOAD STATUS", "failed");
                            Toast.makeText(context, "Download failed", Toast.LENGTH_SHORT).show();
                            break;
                        case DownloadManager.STATUS_PENDING:
                            Log.i("DOWNLOAD STATUS", "pending");
                            break;
                        case DownloadManager.STATUS_RUNNING:
                            Log.i("DOWNLOAD STATUS", "running");
                            break;
                        case DownloadManager.STATUS_PAUSED:
                            Log.i("DOWNLOAD STATUS", "paused");
                            break;
                    }
                }
            }
        }
    };


    private Context mContext;
    private Activity mActivity;

    public DownloadFile(Context context, Activity activity) {
        mContext = context;
        mActivity = activity;
        mDowloadFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        mContext.registerReceiver(mDownloadReceiver, mDowloadFilter);

    }

    public void unregisterDownloadReceiver() {
            mContext.unregisterReceiver(mDownloadReceiver);
    }

    public void registerDownloadReceiver() {
            mContext.registerReceiver(mDownloadReceiver, mDowloadFilter);
    }

    public void start(String link, String dirPath, String name) {
        mDownloadManager = (DownloadManager)mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(link);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDescription(mContext.getString(R.string.app_name)).setTitle(link);
        request.setDestinationInExternalPublicDir("/" + dirPath, name);
        request.setVisibleInDownloadsUi(true);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        mDowloadID = mDownloadManager.enqueue(request);
        showNotification(mDowloadID, link, uri.getLastPathSegment());
    }


    public static void start(Context context, String link, String dirPath, String name) {
        DownloadManager downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (!URLUtil.isValidUrl(link)) return;
        Uri uri = Uri.parse(link);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDescription(context.getString(R.string.app_name)).setTitle(link);
        request.setDestinationInExternalPublicDir("/" + dirPath, name);
        request.setVisibleInDownloadsUi(true);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadManager.enqueue(request);

        if (Build.VERSION.SDK_INT < 16) return;
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(link);
        builder.setContentText(uri.getLastPathSegment());
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultIntent , PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

//        builder.setContentIntent(pendingIntent);
//        builder.setLights(Color.BLUE, 500, 500);
//        long[] pattern = {500,500,500,500};
//        builder.setAutoCancel(true);
//        builder.setVibrate(pattern);
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        builder.setSound(alarmSound);
//        builder.setStyle(new Notification.BigTextStyle());
        builder.setPriority(Notification.PRIORITY_HIGH);
        builder.setTicker(context.getResources().getString(R.string.app_name) + " start downloading...");


        Notification notification = builder.build();
//        notification.defaults |= Notification.DEFAULT_SOUND;
        notificationManager.notify(0, notification);

        try {
            Thread.sleep(500);
            notificationManager.cancel(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void showNotification(long id, String title, String content) {
        if (Build.VERSION.SDK_INT < 16) return;
        Notification.Builder builder = new Notification.Builder(mContext);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(title);
        builder.setContentText(content);
        Intent resultIntent = new Intent(mContext, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, resultIntent , PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) mContext
                .getSystemService(Context.NOTIFICATION_SERVICE);

//        builder.setContentIntent(pendingIntent);
//        builder.setLights(Color.BLUE, 500, 500);
//        long[] pattern = {500,500,500,500};
//        builder.setAutoCancel(true);
//        builder.setVibrate(pattern);
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        builder.setSound(alarmSound);
//        builder.setStyle(new Notification.BigTextStyle());
        builder.setPriority(Notification.PRIORITY_HIGH);
        builder.setTicker("Start downloading...");


        Notification notification = builder.build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        notificationManager.notify(0, notification);

    }


}
