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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AdminOptionsTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void adminOptionsTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.ButtonLogin), withText("Prijava")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.floatingSettingsButton), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withText("Zaposlenici"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.activity_settings_view_pager),
                        withParent(allOf(withId(R.id.main_layout),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.floatingActionButtonAddNewUsers), isDisplayed()));
        floatingActionButton2.perform(click());

    }

}
