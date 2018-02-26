package com.zcc.android.weather.mvp.contract;

import com.zcc.android.weather.mvp.presenter.BasePresenter;
import com.zcc.android.weather.mvp.view.IBaseView;

/**
 * @author ZCC
 * @date 2018/2/26
 * @description
 */
public interface TestContract {

    interface View extends IBaseView{
        void request(String s);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void request(String url);
    }

}
