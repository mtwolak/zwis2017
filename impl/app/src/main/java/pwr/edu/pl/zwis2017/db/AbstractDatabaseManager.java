package pwr.edu.pl.zwis2017.db;

import android.content.Context;

import pwr.edu.pl.zwis2017.db.option.OptionsDb;

public abstract class AbstractDatabaseManager {

    protected final OptionsDb optionsDb;

    public AbstractDatabaseManager(Context context)
    {
        this.optionsDb = new OptionsDb(context);
    }
}
