package se.chalmers.eda397.group8.pairprogramming.note.notedetail;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import se.chalmers.eda397.group8.pairprogramming.note.Note;
import se.chalmers.eda397.group8.pairprogramming.note.NoteDataSource;

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

    private NoteDetailPresenter mNoteDetailPresenter;

    @Test
    public void getsNoteFromDataSourceAndShowsInView() {
        // Given a stubbed note data source and presenter initialized with that id
        given(mNoteDataSource.getNote(NOTE.getId())).willReturn(NOTE);
        mNoteDetailPresenter = new NoteDetailPresenter(mNoteDataSource, NOTE.getId(), mNoteDetailView);

        // When the presenter is started
        mNoteDetailPresenter.start();

        // Then title and text is displayed in the view
        verify(mNoteDetailView).showTitle(TITLE_TEST);
        verify(mNoteDetailView).showText(TEXT_TEST);
    }

    @Test
    public void getsUnknownNoteFromDataSourceAndShowsInView() {
        // Given a presenter initialized with an invalid note id
        mNoteDetailPresenter = new NoteDetailPresenter(mNoteDataSource, INVALID_ID, mNoteDetailView);

        // When the presenter is started
        mNoteDetailPresenter.start();

        // Then a missing note view is shown
        mNoteDetailView.showMissingNote();
    }

    @Test
    public void clickOnDelete_deletesNote() {
        // Given a presenter with a stubbed note
        mNoteDetailPresenter = new NoteDetailPresenter(mNoteDataSource, NOTE.getId(), mNoteDetailView);

        // When deletion is requested
        mNoteDetailPresenter.onDeleteClicked();

        // Then the note is deleted from the data source and the view is notified
        verify(mNoteDataSource).deleteNote(NOTE.getId());
        verify(mNoteDetailView).showNotesView();
    }

    @Test
    public void clickOnEdit_showsEditView() {
        // Given a presenter with a stubbed note
        mNoteDetailPresenter = new NoteDetailPresenter(mNoteDataSource, NOTE.getId(), mNoteDetailView);

        // When edit button is clicked
        mNoteDetailPresenter.onEditClicked();

        // Then the edit view is requested
        verify(mNoteDetailView).showNoteEditView(NOTE.getId());
    }
}
