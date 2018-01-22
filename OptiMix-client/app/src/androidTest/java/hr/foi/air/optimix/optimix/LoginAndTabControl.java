package hr.foi.air.optimix.optimix;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginAndTabControl {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginAndTabControl() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.ButtonLogin), withText("Prijava")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatTextView = onView(
                allOf(withText("Izračun"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.activity_main_view_pager), isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withText("Analiza"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction viewPager2 = onView(
                allOf(withId(R.id.activity_main_view_pager), isDisplayed()));
        viewPager2.perform(swipeLeft());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withText("Izračun"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction viewPager3 = onView(
                allOf(withId(R.id.activity_main_view_pager), isDisplayed()));
        viewPager3.perform(swipeRight());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withText("Skladište"), isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction viewPager4 = onView(
                allOf(withId(R.id.activity_main_view_pager), isDisplayed()));
        viewPager4.perform(swipeRight());

        ViewInteraction appCompatTextView5 = onView(
                allOf(withText("Izračun"), isDisplayed()));
        appCompatTextView5.perform(click());

        ViewInteraction viewPager5 = onView(
                allOf(withId(R.id.activity_main_view_pager), isDisplayed()));
        viewPager5.perform(swipeLeft());

    }

}
