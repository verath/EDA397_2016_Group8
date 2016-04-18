package group8.eda397.chalmers.se.pairprogramming.note;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Entry point for accessing and manipulating note data.
 * <p/>
 * Currently methods block the caller until completed. Should be extended
 * to use callbacks (or some other method) if a data source is added
 * that blocking is not acceptable for (basically anything but in-memory).
 * <p/>
 * See https://github.com/googlesamples/android-architecture/tree/todo-mvp/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/data/source
 */
public interface NoteDataSource {

    @NonNull
    List<Note> getNotes();

    @Nullable
    Note getNote(@NonNull String noteId);

    void saveNote(@NonNull Note note);

    void deleteNote(@NonNull String noteId);
}
