package com.xjcrepe.lunastore.dashboard;

import com.xjcrepe.lunastore.dashboard.account.AccountFragment;
import com.xjcrepe.lunastore.dashboard.cart.CartListContract;
import com.xjcrepe.lunastore.dashboard.cart.CartListFragment;
import com.xjcrepe.lunastore.dashboard.cart.CartListPresenter;
import com.xjcrepe.lunastore.dashboard.cart.list.CartListAdapter;
import com.xjcrepe.lunastore.dashboard.productlist.ProductListContract;
import com.xjcrepe.lunastore.dashboard.productlist.ProductListFragment;
import com.xjcrepe.lunastore.dashboard.productlist.ProductListPresenter;
import com.xjcrepe.lunastore.dashboard.productlist.list.ProductListAdapter;
import com.xjcrepe.lunastore.di.ActivityScoped;
import com.xjcrepe.lunastore.di.FragmentScoped;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
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
    abstract ProductListContract.Presenter productListContractPresenter(ProductListPresenter presenter);

    @ActivityScoped
    @Provides
    static ProductListAdapter productListAdapter() {
        return new ProductListAdapter();
    }

    @FragmentScoped
    @ContributesAndroidInjector
    abstract CartListFragment cartListFragment();

    @ActivityScoped
    @Binds
    abstract CartListContract.Presenter cartListPresenter(CartListPresenter presenter);

    @ActivityScoped
    @Provides
    static CartListAdapter cartListAdapter() {
        return new CartListAdapter();
    }

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AccountFragment accountFragment();
}
