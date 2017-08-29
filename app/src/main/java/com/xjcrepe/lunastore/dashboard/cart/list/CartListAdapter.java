package com.xjcrepe.lunastore.dashboard.cart.list;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xjcrepe.lunastore.model.Product;

import java.util.List;

/**
 * Created by LiXijun on 2017/8/22.
 */

public class CartListAdapter extends RecyclerView.Adapter<CartItemViewHolder> {

    @Nullable
    private List<Product> products;

    private CartItemListener cartItemListener;

    public CartListAdapter() {
    }

    public void setCartItemListener(CartItemListener cartItemListener) {
        this.cartItemListener = cartItemListener;
    }

    public void refreshData(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public CartItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CartItemViewHolder.newInstance(parent, cartItemListener);
    }

    @Override
    public void onBindViewHolder(CartItemViewHolder holder, int position) {
        if (products == null)
            return;

        Product product = products.get(position);
        holder.update(product);
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    public void removeItemAt(int index) {
        products.remove(index);
        notifyItemRemoved(index);

        if (cartItemListener != null && products.isEmpty()) {
            cartItemListener.onCartEmpty();
        }
    }

    public interface CartItemListener {
        void onRemoveClicked(Product product, int position);

        void onCartEmpty();
    }
}
