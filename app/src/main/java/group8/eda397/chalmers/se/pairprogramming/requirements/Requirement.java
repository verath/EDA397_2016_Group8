package group8.eda397.chalmers.se.pairprogramming.requirements;

import android.support.annotation.NonNull;

import java.io.File;

/**
 * Model class for requirement.
 */
public class Requirement {

    @NonNull
    private File pdf;

    public Requirement(@NonNull File file){
        //TODO: Check that it really is a pdf file (ends with .pdf)?
        this.pdf = file;
    }

    @NonNull
    public File getFile(){
        return pdf;
    }

}
