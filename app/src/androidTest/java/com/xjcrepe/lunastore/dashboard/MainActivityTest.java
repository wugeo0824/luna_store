package com.xjcrepe.lunastore.dashboard;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;

import com.xjcrepe.lunastore.R;
import com.xjcrepe.lunastore.model.Product;
import com.xjcrepe.lunastore.repo.SampleData;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.xjcrepe.lunastore.customs.matchers.CustomMatchers.withToolbarTitle;
import static com.xjcrepe.lunastore.customs.matchers.RecyclerViewMatcher.withRecyclerView;
import static org.hamcrest.Matchers.is;

/**
 * Created by xijunli on 30/8/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private final Product firstProduct = SampleData.getProductsMockData().get(0);
    private final Product secondProduct = SampleData.getProductsMockData().get(1);

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void onLaunch_showsListOfProducts() {
        onView(withRecyclerView(R.id.rvProducts).atPositionOnView(0, R.id.tvProductName))
                .check(matches(withText(firstProduct.getName())));
        onView(withRecyclerView(R.id.rvProducts).atPositionOnView(0, R.id.tvProductPrice))
                .check(matches(withText(firstProduct.getPrice())));
        onView(withRecyclerView(R.id.rvProducts).atPositionOnView(0, R.id.tvProductDescription))
                .check(matches(withText(firstProduct.getDescription())));

        onView(withRecyclerView(R.id.rvProducts).atPositionOnView(1, R.id.tvProductName))
                .check(matches(withText(secondProduct.getName())));
        onView(withRecyclerView(R.id.rvProducts).atPositionOnView(1, R.id.tvProductPrice))
                .check(matches(withText(secondProduct.getPrice())));
        onView(withRecyclerView(R.id.rvProducts).atPositionOnView(1, R.id.tvProductDescription))
                .check(matches(withText(secondProduct.getDescription())));
    }

    @Test
    public void clickOnProduct_navigatesToDetailScreen() {
        onView(withId(R.id.rvProducts)).perform(actionOnItemAtPosition(1, click()));

        CharSequence detailTitle = InstrumentationRegistry.getTargetContext().getString(R.string.title_detail);
        
        onView(isAssignableFrom(Toolbar.class))
                .check(matches(withToolbarTitle(is(detailTitle))));
    }
}