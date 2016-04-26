package se.chalmers.eda397.group8.pairprogramming.requirements;

import android.support.annotation.NonNull;

/**
 * Model class for requirement.
 */
public class Requirement {

    @NonNull
    private final String mFilePath;

    public Requirement(@NonNull String filePath) {
        mFilePath = filePath;
    }

    @NonNull
    public String getFilePath() {
        return mFilePath;
    }

}
