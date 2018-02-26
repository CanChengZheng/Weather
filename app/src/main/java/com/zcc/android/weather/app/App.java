package com.zcc.android.weather.app;

import android.app.Application;

/**
 * @author ZCC
 * @date 2018/2/24
 * @description
 */
public class App extends Application {

    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static App getApp() {
        return mApp;
    }

}
