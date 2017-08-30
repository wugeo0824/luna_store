package com.xjcrepe.lunastore.dashboard.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.xjcrepe.lunastore.R;
import com.xjcrepe.lunastore.dashboard.productlist.list.ProductListAdapter;
import com.xjcrepe.lunastore.detail.ProductDetailActivity;
import com.xjcrepe.lunastore.di.ActivityScoped;
import com.xjcrepe.lunastore.model.Product;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.xjcrepe.lunastore.detail.ProductDetailActivity.EXTRA_PRODUCT_ID;
import static com.xjcrepe.lunastore.model.Product.CATEGORY_ALL;
import static com.xjcrepe.lunastore.model.Product.CATEGORY_ELECTRONICS;
import static com.xjcrepe.lunastore.model.Product.CATEGORY_FURNITURE;

/**
 * Created by LiXijun on 2017/8/20.
 */

@ActivityScoped
public class ProductListFragment extends Fragment implements ProductListContract.View, ProductListAdapter.ItemClickListener {

    private static final String KEY_CURRENT_FILTER_CATEGORY = "current_filter";

    @Inject
    ProductListContract.Presenter presenter;

    @Inject
    ProductListAdapter productListAdapter;

    @BindView(R.id.rvProducts)
    RecyclerView rvProducts;

    private int filterCategory = CATEGORY_ALL;

    @Inject
    public ProductListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_product_list, container, false);
        ButterKnife.bind(this, root);
        initViews();
        setHasOptionsMenu(true);
        presenter.bindView(this);
        if (savedInstanceState != null) {
            int filter = savedInstanceState.getInt(KEY_CURRENT_FILTER_CATEGORY, CATEGORY_ALL);
            if (filter == CATEGORY_ALL) {
                presenter.loadAllProducts();
            } else {
                presenter.loadProductsByCategory(filter);
            }
        } else {
            presenter.loadAllProducts();
        }
        return root;
    }

    private void initViews() {
        productListAdapter.setItemClickListener(this);
        rvProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvProducts.setAdapter(productListAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_CURRENT_FILTER_CATEGORY, filterCategory);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        presenter.unbindView();
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_product_list_filter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_all:
                presenter.loadAllProducts();
                return true;
            case R.id.filter_electronics:
                presenter.loadProductsByCategory(CATEGORY_ELECTRONICS);
                return true;
            case R.id.filter_furniture:
                presenter.loadProductsByCategory(CATEGORY_FURNITURE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProducts(List<Product> products) {
        productListAdapter.refreshData(products);
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        Snackbar.make(rvProducts, errorMsg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showToolbarTitle(@StringRes int titleResId) {
        getActivity().setTitle(getString(titleResId));
    }

    @Override
    public void onItemClick(Product product) {
        navigateToDetailView(product);
    }

    private void navigateToDetailView(Product product) {
        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, product.getId());
        getActivity().startActivity(intent);
    }
}
