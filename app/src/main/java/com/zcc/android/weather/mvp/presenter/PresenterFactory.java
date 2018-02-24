package com.zcc.android.weather.mvp.presenter;

import android.util.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ZCC
 * @date 2018/2/24
 * @description Presenter工厂类
 */
public class PresenterFactory {

    public static Set<BasePresenter> getPresenter(Object object) {
        Set<BasePresenter> set = new HashSet<>();
        // 获取修饰object的注解
        CreatePresenter annotation = object.getClass().getAnnotation(CreatePresenter.class);
        if(annotation == null) {
            return set;
        }
        Class<? extends BasePresenter>[] clazz = annotation.value();
        for(Class<? extends BasePresenter> e : clazz) {
            BasePresenter presenter = createPresenter(e);
            Log.d(object.getClass().getSimpleName(), presenter.getClass().getSimpleName() + " -- 成功创建");
            set.add(presenter);
        }
        return set;
    }

    private static BasePresenter createPresenter(Class<? extends BasePresenter> clazz) {
        BasePresenter presenter = null;
        try {
            presenter = clazz.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return presenter;
    }
}
