package pwr.edu.pl.zwis2017.db.localization;

import android.content.Context;

import pwr.edu.pl.zwis2017.db.AbstractDatabaseManager;
import pwr.edu.pl.zwis2017.db.localization.saved.LocalizationDb;

public class LocalizationManagerDatabase extends AbstractDatabaseManager {

    private final LocalizationDb localizationDb;
    private static final String UNKNOWN_LOCALIZATION = "Nieznana";


    public LocalizationManagerDatabase(Context context) {
        super(context);
        this.localizationDb = new LocalizationDb(context);
    }

    public String[] getAllLocalizations() {
        return localizationDb.getAllLocalizations();
    }

    public void remove(String localization) {
        removeLocalization(localization);
        setRandomPrimaryLocalizationFromAllLocalizations();
    }

    private void setRandomPrimaryLocalizationFromAllLocalizations() {
        String[] allLocalizations = getAllLocalizations();
        if (allLocalizations.length != 0 && isPrimaryLocalizationEmpty()) {
            optionsDb.setPrimaryLocalization(allLocalizations[0]);
        }
    }

    private boolean isPrimaryLocalizationEmpty() {
        return optionsDb.getPrimaryLocalization() == null;
    }

    private void removeLocalization(String localization) {
        if (localization.equals(optionsDb.getPrimaryLocalization())) {
            optionsDb.removePrimaryLocalization();
        }
        localizationDb.remove(localization);
    }

    public String getPrimaryLocalization() {
        String localization = this.optionsDb.getPrimaryLocalization();
        return localization == null ? UNKNOWN_LOCALIZATION : localization;
    }

    public boolean rememberLocalization(String localization) {
        if (!localizationDb.doesLocalizationExist(localization)) {
            localizationDb.saveLocalization(localization);
            setPrimaryLocalization(localization);
            return true;
        }
        return false;
    }


    public void setPrimaryLocalization(String localization) {
        if (!isPrimaryLocalizationEmpty()) {
            optionsDb.removePrimaryLocalization();
        }
        optionsDb.setPrimaryLocalization(localization);
    }

}
