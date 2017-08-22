package com.xjcrepe.lunastore.detail;

import com.xjcrepe.lunastore.base.BasePresenter;
import com.xjcrepe.lunastore.model.Product;

/**
 * Created by LiXijun on 2017/8/22.
 */

public interface ProductDetailContract {

    interface View {

        void showProduct(Product product);

        void showMessage(String message);

        void showAddCartSuccessMessage();

        void showAddCartFailedMessage();
    }

    interface Presenter extends BasePresenter<View> {

        void loadProductById(String id);

        void addProductToCart(String id);
    }
}
