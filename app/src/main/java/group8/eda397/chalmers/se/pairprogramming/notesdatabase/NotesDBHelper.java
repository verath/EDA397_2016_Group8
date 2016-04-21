package group8.eda397.chalmers.se.pairprogramming.notesdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class to help creating and updating database for Notes.
 */
public class NotesDBHelper extends SQLiteOpenHelper {

    // SQL table name and column names
    public static final String TABLE_NOTES = "notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_TEXT = "text";

    private static final String DATABASE_NAME = "markers.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement to create database
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NOTES + "(" + COLUMN_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE
            + " TEXT NOT NULL, " + COLUMN_TEXT
            + " TEXT NOT NULL, "
            + ");";

    public NotesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(NotesDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

}
