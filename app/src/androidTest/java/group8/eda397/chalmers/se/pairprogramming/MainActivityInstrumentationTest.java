package group8.eda397.chalmers.se.pairprogramming;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import group8.eda397.chalmers.se.pairprogramming.notes.NotesActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentationTest {

    @Rule
    public IntentsTestRule mActivityRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void notesButtonNavigatesToNotes() {
        onView(withId(R.id.btn_notes)).perform(click());
        intended(hasComponent(NotesActivity.class.getName()));
    }

}