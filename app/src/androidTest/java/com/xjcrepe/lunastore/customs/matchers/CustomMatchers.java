package com.xjcrepe.lunastore.customs.matchers;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.Toolbar;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by xijunli on 30/8/17.
 */

public class CustomMatchers {

    public static Matcher<Object> withToolbarTitle(final Matcher<CharSequence> textMatcher) {
        return new BoundedMatcher<Object, Toolbar>(Toolbar.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("With Toolbar Title");
                textMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(Toolbar item) {
                return textMatcher.matches(item.getTitle());
            }
        };
    }
}
