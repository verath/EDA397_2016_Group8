package group8.eda397.chalmers.se.pairprogramming.addeditnote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
    }

    @Override
    public void onSaveClicked() {
        // TODO: save note
        mView.showNotesView();
    }
}
