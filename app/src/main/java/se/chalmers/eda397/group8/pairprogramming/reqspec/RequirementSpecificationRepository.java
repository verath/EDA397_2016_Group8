package se.chalmers.eda397.group8.pairprogramming.reqspec;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequirementSpecificationRepository implements RequirementSpecificationDataSource {
    private static RequirementSpecificationRepository sInstance;

    private final static String LOG_TAG = "ReqSpecRepository";

    private final Map<String, RequirementSpecification> mDummyData = new HashMap<>();

    private RequirementSpecificationRepository(Context context) {
        createDummyData(context);
    }

    public static RequirementSpecificationRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new RequirementSpecificationRepository(context);
        }
        return sInstance;
    }

    @Override
    public RequirementSpecification get(String id) {
        return mDummyData.get(id);
    }

    @Override
    public boolean save(RequirementSpecification item) {
        mDummyData.put(item.getId(), item);
        return true;
    }

    @Override
    public RequirementSpecification delete(String id) {
        return mDummyData.remove(id);
    }

    @NonNull
    @Override
    public List<RequirementSpecification> getAll() {
        return new ArrayList<>(mDummyData.values());
    }

    private void createDummyData(Context context) {
        String[] assetFiles = null;
        try {
            assetFiles = context.getAssets().list("");
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error listing assets", e);
        }

        if (assetFiles != null) {
            for (String fileName : assetFiles) {
                if (fileName.endsWith(".pdf")) {
                    save(new RequirementSpecification(fileName));
                }
            }
        }
    }
}
