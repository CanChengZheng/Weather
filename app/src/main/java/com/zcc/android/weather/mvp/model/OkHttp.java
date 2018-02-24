package com.zcc.android.weather.mvp.model;

import okhttp3.OkHttpClient;

/**
 * @author ZCC
 * @date 2018/2/24
 * @description
 */
public class OkHttp {
    private static final OkHttpClient ourInstance = new OkHttpClient();

    public static OkHttpClient getInstance() {
        return ourInstance;
    }

    private OkHttp() {
    }
}
