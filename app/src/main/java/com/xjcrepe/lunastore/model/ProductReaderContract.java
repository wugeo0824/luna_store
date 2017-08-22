package com.xjcrepe.lunastore.model;

import android.provider.BaseColumns;

/**
 * Created by LiXijun on 2017/8/22.
 */

public final class ProductReaderContract implements BaseColumns {

    private ProductReaderContract() {
    }

    public static abstract class ProductEntry {
        public static final String TABLE_NAME_PRODUCTS = "products";

        public static final String COLUMN_NAME_ENTRY_ID = "entry_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_IMAGE_URL = "image_url";
        public static final String COLUMN_NAME_IN_CART = "in_cart";
    }
}
