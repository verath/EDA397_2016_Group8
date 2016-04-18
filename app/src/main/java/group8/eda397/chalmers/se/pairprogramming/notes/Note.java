package group8.eda397.chalmers.se.pairprogramming.notes;

import android.support.annotation.NonNull;

/**
 * View layer model class for a note.
 */
public class Note {
    @NonNull
    private final String mText;
    private final String mTitle;

    public Note(@NonNull String title, @NonNull String text) {
        this.mText = text;
        this.mTitle = title;
    }

    @NonNull
    public String getText() {
        return mText;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }
}
