package com.zoomcar.zap.utils;

import android.util.Log;

import com.zoomcar.zap.BuildConfig;

/**
 * Created by Prateek on 01/08/16.
 */
public class Logger {

    public static void dLog(String tag, Object data) {
        if (BuildConfig.DEBUG_MODE && data != null) {
            Log.d(tag, data.toString());
        }
    }

    public static void eLog(String tag, Object data) {
        if (BuildConfig.DEBUG_MODE && data != null) {
            Log.e(tag, data.toString());
        }
    }

}
