package com.xjcrepe.lunastore.dashboard.productlist;

import android.support.annotation.StringRes;

import com.xjcrepe.lunastore.base.BasePresenter;
import com.xjcrepe.lunastore.model.Product;
import com.xjcrepe.lunastore.model.Product.ProductCategory;

import java.util.List;

/**
 * Created by LiXijun on 2017/8/20.
 */

public interface ProductListContract {

    interface View {

        void showProducts(List<Product> products);

        void showErrorMessage(String errorMsg);

        void showToolbarTitle(@StringRes int titleResId);
    }

    interface Presenter extends BasePresenter<View> {

        void loadAllProducts();
        
        void loadProductsByCategory(@ProductCategory int category);
    }
}
