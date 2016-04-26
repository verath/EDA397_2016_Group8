package se.chalmers.eda397.group8.pairprogramming.note.notedetail;


import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;

public interface NoteDetailContract {

    interface View extends BaseView<Presenter> {
        void hideTitle();

        void showTitle(String title);

        void hideText();

        void showText(String text);

        void showNoteEditView(String noteId);

        void showNotesView();

        void showMissingNote();
    }

    interface Presenter extends BasePresenter {
        void onEditClicked();

        void onDeleteClicked();

        void onEditNoteResult(int resultCode);
    }

}
