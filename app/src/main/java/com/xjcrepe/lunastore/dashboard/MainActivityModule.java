package com.xjcrepe.lunastore.dashboard;

import com.xjcrepe.lunastore.dashboard.account.AccountFragment;
import com.xjcrepe.lunastore.dashboard.cart.CartListContract;
import com.xjcrepe.lunastore.dashboard.cart.CartListFragment;
import com.xjcrepe.lunastore.dashboard.cart.CartListPresenter;
import com.xjcrepe.lunastore.dashboard.productlist.ProductListContract;
import com.xjcrepe.lunastore.dashboard.productlist.ProductListFragment;
import com.xjcrepe.lunastore.dashboard.productlist.ProductListPresenter;
import com.xjcrepe.lunastore.di.ActivityScoped;
import com.xjcrepe.lunastore.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by LiXijun on 2017/8/21.
 */

@Module
public abstract class MainActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ProductListFragment productListFragment();
    
    @ActivityScoped
    @Binds
    abstract ProductListContract.Presenter productListPresenter(ProductListPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract CartListFragment cartListFragment();

    @ActivityScoped
    @Binds
    abstract CartListContract.Presenter cartListPresenter(CartListPresenter presenter);

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AccountFragment accountFragment();
}
