package pwr.edu.pl.zwis2017.db.localization.primary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import pwr.edu.pl.zwis2017.db.Database;


public class OptionsDb {
    private final Database db;

    public OptionsDb(Context context) {
        this.db = new Database(context);
    }

    public String getPrimaryLocalization() {
        String[] projection = {
                OptionsDatabase.OPTION_NAME,
                OptionsDatabase.OPTION_VALUE,
        };

        Cursor cursor = db.getReadableDatabase().query(
                OptionsDatabase.TABLE_NAME,
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
        values.put(OptionsDatabase.OPTION_NAME, OptionsDatabase.PRIMARY_LOCALIZATION_NAME);
        values.put(OptionsDatabase.OPTION_VALUE, primaryLocalizationToSet);
        writableDatabase.insert(OptionsDatabase.TABLE_NAME, null, values);
    }

    public void removePrimaryLocalization() {
        db.getWritableDatabase().delete(OptionsDatabase.TABLE_NAME, OptionsDatabase.OPTION_NAME + "='" + OptionsDatabase.PRIMARY_LOCALIZATION_NAME+"'", null);
    }
}
