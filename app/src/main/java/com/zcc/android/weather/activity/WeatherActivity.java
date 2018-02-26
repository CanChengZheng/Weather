package com.zcc.android.weather.activity;

import android.util.Log;

import com.zcc.android.weather.R;
import com.zcc.android.weather.mvp.contract.TestContract;
import com.zcc.android.weather.mvp.presenter.CreatePresenter;
import com.zcc.android.weather.mvp.presenter.TestPresenter;

/**
 * @author ZCC
 * @date 2018/2/24
 * @description 天气预报，使用Viewpager + Weather实现左右滑动显示多个地点天气
 */
@CreatePresenter({TestPresenter.class})
public class WeatherActivity extends BaseActivity implements TestContract.View{

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        TestPresenter presenter = getPresenter(TestPresenter.class);
        presenter.request("https://www.baidu.com/");
    }

    @Override
    public void request(String s) {
        Log.d(TAG, s);
    }
}
