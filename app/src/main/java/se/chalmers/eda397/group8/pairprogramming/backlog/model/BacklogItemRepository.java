package se.chalmers.eda397.group8.pairprogramming.backlog.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Repository of backlog items.
 */
public class BacklogItemRepository implements BacklogItemDataSource {

    private static BacklogItemRepository sInstance;
    private final Map<String, BacklogItem> mBacklog = new HashMap<>();

    private BacklogItemRepository() {
        mBacklog.clear();

        // TODO: populate with real data
        // Populate repository with dummy data:
        List<BacklogStatus> statuses = BacklogStatusRepository.getInstance().getAll();
        for (BacklogStatus status : statuses) {
            for (int i = 0; i < 20; i++) {
                BacklogItem item = new BacklogItem("Item " + (i + 1), "Content", status.getId());
                mBacklog.put(item.getId(), item);
            }
        }
    }

    public static BacklogItemRepository getInstance() {
        if (sInstance == null) {
            sInstance = new BacklogItemRepository();
        }
        return sInstance;
    }

    @Override
    public BacklogItem get(String id) {
        return mBacklog.get(id);
    }

    @Override
    public boolean save(BacklogItem item) {
        mBacklog.put(item.getId(), item);
        return true;
    }

    @Override
    public BacklogItem delete(String id) {
        return mBacklog.remove(id);
    }

    @NonNull
    @Override
    public List<BacklogItem> getAll() {
        List<BacklogItem> items = new ArrayList<>();
        Set<String> keys = mBacklog.keySet();
        for (String key : keys) {
            BacklogItem item = mBacklog.get(key);
            items.add(item);
        }
        return items;
    }

    @Override
    public List<BacklogItem> getAllByStatus(String statusId) {
        List<BacklogItem> items = new ArrayList<>();
        Set<String> keys = mBacklog.keySet();
        for (String key : keys) {
            BacklogItem item = mBacklog.get(key);
            if (statusId.equals(item.getStatusId())) {
                items.add(item);
            }
        }
        return items;
    }
}
