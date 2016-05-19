package se.chalmers.eda397.group8.pairprogramming.backlog.model;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository of backlog items from the local database.
 */
public class BacklogItemRepository implements BacklogItemDataSource {

    private static BacklogItemRepository sInstance;
    private final Map<String, BacklogItem> mCachedBacklog = new HashMap<>();
    private final BacklogItemDataSource mBacklogLocalDataSource;

    private BacklogItemRepository(BacklogItemDataSource backlogLocalDataSource) {
        mCachedBacklog.clear();
        mBacklogLocalDataSource = backlogLocalDataSource;

        // HACK: Clear the database, as items reference Requirements that are not
        // stored in the database currently. This breaks things, removing all items
        // is currently the easiest way to work around this.
        // TODO: Don't do this once Requirement (and RequirementSpecification?) is also stored in db
        List<BacklogItem> backlogItems = mBacklogLocalDataSource.getAll();
        for (BacklogItem backlogItem : backlogItems) {
            delete(backlogItem.getId());
        }
    }

    public static BacklogItemRepository getInstance(@NonNull BacklogItemDataSource mBacklogLocalDataSource) {
        if (sInstance == null) {
            sInstance = new BacklogItemRepository(mBacklogLocalDataSource);
        }
        return sInstance;
    }

    @Override
    public BacklogItem get(@NonNull String id) {
        BacklogItem backlogItem = mCachedBacklog.get(id);
        if (backlogItem != null) {
            return backlogItem;
        }

        // Retrieve it from the local database if it's not in the cache
        return mBacklogLocalDataSource.get(id);
    }

    @Override
    public boolean save(@NonNull BacklogItem item) {
        mBacklogLocalDataSource.save(item);
        return mCachedBacklog.put(item.getId(), item) != null;
    }

    @Override
    public BacklogItem delete(@NonNull String id) {
        mBacklogLocalDataSource.delete(id);
        return mCachedBacklog.remove(id);
    }

    @NonNull
    @Override
    public List<BacklogItem> getAll() {
        return mBacklogLocalDataSource.getAll();
    }

    @Override
    public List<BacklogItem> getAllByStatus(String statusId) {
        return mBacklogLocalDataSource.getAllByStatus(statusId);
    }

}
