package se.chalmers.eda397.group8.pairprogramming.backlog.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BacklogStatusRepository implements BacklogStatusDataSource {

    private static BacklogStatusRepository sInstance;
    private final Map<String, BacklogStatus> mStatuses = new HashMap<>();

    private BacklogStatusRepository() {

    }

    public static BacklogStatusDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new BacklogStatusRepository();
        }
        return sInstance;
    }

    @Override
    public BacklogStatus get(String id) {
        return mStatuses.get(id);
    }

    @Override
    public boolean save(BacklogStatus item) {
        mStatuses.put(item.getId(), item);
        return true;
    }

    @Override
    public BacklogStatus delete(String id) {
        return mStatuses.remove(id);
    }

    @Override
    public List<BacklogStatus> getAll() {
        return new ArrayList<>(mStatuses.values());
    }
}
