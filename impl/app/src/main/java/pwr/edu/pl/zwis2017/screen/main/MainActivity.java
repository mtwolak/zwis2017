package pwr.edu.pl.zwis2017.screen.main;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import pwr.edu.pl.zwis2017.R;
import pwr.edu.pl.zwis2017.db.localization.LocalizationManagerDatabase;
import pwr.edu.pl.zwis2017.db.localization.LocalizationWithCityNamer;
import pwr.edu.pl.zwis2017.screen.maps.selected.MapActivity;
import pwr.edu.pl.zwis2017.screen.maps.nearby.MapCreator;
import pwr.edu.pl.zwis2017.screen.options.OptionActivity;
import pwr.edu.pl.zwis2017.screen.region.intent.RegionIntentCreator;
import pwr.edu.pl.zwis2017.utils.internet.checker.InternetChecker;
import pwr.edu.pl.zwis2017.utils.internet.restriction.CasualStartActivityWithInternet;
import pwr.edu.pl.zwis2017.utils.internet.restriction.StartWithResultActivityWithInternet;

public class MainActivity extends AppCompatActivity {

    private LocalizationManagerDatabase localizationDatabase;
    private TextView actualLocalizationLbl;
    private EditText enteredLocalizationEditText;
    public static final String ENTERED_LOCALIZATION = "enteredLocalization";
    private static final String LOCALIZATION_REMEMBERED = "Lokalizacja zapamiętana";
    private static final String LOCALIZATION_CANNOT_BE_REMEMBERED_EXISTS_ALREADY = "Lokalizacja nie może być zapamiętana, ponieważ już istnieje w pamięci";
    private static final LocalizationWithCityNamer LOCALIZATION_WITH_CITY_NAMER = new LocalizationWithCityNamer();
    private InternetChecker internetChecker;


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

    private void setButtonsListeners() {
        findViewById(R.id.nearbyPlacesMapBtn).setOnClickListener(new StartWithResultActivityWithInternet(MainActivity.this, internetChecker, MapCreator.PLACE_PICKER_REQUEST) {
            @Override
            public Intent createIntent() {
                return createIntentForNearbyPlaces();
            }
        });
        findViewById(R.id.selectedPlacesMapBtn).setOnClickListener(new CasualStartActivityWithInternet(MainActivity.this, internetChecker) {
            @Override
            public Intent createIntent() {
                return createIntentForSelectedPlaces();
            }
        });
        findViewById(R.id.optionsBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OptionActivity.class));
            }
        });

        findViewById(R.id.regionInfoBtn).setOnClickListener(new CasualStartActivityWithInternet(MainActivity.this, internetChecker) {
            @Override
            public Intent createIntent() {
                return new RegionIntentCreator(MainActivity.this, getPrimaryLocalization()).getIntent();
            }
        });

    }

    private Intent createIntentForNearbyPlaces() {
        MapCreator mapCreator = new MapCreator();
        mapCreator.setLatLngBounds(mapCreator.getLocationFromAddress(MainActivity.this, getEnteredLocalization().toString()));
        return mapCreator.createIntent(MainActivity.this);
    }

    @NonNull
    private Intent createIntentForSelectedPlaces() {
        Intent intent = new Intent(MainActivity.this, MapActivity.class);
        intent.putExtra(ENTERED_LOCALIZATION, getPrimaryLocalization());
        return intent;
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
