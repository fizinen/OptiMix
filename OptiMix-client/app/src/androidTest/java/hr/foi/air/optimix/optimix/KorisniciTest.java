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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class KorisniciTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void korisniciTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.ButtonLogin), withText("Prijava")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.floatingSettingsButton), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withText("Zaposlenici"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.floatingActionButtonAddNewUsers), isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.personName));
        appCompatEditText.perform(scrollTo(), replaceText("testni"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.personSurname));
        appCompatEditText2.perform(scrollTo(), replaceText("korisnik"), closeSoftKeyboard());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.radioChemist), withText("Kemičar"),
                        withParent(withId(R.id.radioAuthorityLevel))));
        appCompatRadioButton.perform(scrollTo(), click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.submitButton), withText("Dodaj")));
        appCompatButton2.perform(scrollTo(), click());

    }

}
