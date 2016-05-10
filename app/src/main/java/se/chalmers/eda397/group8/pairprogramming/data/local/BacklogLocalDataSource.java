package se.chalmers.eda397.group8.pairprogramming.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItem;
import se.chalmers.eda397.group8.pairprogramming.backlog.model.BacklogItemDataSource;

public class BacklogLocalDataSource implements BacklogItemDataSource {

    private static BacklogLocalDataSource INSTANCE;
    private SQLiteDatabase db;
    private BacklogDbHelper helper;
    private String[] allColumns = {
            BacklogDbHelper.COLUMN_ID,
            BacklogDbHelper.COLUMN_TITLE,
            BacklogDbHelper.COLUMN_CONTENT,
            BacklogDbHelper.COLUMN_STATUS
    };

    public BacklogLocalDataSource(Context context) {
        helper = new BacklogDbHelper(context);
    }

    public static BacklogLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new BacklogLocalDataSource(context);
        }
        return INSTANCE;
    }

    public void open() {
        db = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public void clearTable() {
        helper.onUpgrade(db, 1, 1);
    }

    @Override
    public List<BacklogItem> getAll(@NonNull BacklogItem.Status status) {
        open();
        List<BacklogItem> backlogItems = new ArrayList<BacklogItem>();

        String selection = BacklogDbHelper.COLUMN_STATUS + " LIKE ?";
        String[] selectionArgs = {status.name()};
        Cursor c = db.query(BacklogDbHelper.TABLE_BACKLOGS, allColumns, selection, selectionArgs, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                String itemId = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_ID));
                String title = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_TITLE));
                String content = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_CONTENT));
                BacklogItem.Status bStatus = BacklogItem.Status.valueOf(c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_STATUS)));
                backlogItems.add(new BacklogItem(itemId, title, content, bStatus));
            }
        }
        if (c != null) {
            c.close();
        }

        close();

        return backlogItems;
    }

    @Override
    public BacklogItem get(@NonNull String id) {
        open();

        String selection = BacklogDbHelper.COLUMN_ID + " LIKE ?";
        String[] selectionArgs = {id};

        Cursor c = db.query(BacklogDbHelper.TABLE_BACKLOGS, allColumns, selection, selectionArgs, null, null, null);

        BacklogItem backlogItem = null;

        // Found row in database
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            String itemId = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_ID));
            String title = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_TITLE));
            String content = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_CONTENT));
            BacklogItem.Status status = BacklogItem.Status.valueOf(c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_STATUS)));
            backlogItem = new BacklogItem(itemId, title, content, status);
        }
        if (c != null) {
            c.close();
        }

        close();

        return backlogItem;
    }

    @Override
    public boolean save(@NonNull BacklogItem item) {
        // TODO: WHEN EDITING, DELETE FIRST AND THEN ADD INTO DATABSE (IT BUGS WHEN EDITING ONLY STATUS CREATING DUPLICATES)
        open();

        ContentValues values = new ContentValues();
        values.put(BacklogDbHelper.COLUMN_TITLE, item.getTitle());
        values.put(BacklogDbHelper.COLUMN_CONTENT, item.getContent());
        values.put(BacklogDbHelper.COLUMN_STATUS, item.getStatus().name());

        long id = db.insert(BacklogDbHelper.TABLE_BACKLOGS, null, values);

        close();

        if (id != -1) {
            return true;
        }
        return false;
    }

    @Override
    public BacklogItem delete(@NonNull String id) {
        open();

        String selection = BacklogDbHelper.COLUMN_ID + " LIKE ?";
        String[] selectionArguments = {id};
        db.delete(BacklogDbHelper.TABLE_BACKLOGS, selection, selectionArguments);

        close();
        // TODO: Might want to return a bool instead?
        return null;
    }

    @Override
    public List<BacklogItem> getAll() {
        open();
        List<BacklogItem> backlogItems = new ArrayList<BacklogItem>();

        Cursor c = db.query(BacklogDbHelper.TABLE_BACKLOGS, allColumns, null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                String itemId = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_ID));
                String title = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_TITLE));
                String content = c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_CONTENT));
                BacklogItem.Status status = BacklogItem.Status.valueOf(c.getString(c.getColumnIndexOrThrow(BacklogDbHelper.COLUMN_STATUS)));
                backlogItems.add(new BacklogItem(itemId, title, content, status));
            }
        }
        if (c != null) {
            c.close();
        }

        close();

        return backlogItems;
    }
}
