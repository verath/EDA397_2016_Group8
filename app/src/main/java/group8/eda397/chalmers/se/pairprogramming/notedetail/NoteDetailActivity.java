package group8.eda397.chalmers.se.pairprogramming.notedetail;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import group8.eda397.chalmers.se.pairprogramming.R;

public class NoteDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Try to find the fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        NoteDetailFragment noteDetailFragment = (NoteDetailFragment) fragmentManager
                .findFragmentById(R.id.contentFrame);

        // Create the fragment if it did not exist
        if (noteDetailFragment == null) {
            noteDetailFragment = NoteDetailFragment.newInstance();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.contentFrame, noteDetailFragment);
            fragmentTransaction.commit();
        }

        // Create the presenter
        new NoteDetailPresenter(noteDetailFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
