package com.zap.network;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Prateek on 01/08/16.
 */
public class RetrofitRequestManager {

    private static final String TAG = "RetrofitRequestManager";
    private static final String BASE_URL = APIConstant.DOMAIN;
    private static RetrofitRequestManager mInstance;
    private ZoomAPI mZoomAPI;

    private RetrofitRequestManager() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(LoganSquareConverterFactory.create())
                .build();
        mZoomAPI = retrofit.create(ZoomAPI.class);
    }

    private OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        return builder.build();
    }

    public static RetrofitRequestManager getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitRequestManager.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitRequestManager();
                }
            }
        }
        return mInstance;
    }

    public ZoomAPI getAPI() {
        if (mZoomAPI == null) {
            throw new NullPointerException("Zoom API has not been initialzed");
        }
        return mZoomAPI;
    }
}
