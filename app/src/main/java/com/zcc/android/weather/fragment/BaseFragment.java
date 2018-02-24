package com.zcc.android.weather.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zcc.android.weather.mvp.presenter.BasePresenter;
import com.zcc.android.weather.mvp.presenter.PresenterFactory;
import com.zcc.android.weather.mvp.view.IBaseView;
import com.zcc.android.weather.util.ToastUtil;
import com.zcc.android.weather.widget.CustomDialog;

import java.util.HashSet;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ZCC
 * @date 2018/2/24
 * @description Fragment 基类
 * 1. 封装Presenter
 * 1.1 在需要使用Presenter的Fragment类上方添加注解@CreatePresenter({XXXPresenter.class})，声明所需要的Presenter
 * 1.2 实现业务逻辑的时候通过getPresenter(Class)来获取Presenter
 * 2. 封装CustomDialog
 * 2.1 需要显示时，根据需要调用showLoadingDialog() / showErrorDialog()
 * 2.2 调用disMissDialog()关闭弹窗，onDestroy()也会调用
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    protected final String TAG = this.getClass().getSimpleName();

    private CustomDialog mDialog;

    /**
     * 使用List来保存Presenter
     * 解决一个界面多个Presenter的情况
     */
    private Set<BasePresenter> mPresenterSet;

    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(bindLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initPresenter();
        initCreate();
        return view;
    }

    /**
     * 获取绑定的布局
     */
    protected abstract int bindLayout();

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        createPresenter();
        attachView();
    }

    /**
     * 创建Presenter
     */
    private void createPresenter() {
        mPresenterSet = PresenterFactory.getPresenter(this);
    }

    /**
     * Presenter绑定V层
     */
    private void attachView() {
        // 绑定View
        for (BasePresenter e : mPresenterSet) {
            e.attachView(this);
            Log.d(TAG, e.getClass().getSimpleName() + " -- 绑定 -- " + TAG);
        }
    }

    /**
     * 获取对应的Presenter
     *
     * @param clazz 对应的Presenter类
     * @param <T>
     * @return 对应的Presenter
     */
    @SuppressWarnings("unchecked")
    public <T extends BasePresenter> T getPresenter(Class<? extends BasePresenter> clazz) {
        for (BasePresenter e : mPresenterSet) {
            if (e.getClass() == clazz) {
                Log.d(TAG, e.getClass().getSimpleName() + " -- 成功获取");
                return (T) e;
            }
        }
        Log.d(TAG, clazz.getSimpleName() + " -- 找不到类");
        return null;
    }


    /**
     * 初始化布局和数据
     */
    protected abstract void initCreate();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        detachView();
        disMissDialog();
    }

    /**
     * 显示加载对话框
     */
    public void showLoadingDialog() {
        showCustomDialog(CustomDialog.LOADING);
    }

    /**
     *
     */
    public void showErrorDialog() {
        showCustomDialog(CustomDialog.ERROR);
    }

    /**
     * 显示对话框
     *
     * @param layoutId 对话框的类型
     */
    private void showCustomDialog(int layoutId) {
        // 考虑多次调用的情况，先将以显示的dialog关闭
        disMissDialog();
        mDialog = new CustomDialog(getContext(), layoutId);
        mDialog.show();
        Log.d(TAG, "显示Dialog");
    }

    /**
     * 若dialog显示，则将其关闭
     */
    public void disMissDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            Log.d(TAG, "关闭Dialog");
        }
    }

    /**
     * 解绑View
     */
    private void detachView() {
        for (BasePresenter e : mPresenterSet) {
            Log.d(TAG, e.getClass().getSimpleName() + " -- 解除绑定");
            e.detachView();
        }
        mPresenterSet.clear();
    }

    @Override
    public void onFailure(String s) {
        ToastUtil.showMainThreadToast(getContext(), s);
        disMissDialog();
    }
}
