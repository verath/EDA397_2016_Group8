package group8.eda397.chalmers.se.pairprogramming;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import group8.eda397.chalmers.se.pairprogramming.notes.NotesActivity;
import group8.eda397.chalmers.se.pairprogramming.timer.TimerActivity;

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

        Button navigateToTimerButton = (Button) findViewById(R.id.btn_timer);
        if (navigateToTimerButton != null) {
            navigateToTimerButton.setOnClickListener(onNavigateToTimer);
        }

    }

    private final View.OnClickListener onNavigateToNotes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, NotesActivity.class);
            startActivity(intent);
        }
    };

    private final View.OnClickListener onNavigateToTimer = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, TimerActivity.class);
            startActivity(intent);
        }
    };
}
