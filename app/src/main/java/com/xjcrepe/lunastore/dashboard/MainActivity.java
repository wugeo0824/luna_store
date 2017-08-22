package com.xjcrepe.lunastore.dashboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.xjcrepe.lunastore.R;
import com.xjcrepe.lunastore.dashboard.account.AccountFragment;
import com.xjcrepe.lunastore.dashboard.cart.CartListFragment;
import com.xjcrepe.lunastore.dashboard.productlist.ProductListFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private static final String TAG_PRODUCT_LIST_FRAGMENT = "products_fragment";
    private static final String TAG_CART_LIST_FRAGMENT = "cart_fragment";
    private static final String TAG_ACCOUNT_FRAGMENT = "account_fragment";

    private final int containerId = R.id.flContainer;

    @Inject
    Lazy<ProductListFragment> productListFragmentLazy;

    @Inject
    Lazy<CartListFragment> cartListFragmentLazy;

    @Inject
    Lazy<AccountFragment> accountFragmentLazy;

    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return switchFragmentByBottomNavigationItem(item.getItemId());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.navigation_products);
        }
    }

    private boolean switchFragmentByBottomNavigationItem(int itemId) {
        switch (itemId) {
            case R.id.navigation_products:
                ProductListFragment productListFragment = (ProductListFragment) getSupportFragmentManager().findFragmentByTag(TAG_PRODUCT_LIST_FRAGMENT);
                if (productListFragment == null) {
                    productListFragment = productListFragmentLazy.get();
                }
                replaceFragment(productListFragment, TAG_PRODUCT_LIST_FRAGMENT);
                return true;
            case R.id.navigation_cart:
                CartListFragment cartListFragment = (CartListFragment) getSupportFragmentManager().findFragmentByTag(TAG_CART_LIST_FRAGMENT);
                if (cartListFragment == null) {
                    cartListFragment = cartListFragmentLazy.get();
                }
                replaceFragment(cartListFragment, TAG_CART_LIST_FRAGMENT);
                return true;
            case R.id.navigation_account:
                AccountFragment accountFragment = (AccountFragment) getSupportFragmentManager().findFragmentByTag(TAG_ACCOUNT_FRAGMENT);
                if (accountFragment == null) {
                    accountFragment = accountFragmentLazy.get();
                }
                replaceFragment(accountFragment, TAG_ACCOUNT_FRAGMENT);
                return true;
        }

        return false;
    }

    private void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(containerId, fragment, tag);
        transaction.commit();
    }
}
