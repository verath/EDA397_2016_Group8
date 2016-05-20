package se.chalmers.eda397.group8.pairprogramming.reqspec.data;

import android.support.annotation.NonNull;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.reqspec.data.local.RequirementLocalDataSource;

public class RequirementRepository implements RequirementDataSource {

    private static RequirementRepository sInstance;
    private final RequirementLocalDataSource mLocalDataSource;

    private RequirementRepository(RequirementLocalDataSource requirementLocalDataSource) {
        mLocalDataSource = requirementLocalDataSource;
    }

    public static RequirementRepository getInstance(RequirementLocalDataSource requirementLocalDataSource) {
        if (sInstance == null) {
            sInstance = new RequirementRepository(requirementLocalDataSource);
        }
        return sInstance;
    }

    @Override
    public Requirement get(String id) {
        return mLocalDataSource.get(id);
    }

    @Override
    public boolean save(Requirement item) {
        return mLocalDataSource.save(item);
    }

    @Override
    public Requirement delete(String id) {
        return mLocalDataSource.delete(id);
    }

    @NonNull
    @Override
    public List<Requirement> getAll() {
        return mLocalDataSource.getAll();
    }
}
