package pwr.edu.pl.zwis2017.db.localization.saved;

import android.content.Context;

import pwr.edu.pl.zwis2017.db.localization.saved.LocalizationDb;

public class Localization {

    private static final String UNKNOWN_LOCALIZATION = "Nieznana";

    private final LocalizationDb db;

    public Localization(Context context) {
        this.db = new LocalizationDb(context);
    }

    public void remember(CharSequence enteredLocalization) {
        this.db.saveLocalization(enteredLocalization.toString());
    }

    public String getSavedLocalization() {
        String localization = this.db.getSavedLocalization();
        return localization == null ? UNKNOWN_LOCALIZATION : localization;
    }

}
