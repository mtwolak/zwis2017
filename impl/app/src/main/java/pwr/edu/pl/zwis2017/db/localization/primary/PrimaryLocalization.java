package pwr.edu.pl.zwis2017.db.localization.primary;

import android.content.Context;

public class PrimaryLocalization {

    private static final String UNKNOWN_LOCALIZATION = "Nieznana";
    private final PrimaryLocalizationDb db;

    public PrimaryLocalization(Context context)
    {
        this.db = new PrimaryLocalizationDb(context);
    }

    public String get() {
        String localization = this.db.getPrimaryLocalization();
        return localization == null ? UNKNOWN_LOCALIZATION : localization;
    }
}
