package com.q.a.hocnhatngumina.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by Nguyen Van Quang on 8/7/2016.
 */
public class FileMn {


    public static boolean isFileExists(String file) {
        File sdFile1 = Environment.getExternalStorageDirectory();
        if (isExternalStorageReadable()) {
            File f = new File(sdFile1.getAbsolutePath() + "/" + file);
            if (f.exists()) return true;
        }
        return false;
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public static String getPathFile(String file) {
        File sdFile1 = Environment.getExternalStorageDirectory();
        File f = new File(sdFile1.getAbsolutePath() + "/" + file);
        if (f.exists()) return f.getAbsolutePath();
        return null;
    }
}
