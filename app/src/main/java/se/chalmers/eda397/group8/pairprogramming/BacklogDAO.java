package se.chalmers.eda397.group8.pairprogramming;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;

public class BacklogDAO {
    private SQLiteDatabase db;
    private BacklogDBHelper helper;
    private String[] allColumns = {BacklogDBHelper.COLUMN_ID,
            BacklogDBHelper.COLUMN_TITLE,
            BacklogDBHelper.COLUMN_CONTENT,
            BacklogDBHelper.COLUMN_STATUS};

    public BacklogDAO(Context context) {
        helper = new BacklogDBHelper(context);
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

    public BacklogItem createBacklogItem(String title, String content, BacklogItem.Status status) {

        ContentValues values = new ContentValues();
        values.put(BacklogDBHelper.COLUMN_ID, 1); // Add an ID variable to BacklogItem?
        values.put(BacklogDBHelper.COLUMN_TITLE, title);
        values.put(BacklogDBHelper.COLUMN_CONTENT, content);
        values.put(BacklogDBHelper.COLUMN_STATUS, status.toString()); // Double check

        // Insert values and grab the id of the new entry
        long insertId = db.insert(BacklogDBHelper.TABLE_BACKLOGS, null, values);

        // Cursor at the newly added entry
        Cursor cursor = db.query(BacklogDBHelper.TABLE_BACKLOGS, allColumns,
                BacklogDBHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        // Create a new InfoNode from the db, making sure it's in
        BacklogItem backlogItem = cursorToBacklogItem(cursor);
        cursor.close();
        return backlogItem;
    }

    /**
     * Method for deleting Note in internal database
     */
    public void deleteBacklogItem(BacklogItem backlogItem) {
        //TODO: Need a better identifier when deleting Note objects...
        String title = backlogItem.getTitle();
        db.delete(BacklogDBHelper.TABLE_BACKLOGS, BacklogDBHelper.COLUMN_TITLE + " = " + title, null);
    }

    public List<BacklogItem> getAllBacklogItems() {
        List<BacklogItem> backlogs = new ArrayList<>();

        Cursor cursor = db.query(BacklogDBHelper.TABLE_BACKLOGS, allColumns,
                null, null, null, null, null);

        // Loop through all entries
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BacklogItem backlogItem = cursorToBacklogItem(cursor);
            backlogs.add(backlogItem);
            cursor.moveToNext();
        }

        cursor.close();
        return backlogs;
    }

    private BacklogItem cursorToBacklogItem(Cursor cursor) {
        return new BacklogItem(cursor.getString(1), cursor.getString(2), BacklogItem.Status.valueOf(cursor.getString(3)));
    }

}
