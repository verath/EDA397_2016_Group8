package se.chalmers.eda397.group8.pairprogramming.backlog.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BacklogStatusRepository implements BacklogStatusDataSource {

    private static BacklogStatusRepository sInstance;
    private final Map<String, BacklogStatus> mStatuses = new HashMap<>();

    private BacklogStatusRepository() {
        save(new BacklogStatus("1", "Backlog"));
        save(new BacklogStatus("2", "Ongoing"));
        save(new BacklogStatus("3", "Ready for testing"));
        save(new BacklogStatus("4", "Done"));
        save(new BacklogStatus("5", "More done!"));
        save(new BacklogStatus("6", "So done!"));
        save(new BacklogStatus("7", "Donest"));
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

    @NonNull
    @Override
    public List<BacklogStatus> getAll() {
        return new ArrayList<>(mStatuses.values());
    }
}
