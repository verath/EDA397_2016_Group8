package group8.eda397.chalmers.se.pairprogramming.note.addeditnote;

import android.support.annotation.NonNull;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;

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
