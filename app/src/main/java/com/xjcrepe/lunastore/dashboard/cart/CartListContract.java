package com.xjcrepe.lunastore.dashboard.cart;

import com.xjcrepe.lunastore.base.BasePresenter;
import com.xjcrepe.lunastore.model.Product;

import java.util.List;

/**
 * Created by LiXijun on 2017/8/21.
 */

public interface CartListContract {
    interface View {

        void showProductsInCart(List<Product> products);

        void showEmptyView();

        void removeProductFromList(int position);

        void showMessage(String message);
    }

    interface Presenter extends BasePresenter<View> {

        void loadProductsInCart();

        void removeProductFromCart(Product product, int position);
    }
}
