package se.chalmers.eda397.group8.pairprogramming.note;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The NoteRepository is an implementation of the NoteDataSource,
 * providing access to Note data. Following the google sample, this
 * class should likely delegate to other sources, depending on the
 * operation and state of the cache.
 * <p>
 * However, for now the NoteRepository uses only an in-memory cache.
 */
public class NoteRepository implements NoteDataSource {

    private static NoteRepository sInstance;
    private final static Map<String, Note> mDummyNotes = new HashMap<>();
    private final NoteDataSource mNoteDataSource;

    /**
     * // This is (apparently) how one "should" do singletons now,
     * // see https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
     * private static class NoteRepositoryHolder {
     * public static final NoteRepository instance = new NoteRepository();
     * }
     */

    private NoteRepository(NoteDataSource noteDataSource) {
        mNoteDataSource = noteDataSource;
        List<Note> notes = mNoteDataSource.getAll();
        for (Note n : notes) {
            mDummyNotes.put(n.getId(), n);
        }
    }

    public static NoteRepository getInstance(@NonNull NoteDataSource mNoteDataSource) {
        if (sInstance == null) {
            sInstance = new NoteRepository(mNoteDataSource);
        }
        return sInstance;
    }

    @NonNull
    @Override
    public List<Note> getAll() {
        return mNoteDataSource.getAll();
    }

    @Nullable
    @Override
    public Note get(@NonNull String noteId) {
        Note note = mDummyNotes.get(noteId);
        if (note != null) {
            return note;
        }

        // Retrieve it from the local database if it's not in the cache
        return mNoteDataSource.get(noteId);
    }

    @Override
    public boolean save(@NonNull Note note) {
        return mNoteDataSource.save(note);
    }

    @Override
    public Note delete(@NonNull String noteId) {
        return mNoteDataSource.delete(noteId);
    }
}
