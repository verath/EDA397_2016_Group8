package group8.eda397.chalmers.se.pairprogramming.note.notes;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import group8.eda397.chalmers.se.pairprogramming.note.Note;
import group8.eda397.chalmers.se.pairprogramming.note.NoteDataSource;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;


public class NotesPresenterTest {

    @Mock
    private NotesContract.View mNotesView;

    @Mock
    private NoteDataSource mNoteDataSource;

    private NotesContract.Presenter mNotesPresenter;

    @Before
    public void setupNotesPresenter() {
        MockitoAnnotations.initMocks(this);
        mNotesPresenter = new NotesPresenter(mNoteDataSource, mNotesView);
    }

    @Test
    public void loadDummyDataOnStart() {
        mNotesPresenter.start();
        verify(mNotesView).showNotes(anyListOf(Note.class));
    }
}