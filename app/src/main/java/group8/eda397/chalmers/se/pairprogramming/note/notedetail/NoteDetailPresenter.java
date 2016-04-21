package group8.eda397.chalmers.se.pairprogramming.note.notedetail;

import android.app.Activity;
import android.support.annotation.NonNull;

import group8.eda397.chalmers.se.pairprogramming.note.Note;
import group8.eda397.chalmers.se.pairprogramming.note.NoteDataSource;

public class NoteDetailPresenter implements NoteDetailContract.Presenter {

    private final String mNoteId;
    private final NoteDetailContract.View mNoteDetailView;
    private final NoteDataSource mNoteDataSource;

    /**
     * Creates a new NoteDetailPresenter for the given note, identified by id.
     *
     * @param noteDataSource The note data source to use.
     * @param noteId         The id of the note to display.
     * @param noteDetailView The view.
     */
    public NoteDetailPresenter(@NonNull NoteDataSource noteDataSource,
                               @NonNull String noteId,
                               @NonNull NoteDetailContract.View noteDetailView) {
        mNoteDataSource = noteDataSource;
        mNoteId = noteId;
        mNoteDetailView = noteDetailView;
        mNoteDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        Note note = mNoteDataSource.getNote(mNoteId);
        if (note != null) {
            showNote(note);
        } else {
            // TODO: show empty view
        }
    }

    private void showNote(@NonNull Note note) {
        String title = note.getTitle();
        String text = note.getText();

        if (title.isEmpty()) {
            mNoteDetailView.hideTitle();
        } else {
            mNoteDetailView.showTitle(title);
        }

        if (text.isEmpty()) {
            mNoteDetailView.hideText();
        } else {
            mNoteDetailView.showText(text);
        }
    }

    @Override
    public void onEditClicked() {
        mNoteDetailView.showNoteEditView(mNoteId);
    }

    @Override
    public void onDeleteClicked() {
        mNoteDataSource.deleteNote(mNoteId);
        mNoteDetailView.showNotesView();
    }

    @Override
    public void onEditNoteResult(int resultCode) {
        if (resultCode == Activity.RESULT_OK) {
            // TODO: success message?
            mNoteDetailView.showNotesView();
        }
    }
}
