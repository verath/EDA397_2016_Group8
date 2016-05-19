package se.chalmers.eda397.group8.pairprogramming.reqspec;

import android.support.annotation.NonNull;

/**
 * Model class for requirement.
 */
public class RequirementSpecification {

    private static int sCount = 0;

    @NonNull
    private final String mId;

    @NonNull
    private final String mFilePath;

    public RequirementSpecification(@NonNull String filePath) {
        this(Integer.toString(sCount++), filePath);
    }

    public RequirementSpecification(@NonNull String id, @NonNull String filePath) {
        mId = id;
        mFilePath = filePath;
    }

    @NonNull
    public String getFilePath() {
        return mFilePath;
    }

    @NonNull
    public String getId() {
        return mId;
    }

}
