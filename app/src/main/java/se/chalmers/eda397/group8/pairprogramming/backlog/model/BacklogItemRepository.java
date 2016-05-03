package se.chalmers.eda397.group8.pairprogramming.backlog.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
        for (BacklogItem.Status status : BacklogItem.Status.values()) {
            for (int i = 0; i < 20; i++) {
                BacklogItem item = new BacklogItem("Item " + (i + 1), "Content", status);
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

    @Override
    public List<BacklogItem> getAll() {
        List<BacklogItem> items = new ArrayList<>();
        Set<String> keys = mBacklog.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            BacklogItem item = mBacklog.get(it.next());
            items.add(item);
        }
        return items;
    }

    @Override
    public List<BacklogItem> getAll(BacklogItem.Status status) {
        List<BacklogItem> items = new ArrayList<>();
        Set<String> keys = mBacklog.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            BacklogItem item = mBacklog.get(it.next());
            if (status == item.getStatus()) {
                items.add(item);
            }
        }
        return items;
    }
}
