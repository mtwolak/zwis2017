package pwr.edu.pl.zwis2017.screen.region.intent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import pwr.edu.pl.zwis2017.db.radius.RadiusManagerDatabase;
import pwr.edu.pl.zwis2017.screen.main.MainActivity;
import pwr.edu.pl.zwis2017.screen.region.RegionActivity;
import pwr.edu.pl.zwis2017.screen.region.logic.info.RegionInformation;
import pwr.edu.pl.zwis2017.screen.region.logic.info.RegionInformationHolder;

public class RegionIntentCreator extends AsyncTask<Void, Void, Void> {

    private final String primaryLocalization;
    private final MainActivity activity;
    private Intent intent;
    public static final String REGION_INTENT_NAME = "RegionIntentHolder";
    private final ProgressDialog dialog;

    public RegionIntentCreator(MainActivity activity, String primaryLocalization) {
        this.activity = activity;
        this.primaryLocalization = primaryLocalization;
        this.dialog = new ProgressDialog(activity);
        execute();
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

    @Override
    protected Void doInBackground(Void... params) {
        this.intent = createIntent(activity, primaryLocalization);
        return null;
    }

    @Override
    protected void onPreExecute() {
        dialog.setMessage("Wczytywanie danych - proszę czekać ...");
        dialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        activity.onRegionIntentCreated(getIntent());
    }
}
