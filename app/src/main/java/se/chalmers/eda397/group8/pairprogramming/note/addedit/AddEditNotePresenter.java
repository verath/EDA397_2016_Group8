package se.chalmers.eda397.group8.pairprogramming.note.addedit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import se.chalmers.eda397.group8.pairprogramming.note.Note;
import se.chalmers.eda397.group8.pairprogramming.note.NoteDataSource;

public class AddEditNotePresenter implements AddEditNoteContract.Presenter {

    private final AddEditNoteContract.View mView;
    @Nullable
    private final String mNoteId;
    private final NoteDataSource mNoteDataSource;

    /**
     * Creates a new AddEditNotePresenter
     *
     * @param noteDataSource  The note data source to use.
     * @param noteId          The id of the note to edit, or null for creating a new note.
     * @param addEditNoteView The view.
     */
    public AddEditNotePresenter(@NonNull NoteDataSource noteDataSource,
                                @Nullable String noteId,
                                @NonNull AddEditNoteContract.View addEditNoteView) {
        mNoteDataSource = noteDataSource;
        mNoteId = noteId;
        mView = addEditNoteView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (mNoteId != null) {
            Note note = mNoteDataSource.get(mNoteId);
            if (note == null) {
                mView.showMissingNote();
            } else {
                populateNote(note);
            }
        }
    }

    private void populateNote(Note note) {
        if (!note.getTitle().isEmpty()) {
            mView.showTitle(note.getTitle());
        }
        if (!note.getText().isEmpty()) {
            mView.showText(note.getText());
        }
    }

    @Override
    public void onSaveClicked(@NonNull String title, @NonNull String text) {
        Note note;
        if (mNoteId == null) {
            note = new Note(title, text);
        } else {
            note = new Note(title, text, mNoteId);
        }

        if (note.isEmpty()) {
            mView.showEmptyNoteError();
        } else {
            mNoteDataSource.save(note);
            mView.showNotesView();
        }
    }
}
