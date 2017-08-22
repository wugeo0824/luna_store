package com.xjcrepe.lunastore.repo;

import com.xjcrepe.lunastore.model.Product;
import com.xjcrepe.lunastore.model.Product.ProductCategory;

import java.util.ArrayList;
import java.util.List;

import static com.xjcrepe.lunastore.model.Product.CATEGORY_ELECTRONICS;

/**
 * Created by LiXijun on 2017/8/20.
 */

public class SampleData {

    private final static List<Product> PRODUCTS_MOCK_DATA;

    static {
        PRODUCTS_MOCK_DATA = new ArrayList<>(6);

        //Electronics
        addProduct("ele001",
                "Microwave Oven",
                "A big metal box that heats up food!",
                "S$ 159.9",
                CATEGORY_ELECTRONICS,
                "http://www.pngmart.com/files/2/Microwave-Oven-PNG-Image.png");

        addProduct("ele002",
                "Television",
                " A telecommunication medium used for transmitting moving images in color, and in two or three dimensions and sound. ",
                "S$ 1799.9",
                CATEGORY_ELECTRONICS,
                "http://www.pngmart.com/files/1/Old-TV-PNG.png");

        addProduct("ele003",
                "Vacuum Cleaner",
                "Keeps your floor clean with little effort",
                "S$ 349.9",
                CATEGORY_ELECTRONICS,
                "https://i.pinimg.com/736x/27/1a/f2/271af23f2c93e1cc121925c0f2b9c9c8--vacuum-cleaners-vacuums.jpg");

        //Furniture
        addProduct("fur001",
                "Table",
                "You can eat, write and read on this.",
                "S$ 66.0",
                Product.CATEGORY_FURNITURE,
               "http://pngimg.com/uploads/table/table_PNG7003.png");

        addProduct("fur002",
                "Chair",
                "Come here and have a rest",
                "S$ 25.0",
                Product.CATEGORY_FURNITURE,
                "http://pngimg.com/uploads/chair/chair_PNG6908.png");

        addProduct("fur003",
                "Almirah",
                "A piece of furniture which has multiple parallel, horizontal drawers stacked one above each other, used mainly for the storage of clean clothes.",
                "S$ 120.0",
                Product.CATEGORY_FURNITURE,
                "https://i2.wp.com/pleasantfurnitures.com/wp-content/uploads/2016/05/Godrej-Almirah-1.png?fit=512%2C512");
    }

    private static void addProduct(String id,
                                   String name,
                                   String description,
                                   String price,
                                   @ProductCategory int category,
                                   String imageUrl) {
        Product product = new Product(id, name, description, price, category, imageUrl);
        PRODUCTS_MOCK_DATA.add(product);
    }

    public static List<Product> getProductsMockData() {
        return PRODUCTS_MOCK_DATA;
    }
}
