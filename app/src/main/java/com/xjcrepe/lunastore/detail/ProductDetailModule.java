package com.xjcrepe.lunastore.detail;

import com.xjcrepe.lunastore.di.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by LiXijun on 2017/8/22.
 */

@Module
public abstract class ProductDetailModule {

    @ActivityScoped
    @Binds
    abstract ProductDetailContract.Presenter productDetailPresenter(ProductDetailPresenter presenter);
}
