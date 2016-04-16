package group8.eda397.chalmers.se.pairprogramming.addeditnote;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;

public interface AddEditNoteContract {

    interface View extends BaseView<Presenter> {
        void showNotesView();
    }

    interface Presenter extends BasePresenter {
        void onSaveClicked();
    }
}
