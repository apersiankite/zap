package com.zap.applications;

import android.app.Application;

import com.zap.network.NetworkManager;
import com.zap.utils.Logger;
import com.zap.utils.SharedPrefUtil;

/**
 * Created by Prateek on 01/08/16.
 */
public class ZAPApplication extends Application {

    private final String TAG = "ZAPApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.dLog(TAG, "onCreate()");
        SharedPrefUtil.getInstance(this);
        NetworkManager.init();
    }
}
