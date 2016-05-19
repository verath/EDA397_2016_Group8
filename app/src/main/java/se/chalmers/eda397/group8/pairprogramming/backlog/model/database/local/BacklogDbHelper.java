package se.chalmers.eda397.group8.pairprogramming.backlog.model.database.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class to help creating and updating database for BacklogItem
 */
public class BacklogDbHelper extends SQLiteOpenHelper {

    // SQL table name and column names
    public static final String TABLE_BACKLOGS = "backlogs";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_STATUS_ID = "status_id";
    public static final String COLUMN_REQUIREMENT_ID = "requirement_id";

    private static final String DATABASE_NAME = "backlogs.db";
    private static final int DATABASE_VERSION = 4;

    // SQL statement to create database
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_BACKLOGS + "("
            + COLUMN_ID + " PRIMARY KEY, "
            + COLUMN_TITLE + " TEXT NOT NULL, "
            + COLUMN_CONTENT + " TEXT NOT NULL, "
            + COLUMN_STATUS_ID + " TEXT NOT NULL, "
            + COLUMN_REQUIREMENT_ID + " TEXT NOT NULL "
            + ");";

    public BacklogDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(BacklogDbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BACKLOGS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(BacklogDbHelper.class.getName(),
                "Downgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BACKLOGS);
        onCreate(db);
    }
}
