package se.chalmers.eda397.group8.pairprogramming.note.notes;


import android.support.annotation.NonNull;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.BasePresenter;
import se.chalmers.eda397.group8.pairprogramming.BaseView;
import se.chalmers.eda397.group8.pairprogramming.note.Note;

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
