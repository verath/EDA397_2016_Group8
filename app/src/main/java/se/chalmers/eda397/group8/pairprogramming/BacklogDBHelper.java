package se.chalmers.eda397.group8.pairprogramming;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class to help creating and updating database for BacklogItems.
 */
public class BacklogDBHelper extends SQLiteOpenHelper {

    // SQL table name and column names
    public static final String TABLE_BACKLOGS = "backlogs";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_STATUS = "status";

    private static final String DATABASE_NAME = "backlogs.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement to create database
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_BACKLOGS + "(" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE
            + " TEXT NOT NULL, " + COLUMN_CONTENT
            + " TEXT NOT NULL, " + COLUMN_STATUS
            + " TEXT NOT NULL"
            + ");";

    public BacklogDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(BacklogDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BACKLOGS);
        onCreate(db);
    }

}
