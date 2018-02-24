package com.zcc.android.weather.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * @author ZCC
 * @date 2018/2/24
 * @description 针对Handler内存泄漏进行封装
 * 使用接口调用处理message
 * 解决了在静态Handler内使用外部变量（方法），需要将变量（方法）改为静态的问题
 */
public class HandlerHepler extends Handler {
    /**
     * WeakReference存放存入的Activity，
     * 这样在Activity结束回收的时候WeakReference不会阻止系统进行回收操作，
     * 能有效的避免因为handler引起的内存泄露风险
     */
    private WeakReference<Activity> mActivityWeakReference;
    private WeakReference<IHandler> mIHandlerWeakReference;

    public HandlerHepler(Activity activity, IHandler iHandler) {
        mActivityWeakReference = new WeakReference<>(activity);
        mIHandlerWeakReference = new WeakReference<>(iHandler);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Activity activity = mActivityWeakReference.get();
        IHandler iHandler = mIHandlerWeakReference.get();
        if(activity != null && iHandler != null) {
            iHandler.handlerMessage(activity, msg);
        }
    }

    public interface IHandler {
        void handlerMessage(Activity activity, Message msg);
    }
}
