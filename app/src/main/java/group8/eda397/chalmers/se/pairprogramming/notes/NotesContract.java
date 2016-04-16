package group8.eda397.chalmers.se.pairprogramming.notes;


import android.support.annotation.NonNull;

import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.BasePresenter;
import group8.eda397.chalmers.se.pairprogramming.BaseView;

public interface NotesContract {
    interface View extends BaseView<Presenter> {
        void showNotes(@NonNull List<Note> notes);

        void showNoteDetailView(Note note);
    }

    interface Presenter extends BasePresenter {
        void onNoteClicked(Note clickedNote);
    }
}
