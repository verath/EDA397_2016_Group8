package group8.eda397.chalmers.se.pairprogramming.note.notedetail;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import group8.eda397.chalmers.se.pairprogramming.note.Note;
import group8.eda397.chalmers.se.pairprogramming.note.NoteDataSource;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class NoteDetailPresenterTest {


    private static final String TITLE_TEST = "title";

    private static final String TEXT_TEST = "text";

    private static final String INVALID_ID = "";

    private final static Note NOTE = new Note(TITLE_TEST, TEXT_TEST);

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private NoteDataSource mNoteDataSource;

    @Mock
    private NoteDetailContract.View mNoteDetailView;

    @Test
    public void getsNoteFromDataSourceAndShowsInView() {
        // Given a stubbed note data source and presenter initialized with that id
        given(mNoteDataSource.getNote(NOTE.getId())).willReturn(NOTE);
        NoteDetailPresenter noteDetailPresenter = new NoteDetailPresenter(
                mNoteDataSource, NOTE.getId(), mNoteDetailView);

        // When the presenter is started
        noteDetailPresenter.start();

        // Then title and text is displayed in the view
        verify(mNoteDetailView).showTitle(TITLE_TEST);
        verify(mNoteDetailView).showText(TEXT_TEST);
    }

    @Test
    public void getsUnknownNoteFromDataSourceAndShowsInView() {
        // Given a presenter initialized with an invalid note id
        NoteDetailPresenter noteDetailPresenter = new NoteDetailPresenter(
                mNoteDataSource, INVALID_ID, mNoteDetailView);

        // When the presenter is started
        noteDetailPresenter.start();

        // Then a missing note view is shown
        mNoteDetailView.showMissingNote();
    }

    @Test
    public void clickOnDelete_deletesNote() {
        // Given a presenter with a stubbed note
        NoteDetailPresenter noteDetailPresenter = new NoteDetailPresenter(
                mNoteDataSource, NOTE.getId(), mNoteDetailView);

        // When deletion is requested
        noteDetailPresenter.onDeleteClicked();

        // Then the note is deleted from the data source and the view is notified
        verify(mNoteDataSource).deleteNote(NOTE.getId());
        verify(mNoteDetailView).showNotesView();
    }

    @Test
    public void clickOnEdit_showsEditView() {
        // Given a presenter with a stubbed note
        NoteDetailPresenter noteDetailPresenter = new NoteDetailPresenter(
                mNoteDataSource, NOTE.getId(), mNoteDetailView);

        // When edit button is clicked
        noteDetailPresenter.onEditClicked();

        // Then the edit view is requested
        verify(mNoteDetailView).showNoteEditView(NOTE.getId());
    }
}
