package group8.eda397.chalmers.se.pairprogramming.requirements;

import android.support.annotation.NonNull;

import java.io.File;

/**
 * Model class for requirement.
 */
public class Requirement {

    @NonNull
    private String filePath;

    public Requirement(@NonNull String filePath) {
        this.filePath = filePath;
    }

    @NonNull
    public String getFilePath() {
        return filePath;
    }

}
