package group8.eda397.chalmers.se.pairprogramming.note.notedetail;

import android.app.Activity;
import android.support.annotation.NonNull;

import group8.eda397.chalmers.se.pairprogramming.note.notes.Note;

public class NoteDetailPresenter implements NoteDetailContract.Presenter {

    private final String mNoteId;
    private final NoteDetailContract.View mNoteDetailView;

    public NoteDetailPresenter(String noteId, @NonNull NoteDetailContract.View noteDetailView) {
        mNoteId = noteId;
        mNoteDetailView = noteDetailView;
        mNoteDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        Note dummyNote = new Note(mNoteId, "Title " + mNoteId, "Text " + mNoteId);
        showNote(dummyNote);
    }

    private void showNote(@NonNull Note note) {
        String title = note.getTitle();
        String text = note.getText();

        if (title.isEmpty()) {
            mNoteDetailView.hideTitle();
        } else {
            mNoteDetailView.showTitle(title);
        }

        if (text.isEmpty()) {
            mNoteDetailView.hideText();
        } else {
            mNoteDetailView.showText(text);
        }
    }

    @Override
    public void onEditClicked() {
        mNoteDetailView.showNoteEditView(mNoteId);
    }

    @Override
    public void onDeleteClicked() {
        // TODO: Remove the note
        mNoteDetailView.showNotesView();
    }

    @Override
    public void onEditNoteResult(int resultCode) {
        if(resultCode == Activity.RESULT_OK) {
            // TODO: success message?
            mNoteDetailView.showNotesView();
        }
    }
}
