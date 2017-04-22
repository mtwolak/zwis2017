package pwr.edu.pl.zwis2017.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pwr.edu.pl.zwis2017.db.option.OptionsDatabase;
import pwr.edu.pl.zwis2017.db.localization.saved.RememberedLocalizationDatabase;

public class Database extends SQLiteOpenHelper {

    private static final String SQL_REMEMBERED_LOCALIZATION_CREATE =
            "CREATE TABLE " + RememberedLocalizationDatabase.TABLE_NAME + " (" +
                    RememberedLocalizationDatabase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RememberedLocalizationDatabase.LOCALIZATION_NAME + " TEXT)";

    private static final String SQL_OPTIONS_CREATE =
            "CREATE TABLE " + OptionsDatabase.TABLE_NAME + " (" +
                    OptionsDatabase.OPTION_NAME + " TEXT PRIMARY KEY, "
            +       OptionsDatabase.OPTION_VALUE + " TEXT)";

    private static final String SQL_DELETE_LOCALIZATION =
            "DROP TABLE IF EXISTS " + RememberedLocalizationDatabase.TABLE_NAME;
    private static final String SQL_DELETE_PRIMARY_LOCALIZATION =
            "DROP TABLE IF EXISTS " + OptionsDatabase.PRIMARY_LOCALIZATION_NAME;


    private static final String DATABASE_NAME = "ZWIS_DB2017";
    private static final int DATABASE_VERSION = 3;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_REMEMBERED_LOCALIZATION_CREATE);
        db.execSQL(SQL_OPTIONS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_LOCALIZATION);
        db.execSQL(SQL_DELETE_PRIMARY_LOCALIZATION);
        onCreate(db);
    }
}
