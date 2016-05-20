package se.chalmers.eda397.group8.pairprogramming.backlog;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusDataSource;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;

public class BacklogPresenterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private BacklogItemDataSource mBacklogDataSource;

    @Mock
    private BacklogStatusDataSource mStatusDataSource;

    @Mock
    private BacklogContract.View mBacklogView;

    private BacklogPresenter mBacklogPresenter;

    @Before
    public void setupDetailPresenter() {
        mBacklogPresenter = new BacklogPresenter(mBacklogView, mBacklogDataSource, mStatusDataSource);
    }

    @Test
    public void resume_displayBacklog() {
        // When we resume:
        mBacklogPresenter.onSwipeFragmentResume(any(String.class));

        // Then view should display backlog and fetch the items to display:
        verify(mBacklogView).showBacklogForStatus(any(BacklogStatus.class), anyListOf(BacklogItem.class));
        verify(mBacklogDataSource).getAllByStatus(any(String.class));
    }

    @Test
    public void clickAdd_showAddView() {
        // When add new item is clicked:
        mBacklogPresenter.onAddClicked();

        // Then view for adding items should be displayed:
        verify(mBacklogView).showAddBacklogItemView();
    }

    @Test
    public void clickItem_showItemDetails() {
        // Given an item:
        BacklogItem item = new BacklogItem("Title", "Content", "2", "test.pdf", "1");

        // When item is clicked:
        mBacklogPresenter.onBacklogItemClicked(item);

        // Then view for item details should be displayed:
        verify(mBacklogView).showBacklogItemDetails(item.getId());
    }
}
