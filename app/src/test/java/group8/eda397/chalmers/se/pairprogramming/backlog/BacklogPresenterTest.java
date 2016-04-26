package group8.eda397.chalmers.se.pairprogramming.backlog;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItem;
import group8.eda397.chalmers.se.pairprogramming.backlog.model.BacklogItemDataSource;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.verify;

public class BacklogPresenterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private BacklogItemDataSource mBacklogDataSource;

    @Mock
    private BacklogContract.View mBacklogView;

    @Mock
    private BacklogPresenter mBacklogPresenter;

    @Before
    public void setupDetailPresenter() {
        mBacklogPresenter = new BacklogPresenter(mBacklogView, mBacklogDataSource);
    }

    @Test
    public void resume_displayBacklog() {
        // When we resume:
        mBacklogPresenter.onSwipeFragmentResume(any(BacklogItem.Status.class));

        // Then view should display backlog and fetch the items to display:
        verify(mBacklogView).showBacklogForStatus(any(BacklogItem.Status.class), anyListOf(BacklogItem.class));
        verify(mBacklogDataSource).getAll(any(BacklogItem.Status.class));
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
        BacklogItem item = new BacklogItem("Title", "Content", BacklogItem.Status.BACKLOG);

        // When item is clicked:
        mBacklogPresenter.onBacklogItemClicked(item);

        // Then view for item details should be displayed:
        verify(mBacklogView).showBacklogItemDetails(item.getId());
    }
}
