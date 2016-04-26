package se.chalmers.eda397.group8.pairprogramming.note;

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
     * Creates a new Note from an <b>existing</b> id. This is used
     * if one wants to edit a note.
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
     * Creates a new Note. An id is automatically generated.
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

    /**
     * Tests if the note is empty, i.e. has an empty title and text.
     *
     * @return True if empty.
     */
    public boolean isEmpty() {
        return mText.isEmpty() && mTitle.isEmpty();
    }
}
