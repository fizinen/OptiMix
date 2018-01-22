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
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AnalizaTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void analizaTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.ButtonLogin), withText("Prijava")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatTextView = onView(
                allOf(withText("Analiza"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(R.id.activity_main_view_pager), isDisplayed()));
        viewPager.perform(swipeLeft());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.analysisUInput));
        appCompatEditText.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.analysisUInput));
        appCompatEditText2.perform(scrollTo(), replaceText("45"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                withId(R.id.analysisXInput));
        appCompatEditText3.perform(scrollTo(), replaceText("12122018"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                withId(R.id.analysis_raw_amount_input));
        appCompatEditText4.perform(scrollTo(), replaceText("1000"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                withId(R.id.analysis_water_input));
        appCompatEditText5.perform(scrollTo(), replaceText("12"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                withId(R.id.analysis_fat_input));
        appCompatEditText6.perform(scrollTo(), replaceText("17"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                withId(R.id.analysis_proteins_input));
        appCompatEditText7.perform(scrollTo(), replaceText("28"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.submitAnalysisButton), withText("Dodaj")));
        appCompatButton2.perform(scrollTo(), click());

    }

}
