package group8.eda397.chalmers.se.pairprogramming.backlog.model;

import java.util.List;

/**
 * Created by m_cal on 2016-04-22.
 */
public interface BacklogItemDataSource extends DataSource<BacklogItem> {

    List<BacklogItem> getAll(BacklogItem.Status status);
}
