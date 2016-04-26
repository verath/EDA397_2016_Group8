package group8.eda397.chalmers.se.pairprogramming.backlog.model;

import java.util.List;

/**
 * Interface for handling backlog data.
 */
public interface BacklogItemDataSource extends DataSource<BacklogItem> {

    /**
     * Gives all items with the specified status.
     *
     * @param status the status of the elements to return
     * @return all elements with the specified status, or an empty list if invalid status
     */
    List<BacklogItem> getAll(BacklogItem.Status status);
}
