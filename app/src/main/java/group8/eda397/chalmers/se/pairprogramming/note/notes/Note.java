package group8.eda397.chalmers.se.pairprogramming.note.notes;

import android.support.annotation.NonNull;

/**
 * View layer model class for a note.
 */
public class Note {
    private final String mId;
    private final String mText;
    private final String mTitle;

    public Note(@NonNull String id, @NonNull String title, @NonNull String text) {
        this.mId = id;
        this.mText = text;
        this.mTitle = title;
    }

    @NonNull
    public String getId() {
        return mId;
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
