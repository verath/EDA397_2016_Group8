package se.chalmers.eda397.group8.pairprogramming.note;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The NoteRepository is an implementation of the NoteDataSource,
 * providing access to Note data. Following the google sample, this
 * class should likely delegate to other sources, depending on the
 * operation and state of the cache.
 * <p/>
 * However, for now the NoteRepository uses only an in-memory cache.
 */
public class NoteRepository implements NoteDataSource {

    // This is (apparently) how one "should" do singletons now,
    // see https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
    private static class NoteRepositoryHolder {
        public static final NoteRepository instance = new NoteRepository();
    }

    private NoteRepository() {
        // Singleton, use #getInstance
    }

    public static NoteRepository getInstance() {
        return NoteRepositoryHolder.instance;
    }

    private final static Map<String, Note> mDummyNotes = new HashMap<>();

    static {
        for (int i = 1; i <= 10; i++) {
            Note note = new Note("Note " + i, "Lorem ipsum dolor sit amet, consectetur "
                    + "adipiscing elit. Maecenas velit lectus, convallis non lectus id, "
                    + "aliquet auctor turpis. Quisque luctus.");

            mDummyNotes.put(note.getId(), note);
        }
    }

    @NonNull
    @Override
    public List<Note> getAll() {
        return new ArrayList<>(mDummyNotes.values());
    }

    @Nullable
    @Override
    public Note get(@NonNull String noteId) {
        return mDummyNotes.get(noteId);
    }

    @Override
    public boolean save(@NonNull Note note) {
        mDummyNotes.put(note.getId(), note);
        return true;
    }

    @Override
    public Note delete(@NonNull String noteId) {
        return mDummyNotes.remove(noteId);
    }
}
