package se.chalmers.eda397.group8.pairprogramming.note;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.data.DataSource;

/**
 * Entry point for accessing and manipulating note data.
 * <p/>
 * Currently methods block the caller until completed. Should be extended
 * to use callbacks (or some other method) if a data source is added
 * that blocking is not acceptable for (basically anything but in-memory).
 * <p/>
 * See https://github.com/googlesamples/android-architecture/tree/todo-mvp/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/data/source
 */
public interface NoteDataSource extends DataSource<Note> {

    /**
     * Returns all notes in the data source as a list.
     *
     * @return all notes.
     */
    @NonNull
    @Override
    List<Note> getAll();

    /**
     * Gets a single note by its id.
     *
     * @param noteId The id of the note to find.
     * @return The note with this id, or null if none was found.
     */
    @Nullable
    @Override
    Note get(@NonNull String noteId);

    /**
     * Saves a note to the data source. If a note with the same id already
     * exist, this operation will overwrite the old note. Otherwise, a new
     * note will simply be inserted.
     *
     * @param note The Note to save.
     */
    @Override
    boolean save(@NonNull Note note);

    /**
     * Deletes a note by id.
     *
     * @param noteId The id of the note to delete.
     */
    @Override
    @Nullable
    Note delete(@NonNull String noteId);
}
