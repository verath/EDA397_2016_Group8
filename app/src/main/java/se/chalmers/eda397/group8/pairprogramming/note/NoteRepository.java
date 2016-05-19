package se.chalmers.eda397.group8.pairprogramming.note;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.chalmers.eda397.group8.pairprogramming.note.database.local.NoteLocalDataSource;

/**
 * The NoteRepository is an implementation of the NoteDataSource,
 * providing access to Note data. Following the google sample, this
 * class should likely delegate to other sources, depending on the
 * operation and state of the cache.
 * <p/>
 * However, for now the NoteRepository uses only an in-memory cache.
 */
public class NoteRepository implements NoteDataSource {

    private static NoteRepository sInstance;
    private final static Map<String, Note> mDummyNotes = new HashMap<>();
    private final NoteLocalDataSource mLocalDataSource;

    private NoteRepository(NoteLocalDataSource noteLocalDataSource) {
        mLocalDataSource = noteLocalDataSource;
        List<Note> notes = mLocalDataSource.getAll();
        for (Note n : notes) {
            mDummyNotes.put(n.getId(), n);
        }
    }

    public static NoteRepository getInstance(@NonNull NoteLocalDataSource mNoteDataSource) {
        if (sInstance == null) {
            sInstance = new NoteRepository(mNoteDataSource);
        }
        return sInstance;
    }

    @NonNull
    @Override
    public List<Note> getAll() {
        return mLocalDataSource.getAll();
    }

    @Nullable
    @Override
    public Note get(@NonNull String noteId) {
        Note note = mDummyNotes.get(noteId);
        if (note != null) {
            return note;
        }

        // Retrieve it from the local database if it's not in the cache
        return mLocalDataSource.get(noteId);
    }

    @Override
    public boolean save(@NonNull Note note) {
        return mLocalDataSource.save(note);
    }

    @Override
    public Note delete(@NonNull String noteId) {
        return mLocalDataSource.delete(noteId);
    }
}
