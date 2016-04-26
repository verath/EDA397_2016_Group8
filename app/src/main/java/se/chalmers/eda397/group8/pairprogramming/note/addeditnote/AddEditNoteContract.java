package se.chalmers.eda397.group8.pairprogramming.note.addeditnote;

import android.support.annotation.NonNull;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;

public interface AddEditNoteContract {

    interface View extends BaseView<Presenter> {
        void showNotesView();

        void showTitle(String title);

        void showText(String text);

        void showEmptyNoteError();

        void showMissingNote();
    }

    interface Presenter extends BasePresenter {
        void onSaveClicked(@NonNull String title, @NonNull String text);
    }
}
