package com.zap.utils;

/**
 * Created by Prateek on 01/08/16.
 */
public class ConstantUtil {

    public static final boolean DEBUG = true;

    public static class ZoomError {
        public static final int ERROR_NONE = -1;
        public static final int ERROR_NO_NETWORK = 0;
        public static final int ERROR_SERVER_ERROR = 1003;
        public static final int ERROR_EMAIL_ALREADY_REGISTERED = 1013;
        public static final int ERROR_PHONE_ALREADY_REGISTERED = 1014;
        public static final int ERROR_WRONG_PASSWORD = 1016;
        public static final int ERROR_WRONG_EMAIL = 1017;
        public static final int ERROR_SESSION_EXPIRED = 1018;
        public static final int ERROR_INVALID_OTP = 1019;
        public static final int ERROR_UPDATE_APP = 1020;
    }
}
