package pwr.edu.pl.zwis2017.screen.region;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import pwr.edu.pl.zwis2017.R;
import pwr.edu.pl.zwis2017.db.option.OptionsDb;
import pwr.edu.pl.zwis2017.db.radius.RadiusManagerDatabase;
import pwr.edu.pl.zwis2017.screen.region.logic.RegionInformation;
import pwr.edu.pl.zwis2017.screen.region.logic.RegionInformationable;
import pwr.edu.pl.zwis2017.screen.region.logic.google.GooglePlaceHolder;

public class RegionActivity extends AppCompatActivity {

    public static final String ACTUAL_LOCALIZATION = "actualLocalization";
    private RadiusManagerDatabase radiusManagerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        radiusManagerDatabase = new RadiusManagerDatabase(this);

        RegionInformationable regionInformation = getRegionInformation(getActualLocalization());
        double elevation = regionInformation.getElevation();
        List<GooglePlaceHolder> numberOfPlaces = regionInformation.getNumberOfPlaces(radiusManagerDatabase.getRadius());
    }

    private RegionInformation getRegionInformation(String localizationName) {
        RegionInformation regionInformation = new RegionInformation();
        regionInformation.init(localizationName);
        return regionInformation;
    }

    private String getActualLocalization() {
        return getIntent().getExtras().getString(ACTUAL_LOCALIZATION);
    }
}
