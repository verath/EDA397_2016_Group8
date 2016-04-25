package group8.eda397.chalmers.se.pairprogramming.note.addeditnote;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import group8.eda397.chalmers.se.pairprogramming.note.Note;
import group8.eda397.chalmers.se.pairprogramming.note.NoteDataSource;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;


public class AddEditNotePresenterTest {

    private static final String TITLE_TEST = "title";
    private static final String TEXT_TEST = "text";
    private static final String TITLE_TEST_SAVE = "title_save";
    private static final String TEXT_TEST_SAVE = "text_save";
    private static final String INVALID_ID = "";


    private final static Note NOTE = new Note(TITLE_TEST, TEXT_TEST);

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private NoteDataSource mNoteDataSource;

    @Mock
    private AddEditNoteContract.View mAddEditNoteView;

    private AddEditNotePresenter mAddEditNotePresenter;

    @Test
    public void populatesFieldsFromDataSourceWhenEditingNote() {
        // Given a mocked note and a presenter created for that note
        given(mNoteDataSource.getNote(NOTE.getId())).willReturn(NOTE);
        mAddEditNotePresenter = new AddEditNotePresenter(mNoteDataSource, NOTE.getId(),
                mAddEditNoteView);

        // When the presenter is started
        mAddEditNotePresenter.start();

        // Then the view is populated with the data from the note
        verify(mAddEditNoteView).showTitle(TITLE_TEST);
        verify(mAddEditNoteView).showText(TEXT_TEST);
    }

    @Test
    public void showsMissingNoteErrorForMissingNote() {
        // Given a presenter for an invalid id
        mAddEditNotePresenter = new AddEditNotePresenter(mNoteDataSource, INVALID_ID,
                mAddEditNoteView);

        // When the presenter is started
        mAddEditNotePresenter.start();

        // Then the missing note view is shown
        verify(mAddEditNoteView).showMissingNote();
    }

    @Test
    public void saveNewNote_showsSuccessView() {
        // Given a presenter for a null note id
        mAddEditNotePresenter = new AddEditNotePresenter(mNoteDataSource, null, mAddEditNoteView);

        // When save is clicked
        mAddEditNotePresenter.onSaveClicked(TITLE_TEST_SAVE, TEXT_TEST_SAVE);

        // Then a new note is saved to the data source, and the view notified
        verify(mNoteDataSource).saveNote(any(Note.class));
        verify(mAddEditNoteView).showNotesView();
    }

    @Test
    public void saveExistingNote_showsSuccessView() {
        // Given a presenter for an already existing note id
        mAddEditNotePresenter = new AddEditNotePresenter(mNoteDataSource, NOTE.getId(),
                mAddEditNoteView);

        // When save is clicked
        mAddEditNotePresenter.onSaveClicked(TITLE_TEST_SAVE, TEXT_TEST_SAVE);

        // Then the existing note is saved, and the view notified
        verify(mNoteDataSource).saveNote(any(Note.class));
        verify(mAddEditNoteView).showNotesView();
    }

    @Test
    public void saveEmptyNote_displaysEmptyError() {
        // Given a presenter for a new note
        mAddEditNotePresenter = new AddEditNotePresenter(mNoteDataSource, null, mAddEditNoteView);

        // When save is clicked for an empty note
        mAddEditNotePresenter.onSaveClicked("", "");

        // Then an empty error is shown
        verify(mAddEditNoteView).showEmptyNoteError();
    }

}