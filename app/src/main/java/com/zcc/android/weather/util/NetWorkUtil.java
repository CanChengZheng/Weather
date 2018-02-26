package com.zcc.android.weather.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.zcc.android.weather.R;


/**
 * @author DeMon
 * @date 2017/11/3
 * @description 判断网络状态的方法
 */

public class NetWorkUtil {
    /**
     * 判断网络状态
     */
    public static boolean NetWorkStatus(Context context) {
        //获取系统的连接服务
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 设置网络
     * Dialog提醒
     *
     * @param activity
     */
    public static void setNetWorkDialog(final Activity activity) {
        AlertDialog.Builder b = new AlertDialog.Builder(activity).setMessage("网络不可用，请检查网络设置!");
        b.setCancelable(false);
        b.setPositiveButton(R.string.setting, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //跳转到设置网络界面
                activity.startActivity(new Intent(Settings.ACTION_SETTINGS));
            }
        });
        b.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                activity.finish();
            }
        });
        b.create().show();
    }
}