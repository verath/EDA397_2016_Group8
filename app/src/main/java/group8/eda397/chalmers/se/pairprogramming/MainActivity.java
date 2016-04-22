package group8.eda397.chalmers.se.pairprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import group8.eda397.chalmers.se.pairprogramming.backlog.BacklogActivity;
import group8.eda397.chalmers.se.pairprogramming.note.notes.NotesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Setup navigation buttons
        Button navigateToNotesButton = (Button) findViewById(R.id.btn_notes);
        if (navigateToNotesButton != null) {
            navigateToNotesButton.setOnClickListener(onNavigateToNotes);
        }
        Button navigateToBacklogButton = (Button) findViewById(R.id.show_backlog_button);
        if (navigateToBacklogButton != null) {
            navigateToBacklogButton.setOnClickListener(onNavigateToBacklog);
        }
    }

    private final View.OnClickListener onNavigateToNotes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent launchIntent = NotesActivity.getCallingIntent(MainActivity.this);
            startActivity(launchIntent);
        }
    };
    private final View.OnClickListener onNavigateToBacklog = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent launchIntent = BacklogActivity.getCallingIntent(MainActivity.this);
            startActivity(launchIntent);
        }
    };
}
