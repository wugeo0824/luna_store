package com.xjcrepe.lunastore.di;

import android.app.Application;

import com.xjcrepe.lunastore.LunaStoreApplication;
import com.xjcrepe.lunastore.repo.ProductsRepository;
import com.xjcrepe.lunastore.repo.ProductsRepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by LiXijun on 2017/8/20.
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ActivityBuilder.class, ProductsRepositoryModule.class})
public interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(LunaStoreApplication application);

    ProductsRepository getProductsRepository();

}
