package se.chalmers.eda397.group8.pairprogramming.reqspec.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.eda397.group8.pairprogramming.reqspec.data.Requirement;
import se.chalmers.eda397.group8.pairprogramming.reqspec.data.RequirementDataSource;


public class RequirementLocalDataSource implements RequirementDataSource {

    private static final String[] ALL_COLUMNS = {
            RequirementDbHelper.COLUMN_ID,
            RequirementDbHelper.COLUMN_PAGE_NUMBER,
            RequirementDbHelper.COLUMN_REQ_SPEC_ID
    };
    private static RequirementLocalDataSource sInstance;
    private SQLiteDatabase mDb;
    private RequirementDbHelper mDbHelper;

    public RequirementLocalDataSource(Context context) {
        mDbHelper = new RequirementDbHelper(context);
    }

    public static RequirementLocalDataSource getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new RequirementLocalDataSource(context);
        }
        return sInstance;
    }

    public void open() {
        mDb = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    @Override
    public boolean save(@NonNull Requirement requirement) {
        open();

        ContentValues values = new ContentValues();
        values.put(RequirementDbHelper.COLUMN_ID, requirement.getId());
        values.put(RequirementDbHelper.COLUMN_PAGE_NUMBER, requirement.getPageNumber());
        values.put(RequirementDbHelper.COLUMN_REQ_SPEC_ID, requirement.getReqSpecId());

        long id = mDb.insertWithOnConflict(RequirementDbHelper.TABLE_REQUIREMENTS, null, values,
                SQLiteDatabase.CONFLICT_REPLACE);

        close();

        return (id != -1);
    }

    @Override
    public Requirement delete(@NonNull String id) {
        open();

        String selection = RequirementDbHelper.COLUMN_ID + " LIKE ?";
        String[] selectionArguments = {id};
        mDb.delete(RequirementDbHelper.TABLE_REQUIREMENTS, selection, selectionArguments);

        close();
        return null;
    }

    @Override
    public Requirement get(@NonNull String id) {
        open();

        String selection = RequirementDbHelper.COLUMN_ID + " LIKE ?";
        String[] selectionArgs = {id};

        Cursor c = mDb.query(RequirementDbHelper.TABLE_REQUIREMENTS, ALL_COLUMNS, selection,
                selectionArgs, null, null, null);

        Requirement requirement = null;
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            requirement = createRequirementFromCursor(c);
        }
        if (c != null) {
            c.close();
        }

        close();

        return requirement;
    }

    @NonNull
    @Override
    public List<Requirement> getAll() {
        open();
        List<Requirement> requirements = new ArrayList<>();

        Cursor c = mDb.query(RequirementDbHelper.TABLE_REQUIREMENTS, ALL_COLUMNS,
                null, null, null, null, null);

        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                Requirement item = createRequirementFromCursor(c);
                requirements.add(item);
            }
        }
        if (c != null) {
            c.close();
        }

        close();

        return requirements;
    }

    @NonNull
    private Requirement createRequirementFromCursor(Cursor c) {
        String id = c.getString(c.getColumnIndexOrThrow(RequirementDbHelper.COLUMN_ID));
        String reqSpecId = c.getString(c.getColumnIndexOrThrow(RequirementDbHelper.COLUMN_REQ_SPEC_ID));
        String pageNumber = c.getString(c.getColumnIndexOrThrow(RequirementDbHelper.COLUMN_PAGE_NUMBER));
        return new Requirement(id, reqSpecId, pageNumber);
    }

}
