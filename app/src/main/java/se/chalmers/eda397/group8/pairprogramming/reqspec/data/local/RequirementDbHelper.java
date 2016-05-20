package se.chalmers.eda397.group8.pairprogramming.reqspec.data.local;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RequirementDbHelper extends SQLiteOpenHelper {

    // SQL table name and column names
    public static final String TABLE_REQUIREMENTS = "requirements";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PAGE_NUMBER = "page_number";
    public static final String COLUMN_REQ_SPEC_ID = "req_spec_id";

    private static final String DATABASE_NAME = "requirements.db";
    private static final int DATABASE_VERSION = 1;

    // SQL statement to create database
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_REQUIREMENTS + "("
            + COLUMN_ID + " PRIMARY KEY, "
            + COLUMN_PAGE_NUMBER + " TEXT NOT NULL, "
            + COLUMN_REQ_SPEC_ID + " TEXT NOT NULL "
            + ");";

    public RequirementDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(RequirementDbHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUIREMENTS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(RequirementDbHelper.class.getName(),
                "Downgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUIREMENTS);
        onCreate(db);
    }
}
