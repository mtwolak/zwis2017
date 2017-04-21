package pwr.edu.pl.zwis2017.db.localization.primary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import pwr.edu.pl.zwis2017.db.Database;


public class PrimaryLocalizationDb {
    private final Database db;

    public PrimaryLocalizationDb(Context context) {
        this.db = new Database(context);
    }

    public String getPrimaryLocalization() {
        String[] projection = {
                PrimaryLocalizationDatabase._ID,
                PrimaryLocalizationDatabase.PRIMARY_LOCALIZATION_NAME,
        };

        Cursor cursor = db.getReadableDatabase().query(
                PrimaryLocalizationDatabase.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToNext()) {
            return cursor.getString(1);
        }

        return null;
    }

    public void setPrimaryLocalization(String primaryLocalizationToSet) {
        SQLiteDatabase writableDatabase = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PrimaryLocalizationDatabase.PRIMARY_LOCALIZATION_NAME, primaryLocalizationToSet);
        writableDatabase.insert(PrimaryLocalizationDatabase.TABLE_NAME, null, values);
    }

    public void removePrimaryLocalization() {
        db.getWritableDatabase().delete(PrimaryLocalizationDatabase.TABLE_NAME, null, null);
    }
}
