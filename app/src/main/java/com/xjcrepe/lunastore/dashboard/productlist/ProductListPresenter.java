package com.xjcrepe.lunastore.dashboard.productlist;

import android.support.annotation.Nullable;

import com.xjcrepe.lunastore.R;
import com.xjcrepe.lunastore.di.ActivityScoped;
import com.xjcrepe.lunastore.model.Product;
import com.xjcrepe.lunastore.model.Product.ProductCategory;
import com.xjcrepe.lunastore.repo.ProductsRepository;
import com.xjcrepe.lunastore.repo.SampleData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

import static com.xjcrepe.lunastore.model.Product.CATEGORY_ALL;
import static com.xjcrepe.lunastore.model.Product.CATEGORY_ELECTRONICS;
import static com.xjcrepe.lunastore.model.Product.CATEGORY_FURNITURE;

/**
 * Created by LiXijun on 2017/8/20.
 */

@ActivityScoped
public class ProductListPresenter implements ProductListContract.Presenter {

    private final ProductsRepository productsRepository;

    @Nullable
    private ProductListContract.View view;

    @Inject
    ProductListPresenter(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void loadAllProducts() {
        switchViewTitle(CATEGORY_ALL);

        productsRepository.getAllProducts().subscribe(new DisposableSingleObserver<List<Product>>() {
            @Override
            public void onSuccess(List<Product> products) {
                if (view == null)
                    return;

                // simple hack to use local mock data
                // will only run once on app start
                if (products.isEmpty()) {
                    products = SampleData.getProductsMockData();
                    productsRepository.saveProducts(products).subscribe(new DisposableSingleObserver<Boolean>() {
                        @Override
                        public void onSuccess(Boolean aBoolean) {
                            //No-op
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (view == null)
                                return;

                            view.showErrorMessage(e.getLocalizedMessage());
                        }
                    });
                }

                view.showProducts(products);
            }

            @Override
            public void onError(Throwable e) {
                if (view == null)
                    return;

                view.showErrorMessage(e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void loadProductsByCategory(@ProductCategory int category) {
        switchViewTitle(category);

        productsRepository.getProductsByCategory(category)
                .subscribe(new DisposableSingleObserver<List<Product>>() {
                    @Override
                    public void onSuccess(List<Product> products) {
                        if (view == null)
                            return;

                        view.showProducts(products);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view == null)
                            return;

                        view.showErrorMessage(e.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void bindView(ProductListContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    private void switchViewTitle(@ProductCategory int category) {
        if (view == null)
            return;

        switch (category) {
            case CATEGORY_ALL:
                view.showToolbarTitle(R.string.title_all);
                break;
            case CATEGORY_ELECTRONICS:
                view.showToolbarTitle(R.string.title_electronics);
                break;
            case CATEGORY_FURNITURE:
                view.showToolbarTitle(R.string.title_furniture);
                break;
        }
    }
}
