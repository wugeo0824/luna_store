package com.xjcrepe.lunastore.detail;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.xjcrepe.lunastore.R;
import com.xjcrepe.lunastore.model.Product;
import com.xjcrepe.lunastore.repo.SampleData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by xijunli on 29/8/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProductDetailActivityTest {

    private final Product productInTest = SampleData.getProductsMockData().get(0);

    @Rule
    public ActivityTestRule<ProductDetailActivity> productDetailActivityActivityTestRule =
            new ActivityTestRule<>(ProductDetailActivity.class, true, false);

    @Before
    public void setUp() {
        Intent intent = new Intent();
        intent.putExtra(ProductDetailActivity.EXTRA_PRODUCT_ID, productInTest.getId());
        productDetailActivityActivityTestRule.launchActivity(intent);
    }

    @Test
    public void productDetails_showDetailsOnScreen() {
        onView(withId(R.id.tvDetailName)).check(matches(withText(productInTest.getName())));
        onView(withId(R.id.tvDetailPrice)).check(matches(withText(productInTest.getPrice())));
        onView(withId(R.id.tvDetailDescription)).check(matches(withText(productInTest.getDescription())));
    }

    @Test
    public void addToCart_showSuccessMessage() {
        onView(withId(R.id.fabAddToCart)).perform(click());

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.cart_add_message_success)))
                .check(matches(isDisplayed()));
    }
}