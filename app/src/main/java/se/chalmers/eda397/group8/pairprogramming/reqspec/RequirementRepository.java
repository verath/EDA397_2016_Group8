package se.chalmers.eda397.group8.pairprogramming.reqspec;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequirementRepository implements RequirementDataSource {

    private static RequirementRepository sInstance;

    private final Map<String, Requirement> mDummyRequirements;

    private RequirementRepository() {
        mDummyRequirements = new HashMap<>();
    }

    public static RequirementRepository getInstance() {
        if (sInstance == null) {
            sInstance = new RequirementRepository();
        }
        return sInstance;
    }

    @Override
    public Requirement get(String id) {
        return mDummyRequirements.get(id);
    }

    @Override
    public boolean save(Requirement item) {
        mDummyRequirements.put(item.getId(), item);
        return true;
    }

    @Override
    public Requirement delete(String id) {
        return mDummyRequirements.remove(id);
    }

    @NonNull
    @Override
    public List<Requirement> getAll() {
        return new ArrayList<>(mDummyRequirements.values());
    }
}
