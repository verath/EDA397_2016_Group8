package group8.eda397.chalmers.se.pairprogramming.note.notes;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import group8.eda397.chalmers.se.pairprogramming.note.Note;
import group8.eda397.chalmers.se.pairprogramming.note.NoteDataSource;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;


public class NotesPresenterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private NotesContract.View mNotesView;

    @Mock
    private NoteDataSource mNoteDataSource;

    private NotesPresenter mNotesPresenter;

    @Before
    public void setupNotesPresenter() {
        mNotesPresenter = new NotesPresenter(mNoteDataSource, mNotesView);
    }

    @Test
    public void showsNotesOnStart() {
        // When presenter is started
        mNotesPresenter.start();

        // Then a list of notes is shown
        verify(mNotesView).showNotes(anyListOf(Note.class));
    }

    @Test
    public void clickOnAdd_ShowsAddView() {
        // When the add button is clicked
        mNotesPresenter.onAddClicked();

        // Then the add view is shown
        verify(mNotesView).showAddNoteView();
    }

    @Test
    public void clickOnNote_ShowsDetailsView() {
        // Given a stubbed note
        Note note = new Note("Title", "Text");

        // When the note is clicked
        mNotesPresenter.onNoteClicked(note);

        // Then the details view for that note is shown
        verify(mNotesView).showNoteDetailView(note.getId());
    }
}