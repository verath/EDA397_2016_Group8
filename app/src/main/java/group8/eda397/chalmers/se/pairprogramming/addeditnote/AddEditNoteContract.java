package group8.eda397.chalmers.se.pairprogramming.addeditnote;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;

public interface AddEditNoteContract {

    interface View extends BaseView<Presenter> {
        void showNotesView();

        void showTitle(String title);

        void showText(String text);
    }

    interface Presenter extends BasePresenter {
        void onSaveClicked();
    }
}
