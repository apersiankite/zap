package com.zoomcar.zap.applications;

import android.app.Application;

import com.zoomcar.zap.network.NetworkManager;
import com.zoomcar.zap.utils.Logger;
import com.zoomcar.zap.utils.SharedPrefUtil;

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
