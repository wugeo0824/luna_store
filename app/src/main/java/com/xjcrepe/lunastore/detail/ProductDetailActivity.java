package com.xjcrepe.lunastore.detail;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xjcrepe.lunastore.R;
import com.xjcrepe.lunastore.model.Product;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by LiXijun on 2017/8/21.
 */

public class ProductDetailActivity extends DaggerAppCompatActivity implements ProductDetailContract.View{

    public static final String EXTRA_PRODUCT_ID = "extra_product_id";

    @Inject
    ProductDetailContract.Presenter presenter;

    @BindView(R.id.ivDetailImage)
    ImageView ivImage;

    @BindView(R.id.tvDetailName)
    TextView tvName;

    @BindView(R.id.tvDetailPrice)
    TextView tvPrice;

    @BindView(R.id.tvDetailDescription)
    TextView tvDescription;

    @BindView(R.id.fabAddToCart)
    FloatingActionButton fabAddCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ButterKnife.bind(this);
        setTitle(R.string.title_detail);

        presenter.bindView(this);

        final String productId = getIntent().getStringExtra(EXTRA_PRODUCT_ID);
        presenter.loadProductById(productId);

        fabAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addProductToCart(productId);
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.unbindView();
        super.onDestroy();
    }

    @Override
    public void showProduct(Product product) {
        Glide.with(this)
                .load(product.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(ivImage);

        tvName.setText(product.getName());
        tvPrice.setText(product.getPrice());
        tvDescription.setText(product.getDescription());
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(ivImage, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showAddCartSuccessMessage() {
        Snackbar.make(ivImage, R.string.cart_add_message_success, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showAddCartFailedMessage() {
        Snackbar.make(ivImage, R.string.cart_add_message_failed, Snackbar.LENGTH_SHORT).show();
    }
}
