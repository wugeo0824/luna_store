package com.xjcrepe.lunastore.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xjcrepe.lunastore.model.Product;
import com.xjcrepe.lunastore.model.ProductReaderContract.ProductEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by LiXijun on 2017/8/20.
 */

@Singleton
public class ProductsRepository implements ProductsDataSource {

    private final ProductsDBHelper dbHelper;

    private String[] productProjection = {
            ProductEntry.COLUMN_NAME_ENTRY_ID,
            ProductEntry.COLUMN_NAME_NAME,
            ProductEntry.COLUMN_NAME_DESCRIPTION,
            ProductEntry.COLUMN_NAME_PRICE,
            ProductEntry.COLUMN_NAME_CATEGORY,
            ProductEntry.COLUMN_NAME_IMAGE_URL,
            ProductEntry.COLUMN_NAME_IN_CART
    };

    @Inject
    public ProductsRepository(Context context) {
        this.dbHelper = new ProductsDBHelper(context);
    }

    @Override
    public Single<Boolean> saveProducts(final List<Product> products) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                for (Product product : products) {
                    db.insert(ProductEntry.TABLE_NAME_PRODUCTS, null, createContentValuesFromProduct(product));
                }
                return true;
            }
        });
    }

    @Override
    public Single<List<Product>> getAllProducts() {
        return Single.fromCallable(new Callable<List<Product>>() {
            @Override
            public List<Product> call() throws Exception {
                List<Product> result = new ArrayList<>();
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                Cursor c = db.query(ProductEntry.TABLE_NAME_PRODUCTS, productProjection, null, null, null, null, null);

                if (c != null && c.getCount() > 0) {
                    while (c.moveToNext()) {
                        result.add(queryCursorAndCreateProduct(c));
                    }
                }
                if (c != null) {
                    c.close();
                }

                db.close();

                return result;
            }
        });
    }

    @Override
    public Single<List<Product>> getProductsByCategory(final int category) {
        return Single.fromCallable(new Callable<List<Product>>() {
            @Override
            public List<Product> call() throws Exception {
                List<Product> result = new ArrayList<>();
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                String selection = ProductEntry.COLUMN_NAME_CATEGORY + " LIKE ?";
                String[] selectionArgs = {"" + category};

                Cursor c = db.query(ProductEntry.TABLE_NAME_PRODUCTS, productProjection, selection, selectionArgs, null, null, null);

                if (c != null && c.getCount() > 0) {
                    while (c.moveToNext()) {
                        result.add(queryCursorAndCreateProduct(c));
                    }
                }
                if (c != null) {
                    c.close();
                }

                db.close();

                return result;
            }
        });
    }

    @Override
    public Single<List<Product>> getProductsInCart() {
        return Single.fromCallable(new Callable<List<Product>>() {
            @Override
            public List<Product> call() throws Exception {
                List<Product> result = new ArrayList<>();
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                String selection = ProductEntry.COLUMN_NAME_IN_CART + " LIKE ?";
                String[] selectionArgs = { "1" };

                Cursor c = db.query(ProductEntry.TABLE_NAME_PRODUCTS, productProjection, selection, selectionArgs, null, null, null);

                if (c != null && c.getCount() > 0) {
                    while (c.moveToNext()) {
                        result.add(queryCursorAndCreateProduct(c));
                    }
                }
                if (c != null) {
                    c.close();
                }

                db.close();

                return result;
            }
        });
    }

    @Override
    public Single<Product> getProductById(final String id) {
        return Single.fromCallable(new Callable<Product>() {
            @Override
            public Product call() throws Exception {
                Product result = null;

                SQLiteDatabase db = dbHelper.getReadableDatabase();

                String selection = ProductEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
                String[] selectionArgs = {id};

                Cursor c = db.query(ProductEntry.TABLE_NAME_PRODUCTS, productProjection, selection, selectionArgs, null, null, null);

                if (c != null && c.getCount() > 0) {
                    while (c.moveToNext()) {
                        result = queryCursorAndCreateProduct(c);
                    }
                }
                if (c != null) {
                    c.close();
                }

                db.close();

                return result;
            }
        });
    }

    @Override
    public Single<Boolean> addToCart(final String id) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                ContentValues values = new ContentValues();
                values.put(ProductEntry.COLUMN_NAME_IN_CART, true);

                String selection = ProductEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
                String[] selectionArgs = {id};

                int rows = db.update(ProductEntry.TABLE_NAME_PRODUCTS, values, selection, selectionArgs);

                db.close();

                return rows > 0;
            }
        });
    }

    @Override
    public Single<Boolean> removeFromCart(final String id) {
        return Single.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                ContentValues values = new ContentValues();
                values.put(ProductEntry.COLUMN_NAME_IN_CART, false);

                String selection = ProductEntry.COLUMN_NAME_ENTRY_ID + " LIKE ?";
                String[] selectionArgs = {id};

                db.update(ProductEntry.TABLE_NAME_PRODUCTS, values, selection, selectionArgs);

                db.close();

                return true;
            }
        });
    }

    private Product queryCursorAndCreateProduct(Cursor c) {
        String id = c.getString(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_ENTRY_ID));
        String name = c.getString(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_NAME));
        String description = c.getString(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_DESCRIPTION));
        String price = c.getString(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_PRICE));
        int category = c.getInt(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_CATEGORY));
        String imageUrl = c.getString(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_IMAGE_URL));
        boolean isInCart = c.getInt(c.getColumnIndexOrThrow(ProductEntry.COLUMN_NAME_IN_CART)) == 1;
        Product product = new Product(id, name, description, price, category, imageUrl);
        product.setInCart(isInCart);
        return product;
    }

    private ContentValues createContentValuesFromProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_NAME_ENTRY_ID, product.getId());
        values.put(ProductEntry.COLUMN_NAME_NAME, product.getName());
        values.put(ProductEntry.COLUMN_NAME_DESCRIPTION, product.getDescription());
        values.put(ProductEntry.COLUMN_NAME_PRICE, product.getPrice());
        values.put(ProductEntry.COLUMN_NAME_CATEGORY, product.getCategory());
        values.put(ProductEntry.COLUMN_NAME_IMAGE_URL, product.getImageUrl());
        values.put(ProductEntry.COLUMN_NAME_IN_CART, product.isInCart());
        return values;
    }
}
