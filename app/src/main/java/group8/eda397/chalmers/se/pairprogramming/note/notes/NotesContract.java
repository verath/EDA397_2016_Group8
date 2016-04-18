package group8.eda397.chalmers.se.pairprogramming.note.notes;


import android.support.annotation.NonNull;

import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;

public interface NotesContract {
    interface View extends BaseView<Presenter> {
        void showNotes(@NonNull List<Note> notes);

        void showNoteDetailView(String noteId);

        void showAddNoteView();
    }

    interface Presenter extends BasePresenter {
        void onNoteClicked(Note clickedNote);

        void onAddClicked();

        void onAddNoteResult(int resultCode);
    }
}
