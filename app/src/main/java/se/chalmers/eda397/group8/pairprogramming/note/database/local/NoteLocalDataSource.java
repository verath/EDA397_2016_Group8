package se.chalmers.eda397.group8.pairprogramming.note.database.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.note.Note;
import se.chalmers.eda397.group8.pairprogramming.note.NoteDataSource;

public class NoteLocalDataSource implements NoteDataSource {
    private static final String[] ALL_COLUMNS = {
            NoteDbHelper.COLUMN_ID,
            NoteDbHelper.COLUMN_TITLE,
            NoteDbHelper.COLUMN_CONTENT
    };
    private static NoteLocalDataSource sInstance;
    private SQLiteDatabase mDb;
    private NoteDbHelper mDbHelper;

    private NoteLocalDataSource(Context context) {
        mDbHelper = new NoteDbHelper(context);
    }

    public static NoteLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new NoteLocalDataSource(context);
        }
        return sInstance;
    }

    public void open() {
        mDb = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public void clearTable() {
        mDbHelper.onUpgrade(mDb, 1, 1);
    }

    @NonNull
    @Override
    public List<Note> getAll() {
        open();
        List<Note> notes = new ArrayList<Note>();

        Cursor c = mDb.query(NoteDbHelper.TABLE_NOTES, ALL_COLUMNS, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                String itemId = c.getString(c.getColumnIndexOrThrow(NoteDbHelper.COLUMN_ID));
                String title = c.getString(c.getColumnIndexOrThrow(NoteDbHelper.COLUMN_TITLE));
                String content = c.getString(c.getColumnIndexOrThrow(NoteDbHelper.COLUMN_CONTENT));
                notes.add(new Note(title, content, itemId));
            }
        }
        if (c != null) {
            c.close();
        }

        close();

        return notes;
    }

    @Nullable
    @Override
    public Note get(@NonNull String noteId) {
        open();

        String selection = NoteDbHelper.COLUMN_ID + " LIKE ?";
        String[] selectionArgs = {noteId};

        Cursor c = mDb.query(NoteDbHelper.TABLE_NOTES, ALL_COLUMNS, selection, selectionArgs, null, null, null);

        Note note = null;

        // Found row in database
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            String itemId = c.getString(c.getColumnIndexOrThrow(NoteDbHelper.COLUMN_ID));
            String title = c.getString(c.getColumnIndexOrThrow(NoteDbHelper.COLUMN_TITLE));
            String content = c.getString(c.getColumnIndexOrThrow(NoteDbHelper.COLUMN_CONTENT));
            note = new Note(title, content, itemId);
        }
        if (c != null) {
            c.close();
        }

        close();

        return note;
    }

    @Override
    public boolean save(@NonNull Note note) {
        open();

        ContentValues values = new ContentValues();
        values.put(NoteDbHelper.COLUMN_ID, note.getId());
        values.put(NoteDbHelper.COLUMN_TITLE, note.getTitle());
        values.put(NoteDbHelper.COLUMN_CONTENT, note.getText());

        long id = mDb.insertWithOnConflict(NoteDbHelper.TABLE_NOTES, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        close();

        return (id != -1);
    }

    @Nullable
    @Override
    public Note delete(@NonNull String noteId) {
        open();

        String selection = NoteDbHelper.COLUMN_ID + " LIKE ?";
        String[] selectionArguments = {noteId};
        mDb.delete(NoteDbHelper.TABLE_NOTES, selection, selectionArguments);

        close();
        return null;
    }
}
