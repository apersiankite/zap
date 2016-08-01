package com.zap.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Prateek on 23/09/15.
 */
public class SharedPrefUtil {

    private static final String PREF_NAME = "SHRED_PREF_ZOOMCAR_ZAP";
    public static final int DEFAULT_INT_VALUE = -1;
    public static final String DEFAULT_STRING = "";
    public static final long DEFAULT_LONG = -1L;
    public static final float DEFAULT_FLOAT = -1.0F;
    private static SharedPrefUtil mInstance;
    private SharedPreferences mSharedPrefs;
    private Context context;

    private SharedPrefUtil(Context context) {
        this.context = context;
        initSharedPrefs();
    }

    private void initSharedPrefs() {
        mSharedPrefs = context.getSharedPreferences(PREF_NAME, 0);
    }

    public static synchronized SharedPrefUtil getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefUtil(context);
        }
        return mInstance;
    }

    public void setSharedData(String key, boolean value) {
        if (mSharedPrefs == null) {
            initSharedPrefs();
        }
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void setSharedData(String key, int value) {
        if (mSharedPrefs == null) {
            initSharedPrefs();
        }
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void setSharedData(String key, float value) {
        if (mSharedPrefs == null) {
            initSharedPrefs();
        }
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public void setSharedData(String key, String value) {
        if (mSharedPrefs == null) {
            initSharedPrefs();
        }
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void setSharedData(String key, long value) {
        if (mSharedPrefs == null) {
            initSharedPrefs();
        }
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public boolean getSharedBoolean(String key) {
        if (mSharedPrefs == null) {
            initSharedPrefs();
        }
        return mSharedPrefs.getBoolean(key, false);
    }

    public int getSharedInteger(String key) {
        if (mSharedPrefs == null) {
            initSharedPrefs();
        }
        return mSharedPrefs.getInt(key, DEFAULT_INT_VALUE);
    }

    public long getSharedLong(String key) {
        if (mSharedPrefs == null) {
            initSharedPrefs();
        }
        return mSharedPrefs.getLong(key, DEFAULT_LONG);
    }

    public String getSharedString(String key) {
        if (mSharedPrefs == null) {
            initSharedPrefs();
        }
        return mSharedPrefs.getString(key, DEFAULT_STRING);
    }

    public float getSharedFloat(String key) {
        if (mSharedPrefs == null) {
            initSharedPrefs();
        }
        return mSharedPrefs.getFloat(key, DEFAULT_FLOAT);
    }

    public void clearSharedData(String key) {
        if (mSharedPrefs == null) {
            initSharedPrefs();
        }
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        editor.remove(key);
        editor.apply();
    }
}
