package pwr.edu.pl.zwis2017.screen.region.intent;

import android.content.Context;
import android.content.Intent;

import pwr.edu.pl.zwis2017.db.radius.RadiusManagerDatabase;
import pwr.edu.pl.zwis2017.screen.region.RegionActivity;
import pwr.edu.pl.zwis2017.screen.region.logic.info.RegionInformation;
import pwr.edu.pl.zwis2017.screen.region.logic.info.RegionInformationHolder;

public class RegionIntentCreator {

    private final Intent intent;
    public static final String REGION_INTENT_NAME = "RegionIntentHolder";

    public RegionIntentCreator(Context context, String primaryLocalization) {

        this.intent = createIntent(context, primaryLocalization);
    }

    private Intent createIntent(Context context, String primaryLocalization) {
        RadiusManagerDatabase radiusManagerDatabase = new RadiusManagerDatabase(context);

        RegionInformationHolder regionHolder = new RegionInformationHolder(getRegionInformation(primaryLocalization));
        regionHolder.getInfo(radiusManagerDatabase.getRadius());

        Intent intent = new Intent(context, RegionActivity.class);
        intent.putExtra(RegionActivity.ACTUAL_LOCALIZATION, primaryLocalization);
        intent.putExtra(REGION_INTENT_NAME, regionHolder);
        return intent;
    }

    private RegionInformation getRegionInformation(String localizationName) {
        RegionInformation regionInformation = new RegionInformation();
        regionInformation.init(localizationName);
        return regionInformation;
    }

    public Intent getIntent() {
        return intent;
    }
}
