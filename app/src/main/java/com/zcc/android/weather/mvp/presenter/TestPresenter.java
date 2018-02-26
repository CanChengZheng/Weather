package com.zcc.android.weather.mvp.presenter;

import com.zcc.android.weather.mvp.contract.TestContract;
import com.zcc.android.weather.mvp.model.ICallBack;
import com.zcc.android.weather.mvp.model.Model;

/**
 * @author ZCC
 * @date 2018/2/26
 * @description
 */
public class TestPresenter extends TestContract.Presenter {
    @Override
    public void request(String url) {
        Model.getInstance().get(url, getTAG(), new ICallBack() {
            @Override
            public void onSuccess(String s) {
                if(getView() != null) {
                    getView().request(s);
                }
            }

            @Override
            public void onFailure(String s) {
                getView().onFailure(s);
            }
        });
    }
}
