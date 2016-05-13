package se.chalmers.eda397.group8.pairprogramming.backlog;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusDataSource;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;

public class BacklogPresenterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private BacklogStatusDataSource mStatusDataSource;

    @Mock
    private BacklogContract.View mBacklogView;

    private BacklogPresenter mBacklogPresenter;

    @Before
    public void setupDetailPresenter() {
        mBacklogPresenter = new BacklogPresenter(mBacklogView, mStatusDataSource);
    }

    @Test
    public void resume_displayBacklog() {
        // When the presenter is resumed
        mBacklogPresenter.start();

        // Then statuses should be fetched from the data source and view notified
        verify(mStatusDataSource).getAll();
        verify(mBacklogView).showBacklogStatuses(anyListOf(BacklogStatus.class));
    }

    @Test
    public void clickAdd_showAddView() {
        // When the add button is clicked for a specific BacklogStatus
        BacklogStatus status = new BacklogStatus("test");
        mBacklogPresenter.onAddClicked(status);

        // Then view for adding items should be displayed, including the status id
        verify(mBacklogView).showAddBacklogItemView(status.getId());
    }
}
