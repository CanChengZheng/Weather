package com.zcc.android.weather.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.zcc.android.weather.R;


/**
 * Created by DeMon on 2017/11/29.
 */
public class CustomDialog extends Dialog {

    private int LayoutId = 0;//默认为0，加载对话框

    public static int LOADING = 0;//加载对话框
    public static int ERROR = 1;//错误对话框


    public CustomDialog(Context context) {
        super(context, R.style.TransparentDialog);
    }

    public CustomDialog(Context context, int LayoutId) {
        super(context, R.style.TransparentDialog);
        this.LayoutId = LayoutId;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (LayoutId == LOADING) {
            setContentView(R.layout.progress_loading);
        } else if (LayoutId == ERROR) {
            setContentView(R.layout.progress_error);
        } else {
            setContentView(R.layout.progress_loading);
        }
        setCanceledOnTouchOutside(false);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }
}
