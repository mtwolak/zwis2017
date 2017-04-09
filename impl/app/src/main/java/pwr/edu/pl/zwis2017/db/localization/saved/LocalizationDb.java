package pwr.edu.pl.zwis2017.db.localization.saved;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import pwr.edu.pl.zwis2017.db.Database;

public class LocalizationDb {

    private final Database db;

    public LocalizationDb(Context context) {
        this.db = new Database(context);
    }

    public void saveLocalization(String localizationToSave) {
        SQLiteDatabase writableDatabase = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RememberedLocalizationDatabase.LOCALIZATION_NAME, localizationToSave);
        writableDatabase.insert(RememberedLocalizationDatabase.TABLE_NAME, null, values);
    }

    private Cursor getSavedLocalizations() {
        return db.getReadableDatabase().query(
                RememberedLocalizationDatabase.TABLE_NAME,
                getProjectionForSavedLocalization(),
                null,
                null,
                null,
                null,
                null
        );
    }

    private String[] getProjectionForSavedLocalization() {
        return new String[]{
                RememberedLocalizationDatabase._ID,
                RememberedLocalizationDatabase.LOCALIZATION_NAME,
        };
    }

    public String[] getAllLocalizations() {
        Cursor cursor = getSavedLocalizations();
        String result[] = new String[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            result[i] = cursor.getString(1);
        }
        return result;
    }

    public void remove(String positionToRemove) {
        String selection = RememberedLocalizationDatabase.LOCALIZATION_NAME + " = ?";
        String[] selectionArgs = {positionToRemove};
        db.getWritableDatabase().delete(RememberedLocalizationDatabase.TABLE_NAME, selection, selectionArgs);
    }

    public boolean doesLocalizationExist(String localization) {
        Cursor cursor = db.getReadableDatabase().query(
                RememberedLocalizationDatabase.TABLE_NAME,
                getProjectionForSavedLocalization(),
                RememberedLocalizationDatabase.LOCALIZATION_NAME + " = ?",
                new String[] {localization},
                null,
                null,
                null
        );

        return cursor.getCount() > 0;
    }
}
