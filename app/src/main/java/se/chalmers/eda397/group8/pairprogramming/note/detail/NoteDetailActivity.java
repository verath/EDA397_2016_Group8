package se.chalmers.eda397.group8.pairprogramming.note.detail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import se.chalmers.eda397.group8.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.note.NoteRepository;

/**
 * The note detail activity. The activity is to be started with
 * a note id, representing the id of the note to show.
 */
public class NoteDetailActivity extends BaseActivity {

    private static final String INTENT_EXTRA_PARAM_NOTE_ID = "group8.eda397.chalmers.se.pairprogramming.INTENT_PARAM_NOTE_ID";
    private static final String INSTANCE_STATE_PARAM_NOTE_ID = "group8.eda397.chalmers.se.pairprogramming.STATE_PARAM_NOTE_ID";

    private String mNoteId;

    public static Intent getCallingIntent(Context context, String noteId) {
        Intent callingIntent = new Intent(context, NoteDetailActivity.class);
        callingIntent.putExtra(INTENT_EXTRA_PARAM_NOTE_ID, noteId);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        // Setup fragment
        NoteDetailFragment noteDetailFragment;
        if (savedInstanceState == null) {
            mNoteId = getIntent().getStringExtra(INTENT_EXTRA_PARAM_NOTE_ID);
            noteDetailFragment = NoteDetailFragment.newInstance();
            addFragment(R.id.contentFrame, noteDetailFragment);
        } else {
            mNoteId = savedInstanceState.getString(INSTANCE_STATE_PARAM_NOTE_ID);
            noteDetailFragment = (NoteDetailFragment) findFragment(R.id.contentFrame);
        }

        // Setup toolbar
        setupToolbar();

        // Create the presenter
        new NoteDetailPresenter(NoteRepository.getInstance(), mNoteId, noteDetailFragment);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(INSTANCE_STATE_PARAM_NOTE_ID, mNoteId);
        }
        super.onSaveInstanceState(outState);
    }
}
