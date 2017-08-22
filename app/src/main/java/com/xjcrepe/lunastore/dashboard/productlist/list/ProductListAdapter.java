package com.xjcrepe.lunastore.dashboard.productlist.list;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xjcrepe.lunastore.di.ActivityScoped;
import com.xjcrepe.lunastore.model.Product;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by LiXijun on 2017/8/21.
 */

@ActivityScoped
public class ProductListAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    @Nullable
    private List<Product> products;

    private ItemClickListener itemClickListener;

    @Inject
    public ProductListAdapter() {
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void refreshData(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ProductViewHolder.newInstance(parent, itemClickListener);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        if (products == null)
            return;

        Product product = products.get(position);
        holder.update(product);
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    public interface ItemClickListener {
        void onItemClick(Product product);
    }
}
