package com.drabarz.karola.raillearn;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.drabarz.karola.raillearn.trip.list.TripsActivity;

import org.junit.Rule;
import org.junit.Test;

public class TripsActivityTest {

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(TripsActivity.class);

    @Test
    public void shouldStartFullTripsActivity() throws InterruptedException {
        Thread.sleep(4000);
        Espresso.onView(ViewMatchers.withId(R.id.tripFieldLayout)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.tripDescriptionTextView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void shouldStartNewTripActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.newTripButton)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.newTripLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

}
