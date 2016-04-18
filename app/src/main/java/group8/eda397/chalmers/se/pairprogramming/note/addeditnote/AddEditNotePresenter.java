package group8.eda397.chalmers.se.pairprogramming.note.addeditnote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import group8.eda397.chalmers.se.pairprogramming.note.Note;
import group8.eda397.chalmers.se.pairprogramming.note.NoteDataSource;

public class AddEditNotePresenter implements AddEditNoteContract.Presenter {

    private final AddEditNoteContract.View mView;
    @Nullable
    private final String mNoteId;
    private final NoteDataSource mNoteDataSource;

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
            Note note = mNoteDataSource.getNote(mNoteId);
            showNote(note);
        }
    }

    private void showNote(Note note) {
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
        mNoteDataSource.saveNote(note);
        mView.showNotesView();
    }
}
