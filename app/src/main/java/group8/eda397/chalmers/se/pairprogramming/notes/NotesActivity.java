package group8.eda397.chalmers.se.pairprogramming.notes;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * The activity for displaying notes.
 */
public class NotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Try to find the notes fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        NotesFragment notesFragment = (NotesFragment) fragmentManager
                .findFragmentById(R.id.contentFrame);

        // Create the note fragment if it did not exist
        if (notesFragment == null) {
            notesFragment = NotesFragment.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.contentFrame, notesFragment);
            fragmentTransaction.commit();
        }

        // Create the presenter
        NotesContract.Presenter notesPresenter = new NotesPresenter(notesFragment);
    }
}
