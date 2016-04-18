package group8.eda397.chalmers.se.pairprogramming.note.addeditnote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import group8.eda397.chalmers.se.pairprogramming.note.notes.Note;

public class AddEditNotePresenter implements AddEditNoteContract.Presenter {

    private final AddEditNoteContract.View mView;
    @Nullable
    private final String mNoteId;

    public AddEditNotePresenter(@Nullable String noteId, @NonNull AddEditNoteContract.View addEditNoteView) {
        mNoteId = noteId;
        mView = addEditNoteView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (mNoteId != null) {
            Note dummyNote = new Note(mNoteId, "Title " + mNoteId, "Text " + mNoteId);
            showNote(dummyNote);
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
    public void onSaveClicked() {
        // TODO: save note
        mView.showNotesView();
    }
}
