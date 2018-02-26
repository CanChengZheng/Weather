package com.zcc.android.weather.mvp.model;

/**
 * @author ZCC
 * @date 2018/2/26
 * @description
 */
public interface ICallBack {

    /**
     * 请求成功的回调
     * @param s 请求成功返回的字符串
     */
    void onSuccess(String s);

    /**
     * 请求失败的回调，一般是网络或服务器问题、或者界面关闭导致的网络请求被取消
     * @param s 请求失败返回的字符串
     */
    void onFailure(String s);
}
