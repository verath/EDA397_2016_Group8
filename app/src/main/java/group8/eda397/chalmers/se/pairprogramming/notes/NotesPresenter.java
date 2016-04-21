package group8.eda397.chalmers.se.pairprogramming.notes;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
/**
 * The notes presenter handles the logic for presenting notes, using
 * a notes view for actually displaying the notes.
 */
public class NotesPresenter implements NotesContract.Presenter {

    private final NotesContract.View mNotesView;

    private final static List<Note> dummyNotes = new ArrayList<>();

    static {
        for (int i = 1; i < 25; i++) {
            dummyNotes.add(new Note("Note " + i, "Lorem ipsum dolor sit amet, consectetur " +
                    "adipiscing elit. Maecenas velit lectus, convallis non lectus id, " +
                    "aliquet auctor turpis. Quisque luctus."));
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
