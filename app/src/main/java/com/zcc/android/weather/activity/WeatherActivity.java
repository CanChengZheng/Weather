package com.zcc.android.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zcc.android.weather.R;

/**
 * @author ZCC
 * @date 2018/2/24
 * @description 天气预报，使用Viewpager + Weather实现左右滑动显示多个地点天气
 */
public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
