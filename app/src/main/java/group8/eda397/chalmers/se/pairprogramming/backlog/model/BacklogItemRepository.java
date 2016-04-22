package group8.eda397.chalmers.se.pairprogramming.backlog.model;

import java.util.List;

/**
 * Created by m_cal on 2016-04-21.
 */
public class BacklogItemRepository implements DataSource<BacklogItem> {
    
    @Override
    public BacklogItem get(String id) {
        return null;
    }

    @Override
    public boolean save(BacklogItem item) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<BacklogItem> getAll() {
        return null;
    }
}
