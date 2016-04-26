package se.chalmers.eda397.group8.pairprogramming.note.addeditnote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.note.NoteRepository;

/**
 * The activity for adding a new note or editing an
 * existing note. When adding a new note, this activity is
 * started without (or with a null) note id. If editing
 * the activity is instead started with the note id to
 * edit.
 */
public class AddEditNoteActivity extends BaseActivity {

    private static final String INTENT_EXTRA_PARAM_NOTE_ID = "group8.eda397.chalmers.se.pairprogramming.INTENT_PARAM_NOTE_ID";
    private static final String INSTANCE_STATE_PARAM_NOTE_ID = "group8.eda397.chalmers.se.pairprogramming.STATE_PARAM_NOTE_ID";

    private String mNoteId;

    public static Intent getCallingIntent(Context context, @Nullable String noteId) {
        Intent callingIntent = new Intent(context, AddEditNoteActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_NOTE_ID, noteId);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        // Setup fragment
        AddEditNoteFragment addEditNoteFragment;
        if (savedInstanceState == null) {
            mNoteId = getIntent().getStringExtra(INTENT_EXTRA_PARAM_NOTE_ID);
            addEditNoteFragment = AddEditNoteFragment.newInstance();
            addFragment(R.id.contentFrame, addEditNoteFragment);
        } else {
            mNoteId = savedInstanceState.getString(INSTANCE_STATE_PARAM_NOTE_ID);
            addEditNoteFragment = (AddEditNoteFragment) findFragment(R.id.contentFrame);
        }

        // Setup toolbar
        setupToolbar();

        // Setup presenter
        new AddEditNotePresenter(NoteRepository.getInstance(), mNoteId, addEditNoteFragment);
    }

    @Override
    protected void setupToolbar() {
        super.setupToolbar();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            int title = (mNoteId == null) ? R.string.title_add_note : R.string.title_edit_note;
            actionBar.setTitle(title);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(INSTANCE_STATE_PARAM_NOTE_ID, mNoteId);
        }
        super.onSaveInstanceState(outState);
    }
}
