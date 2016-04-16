package group8.eda397.chalmers.se.pairprogramming.notedetail;

import android.support.annotation.NonNull;

import group8.eda397.chalmers.se.pairprogramming.notes.Note;

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
}
