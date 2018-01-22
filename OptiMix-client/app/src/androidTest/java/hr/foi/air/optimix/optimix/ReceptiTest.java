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
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ReceptiTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void receptiTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.ButtonLogin), withText("Prijava")));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.floatingSettingsButton), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.floatingActionButtonAddNewRecipe), isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction appCompatEditText = onView(
                withId(R.id.recipe_name));
        appCompatEditText.perform(scrollTo(), replaceText("testna2"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.add_new_raw_button), withText("Dodaj novu sirovinu")));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction editText = onView(
                withId(R.id.generated_raw_amount));
        editText.perform(scrollTo(), replaceText("100"), closeSoftKeyboard());


        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.submit_recipe_button), withText("Dodaj")));
        appCompatButton5.perform(scrollTo(), click());

    }

}
