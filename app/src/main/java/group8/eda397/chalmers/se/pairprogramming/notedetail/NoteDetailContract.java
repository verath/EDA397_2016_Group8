package group8.eda397.chalmers.se.pairprogramming.notedetail;


import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;

public interface NoteDetailContract {

    interface View extends BaseView<Presenter> {
        void hideTitle();

        void showTitle(String title);

        void hideText();

        void showText(String text);

        void showNoteEditView(String noteId);
    }

    interface Presenter extends BasePresenter {
        void onEditClicked();
    }

}
