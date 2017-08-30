package com.xjcrepe.lunastore.dashboard.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xjcrepe.lunastore.R;
import com.xjcrepe.lunastore.dashboard.cart.list.CartListAdapter;
import com.xjcrepe.lunastore.model.Product;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LiXijun on 2017/8/20.
 */

public class CartListFragment extends Fragment implements CartListContract.View, CartListAdapter.CartItemListener{

    @BindView(R.id.rvCart)
    RecyclerView rvCart;

    @BindView(R.id.tvCartEmpty)
    TextView tvCartEmpty;

    @Inject
    CartListAdapter listAdapter;

    @Inject
    CartListContract.Presenter presenter;

    @Inject
    public CartListFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cart_list, container, false);
        ButterKnife.bind(this, root);
        getActivity().setTitle(R.string.title_cart);
        initViews();
        presenter.bindView(this);
        presenter.loadProductsInCart();
        return root;
    }

    private void initViews() {
        listAdapter.setCartItemListener(this);
        rvCart.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCart.setItemAnimator(new DefaultItemAnimator());
        rvCart.setAdapter(listAdapter);
    }

    @Override
    public void onDestroyView() {
        presenter.unbindView();
        super.onDestroyView();
    }

    @Override
    public void showProductsInCart(List<Product> products) {
        rvCart.setVisibility(View.VISIBLE);
        tvCartEmpty.setVisibility(View.GONE);
        listAdapter.refreshData(products);
    }

    @Override
    public void showEmptyView() {
        rvCart.setVisibility(View.GONE);
        tvCartEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeProductFromList(int position) {
        listAdapter.removeItemAt(position);
        Snackbar.make(rvCart, R.string.cart_message_removed, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(rvCart, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRemoveClicked(Product product, int position) {
        presenter.removeProductFromCart(product, position);
    }

    @Override
    public void onCartEmpty() {
        showEmptyView();
    }
}
