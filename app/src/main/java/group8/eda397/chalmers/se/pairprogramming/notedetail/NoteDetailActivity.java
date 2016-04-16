package group8.eda397.chalmers.se.pairprogramming.notedetail;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import group8.eda397.chalmers.se.pairprogramming.BaseActivity;
import group8.eda397.chalmers.se.pairprogramming.R;

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
        new NoteDetailPresenter(mNoteId, noteDetailFragment);
    }

    @Override
    protected void setupToolbar() {
        // We do not want up navigation from this activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (outState != null) {
            outState.putString(INSTANCE_STATE_PARAM_NOTE_ID, mNoteId);
        }
        super.onSaveInstanceState(outState);
    }
}
