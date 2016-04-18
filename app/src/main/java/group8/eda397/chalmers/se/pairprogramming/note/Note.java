package group8.eda397.chalmers.se.pairprogramming.note;

import android.support.annotation.NonNull;

/**
 * View layer model class for a note.
 */
public class Note {
    /**
     * A counter for generating ids of. Note that this is not a good strategy if
     * one wants to ever be able to sync notes. Instead one would use an id that
     * is likely to be unique, even across devices. Commonly UUID is used for this
     * purpose, but we don't bother for now.
     */
    private static int counter = 0;

    private final String mId;
    private final String mText;
    private final String mTitle;

    /**
     * Creates a new Note from an existing id.
     *
     * @param title The title of the note.
     * @param text  The text of the note.
     * @param id    The id of an already existing note.
     */
    public Note(@NonNull String title, @NonNull String text, @NonNull String id) {
        this.mTitle = title;
        this.mText = text;
        this.mId = id;
    }

    /**
     * Creates a new Note and generates an id for it.
     *
     * @param title The title of the note.
     * @param text  The text of the note.
     */
    public Note(@NonNull String title, @NonNull String text) {
        this(title, text, String.valueOf(++counter));
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
