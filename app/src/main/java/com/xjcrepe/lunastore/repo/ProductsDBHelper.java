package com.xjcrepe.lunastore.repo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xjcrepe.lunastore.model.ProductReaderContract;

/**
 * Created by LiXijun on 2017/8/22.
 */

public class ProductsDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Products.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProductReaderContract.ProductEntry.TABLE_NAME_PRODUCTS + " (" +
                    ProductReaderContract.ProductEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + " PRIMARY KEY," +
                    ProductReaderContract.ProductEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    ProductReaderContract.ProductEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    ProductReaderContract.ProductEntry.COLUMN_NAME_PRICE + TEXT_TYPE + COMMA_SEP +
                    ProductReaderContract.ProductEntry.COLUMN_NAME_CATEGORY + INTEGER_TYPE + COMMA_SEP +
                    ProductReaderContract.ProductEntry.COLUMN_NAME_IMAGE_URL + TEXT_TYPE + COMMA_SEP +
                    ProductReaderContract.ProductEntry.COLUMN_NAME_IN_CART + BOOLEAN_TYPE +
                    " )";

    public ProductsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
