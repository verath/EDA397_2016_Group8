package se.chalmers.eda397.group8.pairprogramming.backlog.detail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusDataSource;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementDataSource;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementSpecificationDataSource;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class BacklogDetailsPresenterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private BacklogItemDataSource mBacklogDataSource;

    @Mock
    private BacklogStatusDataSource mStatusDataSource;

    @Mock
    private RequirementDataSource mRequirementDataSource;

    @Mock
    private RequirementSpecificationDataSource mReqSpecDataSource;

    @Mock
    private BacklogDetailContract.View mBacklogDetailView;

    private BacklogDetailPresenter mBacklogDetailPresenter;

    private final BacklogItem ITEM = new BacklogItem("Title", "Content", "2", "test.pdf", "1");

    @Before
    public void setUpData() {
        mBacklogDetailPresenter = new BacklogDetailPresenter(mBacklogDetailView, ITEM.getId(),
                mBacklogDataSource, mStatusDataSource, mRequirementDataSource, mReqSpecDataSource);
        given(mBacklogDataSource.get(ITEM.getId())).willReturn(ITEM);
    }

    @Test
    public void start_displayItem() {
        // When the presenter starts:
        mBacklogDetailPresenter.start();

        // The item should be fetched from data source:
        verify(mBacklogDataSource).get(ITEM.getId());
    }

    @Test
    public void clickDelete_deleteItem() {
        // When delete is clicked:
        mBacklogDetailPresenter.onDeleteItemClicked();

        // Then the item should be removed from the data source:
        verify(mBacklogDataSource).delete(ITEM.getId());
        verify(mBacklogDetailView).showBacklog();
    }

    @Test
    public void clickEdit_displayEditItemView() {
        // When the edit button is clicked:
        mBacklogDetailPresenter.onEditItemClicked();

        // Then the edit view should be opened:
        verify(mBacklogDetailView).showEditView(ITEM.getId());
    }
}
