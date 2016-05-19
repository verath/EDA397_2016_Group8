package se.chalmers.eda397.group8.pairprogramming.backlog.addedit;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatus;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogStatusDataSource;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


public class AddEditBacklogPresenterTest {

    private static final String TEST_ITEM_TITLE = "Title";
    private static final String TEST_ITEM_CONTENT = "Content";
    private static final String TEST_ITEM_PDF_NAME = "test.pdf";
    private static final String TEST_ITEM_PAGE = "1";
    private static final String TEST_ITEM_STATUS_ID = "1";
    private static final String TEST_ITEM_STATUS_OTHER_ID = "2";
    private static final String INVALID_ITEM_ID = "";

    private static final BacklogItem ITEM = new BacklogItem(TEST_ITEM_TITLE,
            TEST_ITEM_CONTENT, TEST_ITEM_STATUS_ID, TEST_ITEM_PDF_NAME, TEST_ITEM_PAGE);
    private static final BacklogStatus STATUS = new BacklogStatus(TEST_ITEM_STATUS_ID, "Backlog");
    private static final BacklogStatus OTHER_STATUS = new BacklogStatus(TEST_ITEM_STATUS_OTHER_ID, "Invalid");

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private AddEditBacklogContract.View mView;

    @Mock
    private BacklogItemDataSource mItemDataSource;

    @Mock
    private BacklogStatusDataSource mStatusDataSource;

    private AddEditBacklogPresenter mPresenter;

    @Test
    public void populatesFieldsFromDataSource() {
        // Given mocked data sources and a presenter for that item
        given(mItemDataSource.get(ITEM.getId())).willReturn(ITEM);
        given(mStatusDataSource.get(STATUS.getId())).willReturn(STATUS);
        mPresenter = new AddEditBacklogPresenter(mView, ITEM.getId(), null, mItemDataSource,
                mStatusDataSource);

        // When the presenter is started
        mPresenter.start();

        // Then the DataSource is queried for the item and view is populated
        verify(mView).showTitle(TEST_ITEM_TITLE);
        verify(mView).showContent(TEST_ITEM_CONTENT);
        verify(mView).showSelectedStatus(STATUS);
        verify(mView).showPage(TEST_ITEM_PAGE);
    }

    @Test
    public void populatesDefaultStatus() {
        // Given a presenter without an item and with a default backlog status
        given(mStatusDataSource.get(STATUS.getId())).willReturn(STATUS);

        mPresenter = new AddEditBacklogPresenter(mView, null, STATUS.getId(),
                mItemDataSource, mStatusDataSource);

        // When the presenter is started
        mPresenter.start();

        // Then the status is set in the view
        verify(mView).showSelectedStatus(STATUS);
    }

    @Test
    public void populatesFieldsFromDataSourceWithDefaultStatus() {
        // Given a presenter with both a valid id and a default status
        given(mItemDataSource.get(ITEM.getId())).willReturn(ITEM);
        given(mStatusDataSource.get(STATUS.getId())).willReturn(STATUS);
        given(mStatusDataSource.get(OTHER_STATUS.getId())).willReturn(OTHER_STATUS);
        mPresenter = new AddEditBacklogPresenter(mView, ITEM.getId(),
                TEST_ITEM_STATUS_OTHER_ID, mItemDataSource, mStatusDataSource);

        // When the presenter is started
        mPresenter.start();

        // Then the DataSource is queried for the item and view is populated
        // with only the data from the item
        verify(mView).showTitle(TEST_ITEM_TITLE);
        verify(mView).showContent(TEST_ITEM_CONTENT);
        verify(mView).showSelectedStatus(STATUS);
        verify(mView, never()).showSelectedStatus(OTHER_STATUS);
    }

    @Test
    public void showMissingBacklogItemErrorForMissingItem() {
        // Given a presenter for a non-existing backlog item id
        mPresenter = new AddEditBacklogPresenter(mView, INVALID_ITEM_ID, null, mItemDataSource,
                mStatusDataSource);

        // When the presenter is started
        mPresenter.start();

        // Then the missing backlog item view is shown
        verify(mView).showMissingBacklogItem();
    }

    @Test
    public void saveNewItem_showsSuccessView() {
        // Given a presenter for a null item
        mPresenter = new AddEditBacklogPresenter(mView, null, null, mItemDataSource,
                mStatusDataSource);

        // When the save button is clicked
        mPresenter.onSaveItem(TEST_ITEM_TITLE, TEST_ITEM_CONTENT, TEST_ITEM_STATUS_ID,
                TEST_ITEM_PDF_NAME, TEST_ITEM_PAGE);

        // Then the item is saved and the view is notified
        verify(mItemDataSource).save(any(BacklogItem.class));
        verify(mView).showBacklog();
    }

    @Test
    public void saveExistingItem_showsSuccessView() {
        // Given a presenter for an already existing item
        mPresenter = new AddEditBacklogPresenter(mView, ITEM.getId(), null, mItemDataSource,
                mStatusDataSource);

        // When the save button is clicked
        mPresenter.onSaveItem(TEST_ITEM_TITLE, TEST_ITEM_CONTENT, TEST_ITEM_STATUS_ID,
                TEST_ITEM_PDF_NAME, TEST_ITEM_PAGE);

        // Then the item is saved and the view is notified
        verify(mItemDataSource).save(any(BacklogItem.class));
        verify(mView).showBacklog();
    }

    @Test
    public void saveEmptyItem_showsEmptyFieldsErrors() {
        // Given a presenter for a new item
        mPresenter = new AddEditBacklogPresenter(mView, null, null, mItemDataSource,
                mStatusDataSource);

        // When the save button is clicked for an empty item
        mPresenter.onSaveItem("", "", TEST_ITEM_STATUS_ID, "", "");

        // Then the view is notified and the item is not saved
        verify(mView).showTitleEmptyError();
        verify(mItemDataSource, never()).save(any(BacklogItem.class));
    }
}