package pwr.edu.pl.zwis2017.screen.region;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

import pwr.edu.pl.zwis2017.R;
import pwr.edu.pl.zwis2017.screen.region.intent.RegionIntentCreator;
import pwr.edu.pl.zwis2017.screen.region.logic.info.RegionInformationHolder;

public class RegionActivity extends AppCompatActivity {

    public static final String ACTUAL_LOCALIZATION = "actualLocalization";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        RegionInformationHolder holder = (RegionInformationHolder) getIntent().getSerializableExtra(RegionIntentCreator.REGION_INTENT_NAME);

        ExpandableListViewAdapter adapter = new ExpandableListViewAdapter(this, holder.getPlaces());
        ExpandableListView view = (ExpandableListView) findViewById(R.id.regionExpandableListView);
        view.setAdapter(adapter);
    }
}
