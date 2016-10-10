package com.q.a.hocnhatngumina.database;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Nguyen Van Quang on 7/31/2016.
 */
public class DbHelper {

    private static final String DB_NAME = "hoc_nhat_ngu.db";
    private static final String DB_PATH = "Hoc nhat ngu Mina/database";
    private static final int DB_VERSION = 24;                                                                                                   ;
    private static final String KEY_VERSION = "VERSION_DATABASE";
    private Context mContext;

    public DbHelper(Context mContext) {
        this.mContext = mContext;
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        int version = sp.getInt(KEY_VERSION, 1);
        if (version != DB_VERSION || !isHasDatabaseFile()) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(KEY_VERSION, DB_VERSION);
            editor.apply();
            Toast.makeText(mContext, "Copy database", Toast.LENGTH_SHORT).show();
           initialize();
        }
    }

    private void initialize() {
        new AsyncTask<Void, Void, Void>() {
            ProgressDialog pd;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                pd = ProgressDialog.show(mContext, null, null);
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    copyDb();
                } catch (Exception e) {
                    e.printStackTrace();
                    while (true) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
//                if (pd.isShowing()) pd.cancel();
            }
        }.execute();
    }

    public SQLiteDatabase open()  {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DB_PATH + "/" + DB_NAME;
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public static SQLiteDatabase openDb()  {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + DB_PATH + "/" + DB_NAME;
        return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    private void copyDb() throws Exception {
        File sdFile = Environment.getExternalStorageDirectory();
        File dirFile = new File(sdFile.getAbsolutePath() + "/" + DB_PATH);
        dirFile.mkdirs();
        File f = new File(dirFile.getAbsolutePath(), DB_NAME);
        if (f.exists()) f.delete();
        InputStream is = mContext.getAssets().open("data/" + DB_NAME );
        if (is != null) {
            if (isExternalStorageReadable() && isExternalStorageWritable()) {
                InputStreamReader isr = new InputStreamReader(is);
                FileOutputStream fout = new FileOutputStream(f);
                byte[] buffer = new byte[1024];
                int countByte = 0;
                while ((countByte = is.read(buffer)) > 0) {
                    fout.write(buffer, 0, countByte);
                    buffer = new byte[1024];
                }
                fout.close();
                isr.close();
                is.close();
            }

        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isHasDatabaseFile() {
        File sdFile = Environment.getExternalStorageDirectory();
        if (isExternalStorageReadable()) {
            File dbFile = new File(sdFile.getAbsolutePath() + "/" + DB_PATH + "/" + DB_NAME);
            if (dbFile.exists()) return true;
        }
        return false;
    }


}
