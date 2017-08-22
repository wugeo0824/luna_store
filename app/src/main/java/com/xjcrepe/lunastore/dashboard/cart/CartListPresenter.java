package com.xjcrepe.lunastore.dashboard.cart;

import android.support.annotation.Nullable;

import com.xjcrepe.lunastore.model.Product;
import com.xjcrepe.lunastore.repo.ProductsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by LiXijun on 2017/8/21.
 */

public class CartListPresenter implements CartListContract.Presenter {

    private final ProductsRepository productsRepository;

    @Nullable
    private CartListContract.View view;

    @Inject
    public CartListPresenter(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void bindView(CartListContract.View view) {
        this.view = view;
    }

    @Override
    public void unbindView() {
        view = null;
    }

    @Override
    public void loadProductsInCart() {
        productsRepository.getProductsInCart()
                .subscribe(new DisposableSingleObserver<List<Product>>() {
                    @Override
                    public void onSuccess(List<Product> products) {
                        if (view == null)
                            return;

                        if (products.isEmpty()) {
                            view.showEmptyView();
                        } else {
                            view.showProductsInCart(products);
                        }
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
    public void removeProductFromCart(Product product, final int position) {
        productsRepository.removeFromCart(product.getId())
                .subscribe(new DisposableSingleObserver<Boolean>() {
                    @Override
                    public void onSuccess(Boolean ignored) {
                        if (view == null)
                            return;

                        view.removeProductFromList(position);
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
