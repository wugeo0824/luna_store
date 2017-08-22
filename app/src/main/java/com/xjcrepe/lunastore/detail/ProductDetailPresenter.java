package com.xjcrepe.lunastore.detail;

import android.support.annotation.Nullable;

import com.xjcrepe.lunastore.model.Product;
import com.xjcrepe.lunastore.repo.ProductsRepository;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by LiXijun on 2017/8/22.
 */

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private final ProductsRepository productsRepository;

    @Nullable
    private ProductDetailContract.View view;

    @Inject
    public ProductDetailPresenter(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void bindView(ProductDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void loadProductById(String id) {
        productsRepository.getProductById(id)
                .subscribe(new DisposableSingleObserver<Product>() {
                    @Override
                    public void onSuccess(Product product) {
                        if (view == null)
                            return;

                        view.showProduct(product);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view == null)
                            return;

                        view.showMessage(e.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void addProductToCart(String id) {
        productsRepository.addToCart(id)
                .subscribe(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onSuccess(Boolean success) {
                        if (view == null)
                            return;

                        if (success)
                            view.showAddCartSuccessMessage();
                        else
                            view.showAddCartFailedMessage();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view == null)
                            return;

                        view.showMessage(e.getLocalizedMessage());
                    }
                });
    }
}
