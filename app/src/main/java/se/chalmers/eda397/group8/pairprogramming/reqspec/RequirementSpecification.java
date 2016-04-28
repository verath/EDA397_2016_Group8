package se.chalmers.eda397.group8.pairprogramming.reqspec;

import android.support.annotation.NonNull;

/**
 * Model class for requirement.
 */
public class RequirementSpecification {

    @NonNull
    private final String mFilePath;

    public RequirementSpecification(@NonNull String filePath) {
        mFilePath = filePath;
    }

    @NonNull
    public String getFilePath() {
        return mFilePath;
    }

}
