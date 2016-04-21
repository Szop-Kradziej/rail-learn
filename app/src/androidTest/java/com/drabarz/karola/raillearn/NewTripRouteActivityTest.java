package com.drabarz.karola.raillearn;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.drabarz.karola.raillearn.trip.create.NewTripRouteActivity;

import org.junit.Rule;
import org.junit.Test;

public class NewTripRouteActivityTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(NewTripRouteActivity.class);

    @Test
    public void shouldStartNewTripOfferActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.confirmRouteButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.newTripOfferLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
