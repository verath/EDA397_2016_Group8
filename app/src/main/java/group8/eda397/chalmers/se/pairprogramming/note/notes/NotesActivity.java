package group8.eda397.chalmers.se.pairprogramming.note.notes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import group8.eda397.chalmers.se.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;

/**
 * The activity for displaying notes.
 */
public class NotesActivity extends BaseActivity {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, NotesActivity.class);
    }

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
