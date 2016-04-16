package group8.eda397.chalmers.se.pairprogramming.notes;

import android.os.Bundle;

import group8.eda397.chalmers.se.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * The activity for displaying notes.
 */
public class NotesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        setupToolbar();

        // Setup fragment
        NotesFragment notesFragment;
        if (savedInstanceState == null) {
            notesFragment = NotesFragment.newInstance();
            addFragment(R.id.contentFrame, notesFragment);
        } else {
            notesFragment = (NotesFragment) findFragment(R.id.contentFrame);
        }

        new NotesPresenter(notesFragment);
    }
}
