package pwr.edu.pl.zwis2017.screen.region;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pwr.edu.pl.zwis2017.R;
import pwr.edu.pl.zwis2017.logic.region.RegionInformation;
import pwr.edu.pl.zwis2017.logic.region.RegionInformationable;

public class RegionActivity extends AppCompatActivity {

    public static final String ACTUAL_LOCALIZATION = "actualLocalization";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        RegionInformationable regionInformation = getRegionInformation(getActualLocalization());
        double elevation = regionInformation.getElevation();

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
