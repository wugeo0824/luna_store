package com.xjcrepe.lunastore.dashboard.productlist.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xjcrepe.lunastore.R;
import com.xjcrepe.lunastore.model.Product;
import com.xjcrepe.lunastore.dashboard.productlist.list.ProductListAdapter.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LiXijun on 2017/8/21.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivProductImage)
    ImageView ivImage;

    @BindView(R.id.tvProductName)
    TextView tvName;

    @BindView(R.id.tvProductPrice)
    TextView tvPrice;

    @BindView(R.id.tvProductDescription)
    TextView tvDescription;

    private final ItemClickListener itemClickListener;

    public static ProductViewHolder newInstance(ViewGroup parent, ItemClickListener itemClickListener) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(root, itemClickListener);
    }

    private ProductViewHolder(View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemClickListener = itemClickListener;
    }

    public void update(@NonNull final Product product) {
        Glide.with(itemView.getContext())
                .load(product.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .into(ivImage);

        tvName.setText(product.getName());
        tvPrice.setText(product.getPrice());
        tvDescription.setText(product.getDescription());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(product);
                }
            }
        });
    }
}
