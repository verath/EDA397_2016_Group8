package group8.eda397.chalmers.se.pairprogramming.note.notes;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;


public class NotesPresenterTest {

    @Mock
    private NotesContract.View mNotesView;

    private NotesContract.Presenter mNotesPresenter;

    @Before
    public void setupNotesPresenter() {
        MockitoAnnotations.initMocks(this);
        mNotesPresenter = new NotesPresenter(mNotesView);
    }

    @Test
    public void loadDummyDataOnStart() {
        mNotesPresenter.start();
        verify(mNotesView).showNotes(anyListOf(Note.class));
    }
}