package com.xjcrepe.lunastore.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by LiXijun on 2017/8/20.
 * <p>
 * Model class for all the products in app
 * <p>
 * Using local image res for now
 */

public final class Product implements Parcelable{

    public static final int CATEGORY_ELECTRONICS = 100;
    public static final int CATEGORY_FURNITURE = 101;
    public static final int CATEGORY_ALL = 999;

    private final String id;

    private final String name;

    private final String description;

    private final String price;

    @ProductCategory
    private final int category;

    private final String imageUrl;

    private boolean isInCart = false;

    public Product(String id, String name, String description, String price, @ProductCategory int category, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    protected Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        price = in.readString();
        category = in.readInt();
        imageUrl = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    @ProductCategory
    public int getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isInCart() {
        return isInCart;
    }

    public void setInCart(boolean inCart) {
        isInCart = inCart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (category != product.category) return false;
        if (!id.equals(product.id)) return false;
        if (!name.equals(product.name)) return false;
        return price.equals(product.price);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + category;
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(price);
        parcel.writeInt(category);
        parcel.writeString(imageUrl);
    }

    /**
     * Annotations for product category
     */

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CATEGORY_ELECTRONICS, CATEGORY_FURNITURE, CATEGORY_ALL})
    public @interface ProductCategory {}
}
