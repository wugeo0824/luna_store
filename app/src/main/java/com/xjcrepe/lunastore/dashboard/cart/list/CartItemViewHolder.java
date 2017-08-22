package com.xjcrepe.lunastore.dashboard.cart.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xjcrepe.lunastore.R;
import com.xjcrepe.lunastore.dashboard.cart.list.CartListAdapter.CartItemListener;
import com.xjcrepe.lunastore.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LiXijun on 2017/8/22.
 */

public class CartItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivProductImage)
    ImageView ivImage;

    @BindView(R.id.tvProductName)
    TextView tvName;

    @BindView(R.id.tvProductPrice)
    TextView tvPrice;

    @BindView(R.id.btnDelete)
    Button btnDelete;

    private final CartItemListener cartItemListener;

    public static CartItemViewHolder newInstance(ViewGroup parent, CartItemListener cartItemListener) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_cart, parent, false);
        return new CartItemViewHolder(root, cartItemListener);
    }

    private CartItemViewHolder(View itemView, CartItemListener cartItemListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.cartItemListener = cartItemListener;
    }

    public void update(final Product product) {
        Glide.with(itemView.getContext())
                .load(product.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(ivImage);

        tvName.setText(product.getName());
        tvPrice.setText(product.getPrice());
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartItemListener != null) {
                    cartItemListener.onRemoveClicked(product, getAdapterPosition());
                }
            }
        });
    }
}
