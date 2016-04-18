package group8.eda397.chalmers.se.pairprogramming.note.notes;

import android.support.annotation.NonNull;

import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.note.Note;
import group8.eda397.chalmers.se.pairprogramming.note.NoteDataSource;

/**
 * The notes presenter handles the logic for presenting notes, using
 * a notes view for actually displaying the notes.
 */
public class NotesPresenter implements NotesContract.Presenter {

    private final NotesContract.View mNotesView;

    private final NoteDataSource mNoteDataSource;

    public NotesPresenter(@NonNull NoteDataSource noteDataSource, @NonNull NotesContract.View notesView) {
        mNoteDataSource = noteDataSource;
        mNotesView = notesView;
        mNotesView.setPresenter(this);
    }

    @Override
    public void start() {
        List<Note> notes = mNoteDataSource.getNotes();
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
