package pwr.edu.pl.zwis2017.db.localization;

import android.content.Context;

import pwr.edu.pl.zwis2017.db.localization.primary.PrimaryLocalizationDb;
import pwr.edu.pl.zwis2017.db.localization.saved.LocalizationDb;

public class LocalizationManagerDatabase {

    private final PrimaryLocalizationDb primaryLocalizationDb;
    private final LocalizationDb localizationDb;
    private static final String UNKNOWN_LOCALIZATION = "Nieznana";
    private static final LocalizationWithCityNamer LOCALIZATION_WITH_CITY_NAME = new LocalizationWithCityNamer();


    public LocalizationManagerDatabase(Context context) {
        this.primaryLocalizationDb = new PrimaryLocalizationDb(context);
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
            primaryLocalizationDb.setPrimaryLocalization(allLocalizations[0]);
        }
    }

    private boolean isPrimaryLocalizationEmpty() {
        return primaryLocalizationDb.getPrimaryLocalization() == null;
    }

    private void removeLocalization(String localization) {
        if (localization.equals(primaryLocalizationDb.getPrimaryLocalization())) {
            primaryLocalizationDb.removePrimaryLocalization();
        }
        localizationDb.remove(localization);
    }

    public String getPrimaryLocalization() {
        String localization = this.primaryLocalizationDb.getPrimaryLocalization();
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
            primaryLocalizationDb.removePrimaryLocalization();
        }
        primaryLocalizationDb.setPrimaryLocalization(localization);
    }

}
