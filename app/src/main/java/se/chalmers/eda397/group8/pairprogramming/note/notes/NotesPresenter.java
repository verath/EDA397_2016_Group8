package se.chalmers.eda397.group8.pairprogramming.note.notes;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.note.Note;
import se.chalmers.eda397.group8.pairprogramming.note.NoteDataSource;

/**
 * The notes presenter handles the logic for presenting notes, using
 * a notes view for actually displaying the notes.
 */
public class NotesPresenter implements NotesContract.Presenter {

    private final NotesContract.View mNotesView;

    private final NoteDataSource mNoteDataSource;

    /**
     * Creates a new NotesPresenter for displaying notes.
     *
     * @param noteDataSource The note data source to use for note data.
     * @param notesView      The view.
     */
    public NotesPresenter(@NonNull NoteDataSource noteDataSource,
                          @NonNull NotesContract.View notesView) {
        mNoteDataSource = noteDataSource;
        mNotesView = notesView;
        mNotesView.setPresenter(this);
    }

    @Override
    public void start() {
        List<Note> notes = mNoteDataSource.getAll();
        // Sort by id, as the list returned is not ordered.
        // TODO: Sort by a property that is more suited for sorting
        Collections.sort(notes, new Comparator<Note>() {
            @Override
            public int compare(Note lhs, Note rhs) {
                return lhs.getId().compareTo(rhs.getId());
            }
        });
        mNotesView.showNotes(notes);
    }

    @Override
    public void onNoteClicked(Note clickedNote) {
        mNotesView.showNoteDetailView(clickedNote.getId());
    }

    @Override
    public void onAddClicked() {
        mNotesView.showAddNoteView();
    }

    @Override
    public void onAddNoteResult(int resultCode) {
        // TODO: show success message on successful add?
    }
}
