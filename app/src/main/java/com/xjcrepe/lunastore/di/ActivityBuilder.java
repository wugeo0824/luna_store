package com.xjcrepe.lunastore.di;

import com.xjcrepe.lunastore.dashboard.MainActivity;
import com.xjcrepe.lunastore.dashboard.MainActivityModule;
import com.xjcrepe.lunastore.detail.ProductDetailActivity;
import com.xjcrepe.lunastore.detail.ProductDetailModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by LiXijun on 2017/8/20.
 */

@Module
public abstract class ActivityBuilder {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = ProductDetailModule.class)
    abstract ProductDetailActivity bindProductDetailActivity();
}
