package se.chalmers.eda397.group8.pairprogramming.backlog.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        // Populate cache with data from local database
        List<BacklogItem> backlogItems = mBacklogLocalDataSource.getAll();
        for (BacklogItem backlogItem : backlogItems) {
            mCachedBacklog.put(backlogItem.getId(), backlogItem);
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

        // Retrieve it from the local database otherwise
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

    @Override
    public List<BacklogItem> getAll() {
        /**
        List<BacklogItem> items = new ArrayList<>();
        Set<String> keys = mCachedBacklog.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            BacklogItem item = mCachedBacklog.get(it.next());
            items.add(item);
        }
        return items;
        // TODO: Might want to retrieve from the cache instead? (Discuss)
         */
        return mBacklogLocalDataSource.getAll();
    }

    @Override
    public List<BacklogItem> getAll(@NonNull BacklogItem.Status status) {
        /**
        List<BacklogItem> items = new ArrayList<>();
        Set<String> keys = mCachedBacklog.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            BacklogItem item = mCachedBacklog.get(it.next());
            if (status == item.getStatus()) {
                items.add(item);
            }
        }
        return items;
        // TODO: Might want to retrieve from the cache instead? (Discuss)
         */
        return mBacklogLocalDataSource.getAll(status);
    }
}
