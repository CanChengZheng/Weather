package com.zcc.android.weather.mvp.model;

import okhttp3.OkHttpClient;

/**
 * @author ZCC
 * @date 2018/2/24
 * @description
 */
public class Model {
    private static final String TAG = "Model";
    private static final Model ourInstance = new Model();
    private OkHttpClient mClient = OkHttp.getInstance();

    public static Model getInstance() {
        return ourInstance;
    }

    private Model() {
    }

    public void interruptRequest(String tag) {

    }

}
