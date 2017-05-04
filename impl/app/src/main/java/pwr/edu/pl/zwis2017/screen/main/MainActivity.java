package pwr.edu.pl.zwis2017.screen.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import pwr.edu.pl.zwis2017.R;
import pwr.edu.pl.zwis2017.db.localization.LocalizationManagerDatabase;
import pwr.edu.pl.zwis2017.db.localization.LocalizationWithCityNamer;
import pwr.edu.pl.zwis2017.screen.maps.PlacePickerActivity;
import pwr.edu.pl.zwis2017.screen.options.OptionActivity;
import pwr.edu.pl.zwis2017.screen.maps.MapActivity;
import pwr.edu.pl.zwis2017.screen.region.intent.RegionIntentCreator;
import pwr.edu.pl.zwis2017.utils.InternetChecker;

public class MainActivity extends AppCompatActivity {

    private LocalizationManagerDatabase localizationDatabase;
    private TextView actualLocalizationLbl;
    private EditText enteredLocalizationEditText;
    private static final String LOCALIZATION_REMEMBERED = "Lokalizacja zapamiętana";
    private static final String LOCALIZATION_CANNOT_BE_REMEMBERED_EXISTS_ALREADY = "Lokalizacja nie może być zapamiętana, ponieważ już istnieje w pamięci";
    private static final LocalizationWithCityNamer LOCALIZATION_WITH_CITY_NAMER = new LocalizationWithCityNamer();
    private InternetChecker internetChecker;
    private int PLACE_PICKER_REQUEST = 1;
    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVariables();
        setButtonsListeners();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {
        actualLocalizationLbl.setText(getPrimaryLocalization());
        enteredLocalizationEditText.setText(getPrimaryLocalization());
    }

    private void setVariables() {
        enteredLocalizationEditText = (EditText) findViewById(R.id.actualLocalizationTxt);
        actualLocalizationLbl = (TextView) findViewById(R.id.actualLocalizationLbl);
        localizationDatabase = new LocalizationManagerDatabase(this);
        internetChecker = new InternetChecker(this);
    }
/*
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {

        if (requestCode == PLACE_PICKER_REQUEST
                && resultCode == Activity.RESULT_OK) {

            final Place place = PlacePicker.getPlace(this, data);
            final CharSequence name = place.getName();
            final CharSequence address = place.getAddress();
            String attributions = (String) place.getAttributions();
            if (attributions == null) {
                attributions = "";
            }

            mName.setText(name);
            mAddress.setText(address);
            mAttributions.setText(Html.fromHtml(attributions));

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
*/
    private void setButtonsListeners() {
        /*findViewById(R.id.btnMap).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    PlacePicker.IntentBuilder intentBuilder =
                            new PlacePicker.IntentBuilder();
                    intentBuilder.setLatLngBounds(BOUNDS_MOUNTAIN_VIEW);
                    Intent intent = intentBuilder.build(MainActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);

                } catch (GooglePlayServicesRepairableException
                        | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });*/
        findViewById(R.id.btnMap).setOnClickListener(new ActivityWithInternetEnabledListener(MainActivity.this, internetChecker) {
            @Override
            public Intent createIntent() {
                Intent intent = new Intent(MainActivity.this, PlacePickerActivity.class);
                intent.putExtra("enteredLocalization", getPrimaryLocalization());
                return intent;
            }
        });

        findViewById(R.id.optionsBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OptionActivity.class));
            }
        });

        findViewById(R.id.regionInfoBtn).setOnClickListener(new ActivityWithInternetEnabledListener(MainActivity.this, internetChecker) {
            @Override
            public Intent createIntent() {
                return new RegionIntentCreator(MainActivity.this, getPrimaryLocalization()).getIntent();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.rememberLocalication:
                rememberLocalization();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void rememberLocalization() {
        String cityToRemember = LOCALIZATION_WITH_CITY_NAMER.addCityName(getEnteredLocalization().trim());
        if (localizationDatabase.rememberLocalization(cityToRemember)) {
            Toast.makeText(MainActivity.this, LOCALIZATION_REMEMBERED, Toast.LENGTH_LONG).show();
            actualLocalizationLbl.setText(cityToRemember);
            enteredLocalizationEditText.setText(cityToRemember);
        } else {
            Toast.makeText(MainActivity.this, LOCALIZATION_CANNOT_BE_REMEMBERED_EXISTS_ALREADY, Toast.LENGTH_LONG).show();
        }

    }

    public String getEnteredLocalization() {
        return enteredLocalizationEditText.getText().toString();
    }

    public String getPrimaryLocalization() {
        return localizationDatabase.getPrimaryLocalization();
    }

}
