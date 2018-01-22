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
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class IzracunTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void izracunTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.ButtonLogin), withText("Prijava")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction appCompatTextView = onView(
                allOf(withText("Izračun"), isDisplayed()));
        appCompatTextView.perform(click());

 //       ViewInteraction viewPager = onView(
//                allOf(withId(R.id.activity_main_view_pager), isDisplayed()));
  //      viewPager.perform(swipeLeft());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.recipe_amount), isDisplayed()));
        appCompatEditText.perform(replaceText("25"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.preview_of_calculations), withText("Ispis izračuna"),
                        withParent(withId(R.id.relativ_tata)),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.document_create_pdf), withText("Kreiraj pdf"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.document_print_modular), withText("Ispiši dokument"), isDisplayed()));
        appCompatButton4.perform(click());

    }

}
