package com.xjcrepe.lunastore.repo;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiXijun on 2017/8/20.
 */

@Module
public class ProductsRepositoryModule {

    @Provides
    @Singleton
    public ProductsRepository provideProductsRepository(Context context) {
        return new ProductsRepository(context);
    }
}
