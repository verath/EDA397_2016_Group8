package group8.eda397.chalmers.se.pairprogramming.notedetail;

import android.support.annotation.NonNull;

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
    }
}
