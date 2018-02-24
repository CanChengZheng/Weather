package com.zcc.android.weather.mvp.presenter;

import com.zcc.android.weather.mvp.model.Model;
import com.zcc.android.weather.mvp.view.IBaseView;

/**
 * @author ZCC
 * @date 2018/2/24
 * @description Presenter 基类，提供绑定解绑Presenter的方法
 */
public class BasePresenter<V extends IBaseView> {
    protected final String TAG = this.getClass().getSimpleName();

    private V mView;
    private Model mModel = Model.getInstance();

    /**
     * 绑定View
     *
     * @param view 要绑定的View
     */
    public void attachView(V view) {
        mView = view;
    }

    /**
     * 解绑View
     */
    public void detachView() {
        interruptRequest();
        mView = null;
    }

    public V getView() {
        return mView;
    }

    public Model getModel() {
        return mModel;
    }

    /**
     * 获取View的完整名称
     */
    public String getTAG() {
        return mView.getClass().getName() + ":" + TAG;
    }

    /**
     * 中断正在进行的请求
     */
    private void interruptRequest() {
        // TODO 中断请求
    }
}
