package se.chalmers.eda397.group8.pairprogramming.backlog.model.database.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;

/**
 * A data source implemented as a database for BacklogItem,
 * with the basic functions for persistent storage (CRUD).
 */
public class BacklogLocalDataSource implements BacklogItemDataSource {

    private static final String[] ALL_COLUMNS = {
            BacklogDbHelper.COLUMN_ID,
            BacklogDbHelper.COLUMN_TITLE,
            BacklogDbHelper.COLUMN_CONTENT,
            BacklogDbHelper.COLUMN_STATUS_ID,
            BacklogDbHelper.COLUMN_PAGE
    };
    private static BacklogLocalDataSource sInstance;
    private SQLiteDatabase mDb;
    private BacklogDbHelper mDbHelper;

    public BacklogLocalDataSource(Context context) {
        mDbHelper = new BacklogDbHelper(context);
    }

    public static BacklogLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new BacklogLocalDataSource(context);
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

    @Override
    public boolean save(@NonNull BacklogItem item) {
        open();

        ContentValues values = new ContentValues();
        values.put(BacklogDbHelper.COLUMN_ID, item.getId());
        values.put(BacklogDbHelper.COLUMN_TITLE, item.getTitle());
        values.put(BacklogDbHelper.COLUMN_CONTENT, item.getContent());
        values.put(BacklogDbHelper.COLUMN_STATUS_ID, item.getStatusId());
        values.put(BacklogDbHelper.COLUMN_PAGE, item.getPage());

        long id = mDb.insertWithOnConflict(BacklogDbHelper.TABLE_BACKLOGS, null, values, SQLiteDatabase.CONFLICT_REPLACE);

        close();

        return (id != -1);
    }

    @Override
    public BacklogItem delete(@NonNull String id) {
        open();

        String selection = BacklogDbHelper.COLUMN_ID + " LIKE ?";
        String[] selectionArguments = {id};
        mDb.delete(BacklogDbHelper.TABLE_BACKLOGS, selection, selectionArguments);

        close();
        // TODO: Might want to return a bool instead? Unnecessary to return BacklogItem deleted.
        return null;
    }

    @Override
    public BacklogItem get(@NonNull String id) {
        open();

        String selection = BacklogDbHelper.COLUMN_ID + " LIKE ?";
        String[] selectionArgs = {id};

        Cursor c = mDb.query(BacklogDbHelper.TABLE_BACKLOGS, ALL_COLUMNS, selection, selectionArgs, null, null, null);

        BacklogItem backlogItem = null;

        // Found row in database
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            String itemId = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_ID));
            String title = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_TITLE));
            String content = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_CONTENT));
            String statusId = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_STATUS_ID));
            String page = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_PAGE));
            backlogItem = new BacklogItem(itemId, title, content, statusId, page);
        }
        if (c != null) {
            c.close();
        }

        close();

        return backlogItem;
    }

    @NonNull
    @Override
    public List<BacklogItem> getAll() {
        open();
        List<BacklogItem> backlogItems = new ArrayList<BacklogItem>();

        Cursor c = mDb.query(BacklogDbHelper.TABLE_BACKLOGS, ALL_COLUMNS, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                String itemId = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_ID));
                String title = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_TITLE));
                String content = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_CONTENT));
                String status = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_STATUS_ID));
                String page = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_PAGE));
                backlogItems.add(new BacklogItem(itemId, title, content, status, page));
            }
        }
        if (c != null) {
            c.close();
        }

        close();

        return backlogItems;
    }

    @Override
    public List<BacklogItem> getAllByStatus(String statusId) {
        open();
        List<BacklogItem> backlogItems = new ArrayList<BacklogItem>();

        String selection = BacklogDbHelper.COLUMN_STATUS_ID + " LIKE ?";
        String[] selectionArgs = {statusId};
        Cursor c = mDb.query(BacklogDbHelper.TABLE_BACKLOGS, ALL_COLUMNS, selection, selectionArgs, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                String itemId = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_ID));
                String title = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_TITLE));
                String content = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_CONTENT));
                String status = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_STATUS_ID));
                String page = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_PAGE));
                backlogItems.add(new BacklogItem(itemId, title, content, status, page));
            }
        }
        if (c != null) {
            c.close();
        }

        close();

        return backlogItems;
    }

}
