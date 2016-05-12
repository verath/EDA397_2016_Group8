package se.chalmers.eda397.group8.pairprogramming.backlog.model;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.data.DataSource;

/**
 * Interface for handling backlog data.
 */
public interface BacklogItemDataSource extends DataSource<BacklogItem> {

    /**
     * Gives all items with the specified status.
     *
     * @param statusId the status ID of the elements to return
     * @return all elements with the specified status, or an empty list if invalid status
     */
    List<BacklogItem> getAllByStatus(String statusId);
}
