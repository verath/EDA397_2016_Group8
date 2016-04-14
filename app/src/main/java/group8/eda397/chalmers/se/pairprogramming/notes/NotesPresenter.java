package group8.eda397.chalmers.se.pairprogramming.notes;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;


public class NotesPresenter implements NotesContract.Presenter {

    private final NotesContract.View mNotesView;

    private final static List<Note> dummyNotes = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            dummyNotes.add(new Note("Note " + i, "Text for note " + i));
        }
    }

    public NotesPresenter(@NonNull NotesContract.View notesView) {
        mNotesView = notesView;
        mNotesView.setPresenter(this);
    }

    @Override
    public void start() {
        mNotesView.showNotes(dummyNotes);
    }
}
