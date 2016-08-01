package com.zap.network;

import android.app.Activity;
import android.content.Context;

import com.bluelinelabs.logansquare.LoganSquare;
import com.zap.utils.ConstantUtil;
import com.zap.utils.Logger;
import com.zap.vo.BaseVO;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Prateek on 01/08/16.
 */
public class NetworkManager {

    private static final String TAG = "NetworkManager";

    public static void init() {
        RetrofitRequestManager.getInstance();
        Logger.dLog(TAG, "[init]");
    }

    public static <T> void request(final Context context, final int requestCode, Map<String, String> params, final Listener listener, final String requestName) {

        Callback<T> callback = new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful()) {
                    if (context instanceof Activity) {
                        if (!((Activity) context).isFinishing()) {
                            // delivering response to activity [only when in resumed state]
                            if (listener != null) {
                                listener.onSuccess(response.body());
                            }
                        }
                    } else {
                        // calling context is service
                        if (listener != null) {
                            listener.onSuccess(response.body());
                        }
                    }
                } else if (response.errorBody() != null) {
                    try {
                        NetworkError networkError = NetworkError.buildNetworkError(response.code(), response.errorBody().bytes());
                        if (context instanceof Activity) {
                            if (!((Activity) context).isFinishing()) {
                                // delivering response to activity [only when in resumed state]
                                if (listener != null) {
                                    listener.onError(networkError);
                                }
                            }
                        } else {
                            if (listener != null) {
                                listener.onError(networkError);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Logger.eLog(TAG, "Exception caught while building NetworkError : " + e);
                        if (listener != null) {
                            NetworkError networkError = new NetworkError(ConstantUtil.ZoomError.ERROR_NO_NETWORK);
                            networkError.error.msg = "Seems like there is no network.Check your net connection and try again";
                            listener.onError(networkError);
                        }
                    }
                } else {
                    NetworkError networkError = new NetworkError(ConstantUtil.ZoomError.ERROR_NO_NETWORK);
                    networkError.error.msg = "Seems like there is no network.Check your net connection and try again";
                    if (context instanceof Activity) {
                        if (!((Activity) context).isFinishing()) {
                            // delivering response to activity [only when in resumed state]
                            if (listener != null) {
                                listener.onError(networkError);
                            }
                        }
                    } else {
                        if (listener != null) {
                            listener.onError(networkError);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                NetworkError networkError = new NetworkError(ConstantUtil.ZoomError.ERROR_NO_NETWORK);
                networkError.error.msg = "Seems like there is no network.Check your net connection and try again";
                if (context instanceof Activity) {
                    if (!((Activity) context).isFinishing()) {
                        // delivering response to activity [only when in resumed state]
                        if (listener != null) {
                            listener.onError(networkError);
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.onError(networkError);
                    }
                }
            }
        };


        switch (requestCode) {
            default:
                listener.onError(new NetworkError(0, "Something went wrong.Please try again later."));
        }
    }


    public static class NetworkError {
        private int httpCode;
        private BaseVO error;

        public NetworkError(int httpCode, BaseVO error) {
            this.httpCode = httpCode;
            this.error = error;
            if (httpCode == 500) {
                // Internal Server Error
                this.error.error_code = ConstantUtil.ZoomError.ERROR_SERVER_ERROR;
            }
        }

        static NetworkError buildNetworkError(int httpCode, byte[] data) {
            BaseVO baseResponse;
            try {
                baseResponse = LoganSquare.parse(new String(data), BaseVO.class);
                return new NetworkError(httpCode, baseResponse);
            } catch (Exception e) {
                Logger.eLog(TAG, "Exception caught while building network error object : " + e);
                return new NetworkError(0);
            }
        }

        public NetworkError(int httpCode) {
            this.httpCode = httpCode;
            this.error = new BaseVO();
            error.msg = "Something went wrong.Please try again later.";

        }

        NetworkError(int httpCode, String errorMsg) {
            this.httpCode = httpCode;
            error = new BaseVO();
            error.msg = errorMsg;
        }

        public int getHttpCode() {
            return httpCode;
        }

        public BaseVO getError() {
            return error;
        }

        @Override
        public String toString() {
            return "NetworkError{" +
                    "httpCode=" + httpCode +
                    ", error=" + error +
                    '}';
        }
    }

    public interface Listener<T> {
        void onSuccess(T response);

        void onError(NetworkError error);
    }

}
