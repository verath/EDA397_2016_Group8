package se.chalmers.eda397.group8.pairprogramming.backlog.addedit;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


public class AddEditBacklogPresenterTest {

    private static final String TEST_ITEM_TITLE = "Title";
    private static final String TEST_ITEM_CONTENT = "Content";
    private static final String TEST_ITEM_PAGE = "1";
    private static final BacklogItem.Status TEST_ITEM_STATUS = BacklogItem.Status.READY_FOR_TEST;
    private static final BacklogItem.Status TEST_ITEM_STATUS_OTHER = BacklogItem.Status.DONE;
    private static final String INVALID_ITEM_ID = "";

    private static final BacklogItem ITEM = new BacklogItem(TEST_ITEM_TITLE,
            TEST_ITEM_CONTENT, TEST_ITEM_STATUS, TEST_ITEM_PAGE);

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private AddEditBacklogContract.View mView;

    @Mock
    private BacklogItemDataSource mDataSource;

    private AddEditBacklogPresenter mPresenter;

    @Test
    public void populatesFieldsFromDataSource() {
        // Given a mocked data source and a presenter for that item
        given(mDataSource.get(ITEM.getId())).willReturn(ITEM);
        mPresenter = new AddEditBacklogPresenter(mView, ITEM.getId(), null, mDataSource);

        // When the presenter is started
        mPresenter.start();

        // Then the DataSource is queried for the item and view is populated
        verify(mView).showTitle(TEST_ITEM_TITLE);
        verify(mView).showContent(TEST_ITEM_CONTENT);
        verify(mView).showStatus(TEST_ITEM_STATUS);
    }

    @Test
    public void populatesDefaultStatus() {
        // Given a presenter without an item and with a default backlog status
        BacklogItem.Status status = BacklogItem.Status.DONE;
        mPresenter = new AddEditBacklogPresenter(mView, null, status, mDataSource);

        // When the presenter is started
        mPresenter.start();

        // Then the status is set in the view
        verify(mView).showStatus(status);
    }

    @Test
    public void populatesFieldsFromDataSourceWithDefaultStatus() {
        // Given a presenter with both a valid id and a default status
        given(mDataSource.get(ITEM.getId())).willReturn(ITEM);
        mPresenter = new AddEditBacklogPresenter(mView, ITEM.getId(),
                TEST_ITEM_STATUS_OTHER, mDataSource);

        // When the presenter is started
        mPresenter.start();

        // Then the DataSource is queried for the item and view is populated
        // with only the data from the item
        verify(mView).showTitle(TEST_ITEM_TITLE);
        verify(mView).showContent(TEST_ITEM_CONTENT);
        verify(mView).showStatus(TEST_ITEM_STATUS);
        verify(mView, never()).showStatus(TEST_ITEM_STATUS_OTHER);
    }

    @Test
    public void showMissingBacklogItemErrorForMissingItem() {
        // Given a presenter for a non-existing backlog item id
        mPresenter = new AddEditBacklogPresenter(mView, INVALID_ITEM_ID, null, mDataSource);

        // When the presenter is started
        mPresenter.start();

        // Then the missing backlog item view is shown
        verify(mView).showMissingBacklogItem();
    }

    @Test
    public void saveNewItem_showsSuccessView() {
        // Given a presenter for a null item
        mPresenter = new AddEditBacklogPresenter(mView, null, null, mDataSource);

        // When the save button is clicked
        mPresenter.onSaveItem(TEST_ITEM_TITLE, TEST_ITEM_CONTENT, TEST_ITEM_STATUS, TEST_ITEM_PAGE);

        // Then the item is saved and the view is notified
        verify(mDataSource).save(any(BacklogItem.class));
        verify(mView).showBacklog();
    }

    @Test
    public void saveExistingItem_showsSuccessView() {
        // Given a presenter for an already existing item
        mPresenter = new AddEditBacklogPresenter(mView, ITEM.getId(), null, mDataSource);

        // When the save button is clicked
        mPresenter.onSaveItem(TEST_ITEM_TITLE, TEST_ITEM_CONTENT, TEST_ITEM_STATUS, TEST_ITEM_PAGE);

        // Then the item is saved and the view is notified
        verify(mDataSource).save(any(BacklogItem.class));
        verify(mView).showBacklog();
    }

    @Test
    public void saveEmptyItem_showsEmptyFieldsErrors() {
        // Given a presenter for a new item
        mPresenter = new AddEditBacklogPresenter(mView, null, null, mDataSource);

        // When the save button is clicked for an empty item
        mPresenter.onSaveItem("", "", TEST_ITEM_STATUS, "");

        // Then the view is notified and the item is not saved
        verify(mView).showTitleEmptyError();
        verify(mDataSource, never()).save(any(BacklogItem.class));
    }
}