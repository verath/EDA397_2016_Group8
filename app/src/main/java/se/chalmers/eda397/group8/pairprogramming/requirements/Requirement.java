package se.chalmers.eda397.group8.pairprogramming.requirements;

import android.support.annotation.NonNull;

/**
 * Model class for requirement.
 */
public class Requirement {

    @NonNull
    private final String filePath;

    public Requirement(@NonNull String filePath) {
        this.filePath = filePath;
    }

    @NonNull
    public String getFilePath() {
        return filePath;
    }

}
