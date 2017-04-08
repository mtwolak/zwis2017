package pwr.edu.pl.zwis2017.localization;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pwr.edu.pl.zwis2017.db.Database;
import pwr.edu.pl.zwis2017.db.RememberedLocalizationDatabase;

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

    public String getSavedLocalization() {

        String[] projection = {
                RememberedLocalizationDatabase._ID,
                RememberedLocalizationDatabase.LOCALIZATION_NAME,
        };

        Cursor cursor = db.getReadableDatabase().query(
                RememberedLocalizationDatabase.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            return cursor.getString(1);
        }
        return null;
    }
}
