package com.xjcrepe.lunastore.repo;

import com.xjcrepe.lunastore.model.Product;
import com.xjcrepe.lunastore.model.Product.ProductCategory;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by LiXijun on 2017/8/20.
 */

public interface ProductsDataSource {

    Single<Boolean> saveProducts(List<Product> products);

    Single<List<Product>> getAllProducts();

    Single<List<Product>> getProductsByCategory(@ProductCategory int category);

    Single<List<Product>> getProductsInCart();

    Single<Product> getProductById(String id);

    Single<Boolean> addToCart(String id);

    Single<Boolean> removeFromCart(String id);
}
