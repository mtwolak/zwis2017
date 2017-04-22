package pwr.edu.pl.zwis2017.db.option;

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

    public String getOption(String option)
    {
        String[] projection = {
                OptionsDatabase.OPTION_NAME,
                OptionsDatabase.OPTION_VALUE,
        };

        Cursor cursor = db.getReadableDatabase().query(
                OptionsDatabase.TABLE_NAME,
                projection,
                OptionsDatabase.OPTION_NAME + "=?",
                new String[] {option},
                null,
                null,
                null
        );

        if (cursor.moveToNext()) {
            return cursor.getString(1);
        }

        return null;
    }

    public String getPrimaryLocalization() {
        return getOption(OptionsDatabase.PRIMARY_LOCALIZATION_NAME);
    }

    public void setPrimaryLocalization(String primaryLocalizationToSet) {
        insertIntoDatabase(OptionsDatabase.PRIMARY_LOCALIZATION_NAME, primaryLocalizationToSet);
    }

    public void setRadius(String radius) {
        removeOption(OptionsDatabase.RADIUS);
        insertIntoDatabase(OptionsDatabase.RADIUS, radius);
    }

    private void insertIntoDatabase(String optionName, String optionValue) {
        SQLiteDatabase writableDatabase = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(OptionsDatabase.OPTION_NAME, optionName);
        values.put(OptionsDatabase.OPTION_VALUE, optionValue);
        writableDatabase.insert(OptionsDatabase.TABLE_NAME, null, values);
    }

    public void removePrimaryLocalization() {
        removeOption(OptionsDatabase.PRIMARY_LOCALIZATION_NAME);
    }

    private void removeOption(String optionNameToBeRemoved) {
        db.getWritableDatabase().delete(OptionsDatabase.TABLE_NAME, OptionsDatabase.OPTION_NAME + "='" + optionNameToBeRemoved+ "'", null);
    }

    public String getRadius() {
        return getOption(OptionsDatabase.RADIUS);
    }
}
