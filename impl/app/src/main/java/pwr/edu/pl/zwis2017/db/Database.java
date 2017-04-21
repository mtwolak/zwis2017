package pwr.edu.pl.zwis2017.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pwr.edu.pl.zwis2017.db.localization.primary.PrimaryLocalizationDatabase;
import pwr.edu.pl.zwis2017.db.localization.saved.RememberedLocalizationDatabase;

public class Database extends SQLiteOpenHelper {

    private static final String SQL_REMEMBERED_LOCALIZATION_CREATE =
            "CREATE TABLE " + RememberedLocalizationDatabase.TABLE_NAME + " (" +
                    RememberedLocalizationDatabase._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    RememberedLocalizationDatabase.LOCALIZATION_NAME + " TEXT)";

    private static final String SQL_PRIMARY_LOCALIZATION_CREATE =
            "CREATE TABLE " + PrimaryLocalizationDatabase.TABLE_NAME + " (" +
                    PrimaryLocalizationDatabase._ID + " INTEGER PRIMARY KEY, " +
                    PrimaryLocalizationDatabase.PRIMARY_LOCALIZATION_NAME + " TEXT, "
            + "FOREIGN KEY(" + PrimaryLocalizationDatabase.PRIMARY_LOCALIZATION_NAME + ") REFERENCES " +
            PrimaryLocalizationDatabase.TABLE_NAME + "(" +RememberedLocalizationDatabase.LOCALIZATION_NAME+ "))";

    private static final String SQL_DELETE_LOCALIZATION =
            "DROP TABLE IF EXISTS " + RememberedLocalizationDatabase.TABLE_NAME;
    private static final String SQL_DELETE_PRIMARY_LOCALIZATION =
            "DROP TABLE IF EXISTS " + PrimaryLocalizationDatabase.TABLE_NAME;


    private static final String DATABASE_NAME = "ZWIS_DB2017";
    private static final int DATABASE_VERSION = 2;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_REMEMBERED_LOCALIZATION_CREATE);
        db.execSQL(SQL_PRIMARY_LOCALIZATION_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_LOCALIZATION);
        db.execSQL(SQL_DELETE_PRIMARY_LOCALIZATION);
        onCreate(db);
    }
}
