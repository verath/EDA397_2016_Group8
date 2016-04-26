package se.chalmers.eda397.group8.pairprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import group8.eda397.chalmers.se.pairprogramming.R;
import se.chalmers.eda397.group8.pairprogramming.backlog.BacklogActivity;
import se.chalmers.eda397.group8.pairprogramming.note.notes.NotesActivity;
import se.chalmers.eda397.group8.pairprogramming.requirement.requirements.RequirementsActivity;
import se.chalmers.eda397.group8.pairprogramming.timer.TimerActivity;

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
            navigateToNotesButton.setOnClickListener(mOnNavigateToNotes);
        }

        Button navigateToBacklogButton = (Button) findViewById(R.id.show_backlog_button);
        if (navigateToBacklogButton != null) {
            navigateToBacklogButton.setOnClickListener(mOnNavigateToBacklog);
        }

        Button requirementsBtn = (Button) findViewById(R.id.btn_requirements);
        if (requirementsBtn != null) {
            requirementsBtn.setOnClickListener(mOnShowRequirements);
        }

        Button navigateToTimerButton = (Button) findViewById(R.id.btn_timer);
        if (navigateToTimerButton != null) {
            navigateToTimerButton.setOnClickListener(mOnNavigateToTimer);
        }

    }

    private final View.OnClickListener mOnNavigateToNotes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent launchIntent = NotesActivity.getCallingIntent(MainActivity.this);
            startActivity(launchIntent);
        }
    };
    private final View.OnClickListener mOnNavigateToBacklog = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent launchIntent = BacklogActivity.getCallingIntent(MainActivity.this);
            startActivity(launchIntent);
        }
    };
    private final View.OnClickListener mOnShowRequirements = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent launchIntent = RequirementsActivity.getCallingIntent(MainActivity.this);
            startActivity(launchIntent);
        }
    };

    private final View.OnClickListener mOnNavigateToTimer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent launchIntent = TimerActivity.getCallingIntent(MainActivity.this);
            startActivity(launchIntent);
        }
    };
}
