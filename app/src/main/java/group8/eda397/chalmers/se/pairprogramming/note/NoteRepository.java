package group8.eda397.chalmers.se.pairprogramming.note;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The NoteRepository is an implementation of the NoteDataSource.
 * Currently the NoteRepository only uses an in-memory cache.
 */
public class NoteRepository implements NoteDataSource {

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
            Note note = new Note("Note " + i, "Lorem ipsum dolor sit amet, consectetur " +
                    "adipiscing elit. Maecenas velit lectus, convallis non lectus id, " +
                    "aliquet auctor turpis. Quisque luctus.");

            mDummyNotes.put(note.getId(), note);
        }
    }

    @NonNull
    @Override
    public List<Note> getNotes() {
        return new ArrayList<>(mDummyNotes.values());
    }

    @Nullable
    @Override
    public Note getNote(@NonNull String noteId) {
        return mDummyNotes.get(noteId);
    }

    @Override
    public void saveNote(@NonNull Note note) {
        mDummyNotes.put(note.getId(), note);
    }

    @Override
    public void deleteNote(@NonNull String noteId) {
        mDummyNotes.remove(noteId);
    }
}
