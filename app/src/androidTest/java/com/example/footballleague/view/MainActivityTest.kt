package com.example.footballleague.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.footballleague.R
import com.example.footballleague.view.fragment.EventsFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testSearchViewBehaviour() {
        val scenario = launchFragmentInContainer<EventsFragment>()
        onView(withId(R.id.svEvents)).check(matches(isDisplayed()));
        onView(withId(R.id.svEvents)).perform(click());
        onView(withId(R.id.svEvents)).perform(typeText("england"))
    }
}