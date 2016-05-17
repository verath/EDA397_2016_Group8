package se.chalmers.eda397.group8.pairprogramming.note;

import android.support.annotation.NonNull;

import java.util.UUID;

/**
 * View layer model class for a note.
 */
public class Note {

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
        this(title, text, UUID.randomUUID().toString());
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
