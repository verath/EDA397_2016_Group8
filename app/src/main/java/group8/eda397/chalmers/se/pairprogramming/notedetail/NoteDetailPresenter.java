package group8.eda397.chalmers.se.pairprogramming.notedetail;

import android.support.annotation.NonNull;

public class NoteDetailPresenter implements NoteDetailContract.Presenter {

    private final NoteDetailContract.View mNoteDetailView;

    public NoteDetailPresenter(@NonNull NoteDetailContract.View noteDetailView) {
        mNoteDetailView = noteDetailView;
        mNoteDetailView.setPresenter(this);
    }

    @Override
    public void start() {
    }
}
