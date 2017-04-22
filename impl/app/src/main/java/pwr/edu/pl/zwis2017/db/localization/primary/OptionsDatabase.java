package pwr.edu.pl.zwis2017.db.localization.primary;


import android.provider.BaseColumns;

public final class OptionsDatabase implements BaseColumns {

    public static final String TABLE_NAME = "Options";
    public static final String OPTION_NAME = "OptionName";
    public static final String OPTION_VALUE = "OptionValue";

    public static final String PRIMARY_LOCALIZATION_NAME = "PrimaryLocalizationName";
    public static final String RADIUS = "Radius";
}
