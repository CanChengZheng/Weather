package com.zcc.android.weather.mvp.model;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author ZCC
 * @date 2018/2/24
 * @description
 */
public class Model {
    private static final String TAG = "Model";
    private static final Model sModel = new Model();
    private OkHttpClient mOkHttpClient = OkHttp.getInstance();

    public static Model getInstance() {
        return sModel;
    }

    private Model() {
    }

    public void get(String url, String tag, final ICallBack iCallBack) {
        final Request request = new Request.Builder().url(url).tag(tag).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //连接失败，一般来说是网络或者服务器的问题或者界面关闭导致的任务被取消
                if (call.isCanceled()) {
                    Log.d(TAG, "onFailure：取消网络请求 -- " + call.request().tag());
                } else {
                    Log.e(TAG, "onFailure：其他原因导致的网络请求失败 -- " + call.request().tag(), e);
                    iCallBack.onFailure("网络或服务器出了小问题，请稍后再试...");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                //连接成功，针对请求状态进行判断
                if (response.isSuccessful()) { // 请求成功，回掉请求结果
                    //将请求响应结果统一转为String，回调到Presenter，然后根据需求进行处理
                    iCallBack.onSuccess(result);
                } else { // 请求失败
                    Log.e(TAG, "状态码：" + response.code() + "\n" + result);
                    iCallBack.onFailure("状态码：" + response.code() + "\n" + result);
                }
                response.close();
            }
        });
    }

    /**
     * post请求
     *
     * @param url Api
     * @param map 请求参数集合，封装解决不同Api请求参数不同的问题
     * @param tag 标识唯一网络请求
     * @param callback 回调接口
     */
    public void post(String url, Map<String, Object> map, String tag, final ICallBack callback) {
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null) {
            //增强for循环遍历
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                builder.add(entry.getKey(), entry.getValue() + "");
            }
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().post(formBody).tag(tag).url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //连接失败，一般来说是网络或者服务器的问题或者界面关闭导致的任务被取消
                if (call.isCanceled()) {
                    Log.d(TAG, "onFailure：取消网络请求 -- " + call.request().tag());
                } else {
                    Log.e(TAG, "onFailure：其他原因导致的网络请求失败 -- " + call.request().tag() + "\n" + e.getMessage());
                    e.printStackTrace();
                    callback.onFailure("网络或服务器出了小问题，请稍后再试...");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                //连接成功，针对请求状态进行判断
                if (response.isSuccessful()) {//请求成功，回掉请求结果
                    //将请求响应结果统一转为String，回掉到Presenter，然后根据需求进行处理
                    callback.onSuccess(result);
                } else {//请求失败，Toast提示错误码
                    Log.e(TAG, "状态码：" + response.code() + "\n" + result);
                    callback.onFailure("状态码：" + response.code() + "\n" + result);
                }
                response.close();
            }
        });
    }

    /**
     * 取消对应的网络请求
     * @param tag 网络请求对应的tag
     */
    public void interruptRequest(String tag) {
        if(mOkHttpClient == null) return;
        Dispatcher dispatcher = mOkHttpClient.dispatcher();

        // 取消还在队列中的任务
        for (Call call : dispatcher.queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
                Log.d(TAG, "interruptRequest：取消网络请求 -- queuedCalls() -- " + tag);
            }
        }
        // 取消正在进行的网络请求任务
        for (Call call : dispatcher.runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
                Log.d(TAG, "interruptRequest：取消网络请求 -- runningCalls() -- " + tag);
            }
        }
    }

}
