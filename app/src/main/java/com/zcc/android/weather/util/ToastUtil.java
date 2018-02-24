package com.zcc.android.weather.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * @author ZCC
 * @date 2018/2/24
 * @description Toast 工具类
 */
public class ToastUtil {

    /**
     * 显示一个Toast
     * @param context
     * @param msg 显示的内容
     */
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 异步线程中显示Toast，解决异步线程无法显示Toast的问题
     * @param context
     * @param msg 显示的内容
     */
    public static void showMainThreadToast(final Context context, final String msg) {
        new Handler(Looper.getMainLooper()).post(
                new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(context, msg);
                    }
                }
        );
    }
}
