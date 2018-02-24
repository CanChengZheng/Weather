package com.zcc.android.weather.mvp.view;

/**
 * @author ZCC
 * @date 2018/2/24
 * @description 所有View必须实现该接口
 */
public interface IBaseView {
    /**
     * 处理请求失败的情况
     * @param s 返回的请求失败的有关信息，根据实际情况进行处理
     */
    void onFailure(String s);

}
