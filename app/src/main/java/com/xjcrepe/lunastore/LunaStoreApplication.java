package com.xjcrepe.lunastore;

import com.xjcrepe.lunastore.di.AppComponent;
import com.xjcrepe.lunastore.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * Created by LiXijun on 2017/8/20.
 */

public class LunaStoreApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
