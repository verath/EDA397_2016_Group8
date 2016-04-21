package group8.eda397.chalmers.se.pairprogramming.notesdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import group8.eda397.chalmers.se.pairprogramming.notes.Note;

public class NotesDAO {
    private SQLiteDatabase db;
    private NotesDBHelper helper;
    private String[] allColumns = {NotesDBHelper.COLUMN_ID,
            NotesDBHelper.COLUMN_TITLE,
            NotesDBHelper.COLUMN_TEXT,};

    public NotesDAO(Context context) {
        helper = new NotesDBHelper(context);
    }

    /**
     * Open the database session to be able to perform
     *
     * @throws SQLException
     */
    public void open() throws SQLException {
        db = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public void clearTable() {
        helper.onUpgrade(db, 1, 1);
    }

    /**
     * Method for adding new Note to internal database table (as primitive values)
     *
     * @param title
     * @param text
     * @return Note
     */
    public Note createNote(String title, String text) {

        ContentValues values = new ContentValues();
        values.put(NotesDBHelper.COLUMN_ID, 1); // Add an ID variable to Note?
        values.put(NotesDBHelper.COLUMN_TITLE, title);
        values.put(NotesDBHelper.COLUMN_TEXT, text);

        // Insert values and grab the id of the new entry
        long insertId = db.insert(NotesDBHelper.TABLE_NOTES, null, values);

        // Cursor at the newly added entry
        Cursor cursor = db.query(NotesDBHelper.TABLE_NOTES, allColumns,
                NotesDBHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        // Create a new InfoNode from the db, making sure it's in
        Note note = cursorToInfo(cursor);
        cursor.close();
        return note;
    }

    /**
     * Method for deleting Note in internal database
     */
    public void deleteNote(Note note) {
        //TODO: Need a better identifier when deleting Note objects...
        String title = note.getTitle();
        db.delete(NotesDBHelper.TABLE_NOTES, NotesDBHelper.COLUMN_TITLE + " = " + title, null);
    }

    /**
     * Method for getting all data as list of Note from internal database.
     *
     * @return
     */
    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();

        Cursor cursor = db.query(NotesDBHelper.TABLE_NOTES, allColumns,
                null, null, null, null, null);

        // Loop through all entries
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Note note = cursorToInfo(cursor);
            notes.add(note);
            cursor.moveToNext();
        }

        cursor.close();
        return notes;
    }

    /**
     * Creates a Note from the database at #cursor (with primitive data)
     *
     * @param cursor
     * @return Note
     */
    private Note cursorToInfo(Cursor cursor) {
        return new Note(cursor.getString(1), cursor.getString(2));
    }

}
