package pwr.edu.pl.zwis2017.screen.main;

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

import pwr.edu.pl.zwis2017.R;
import pwr.edu.pl.zwis2017.db.localization.LocalizationManagerDatabase;
import pwr.edu.pl.zwis2017.db.localization.LocalizationWithCityNamer;
import pwr.edu.pl.zwis2017.screen.options.OptionActivity;
import pwr.edu.pl.zwis2017.screen.maps.MapActivity;
import pwr.edu.pl.zwis2017.screen.region.RegionActivity;
import pwr.edu.pl.zwis2017.utils.WifiChecker;

public class MainActivity extends AppCompatActivity {

    private LocalizationManagerDatabase localizationDatabase;
    private TextView actualLocalizationLbl;
    private EditText enteredLocalizationEditText;
    private static final String LOCALIZATION_REMEMBERED = "Lokalizacja zapamiętana";
    private static final String LOCALIZATION_CANNOT_BE_REMEMBERED_EXISTS_ALREADY = "Lokalizacja nie może być zapamiętana, ponieważ już istnieje w pamięci";
    private static final LocalizationWithCityNamer LOCALIZATION_WITH_CITY_NAMER = new LocalizationWithCityNamer();
    private WifiChecker wifiChecker;

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
        wifiChecker = new WifiChecker(this);
    }

    private void setButtonsListeners() {
        findViewById(R.id.btnMap).setOnClickListener(new ActivityWithWifiEnabledListener(MainActivity.this, wifiChecker) {
            @Override
            public Intent createIntent() {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
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

        findViewById(R.id.regionInfoBtn).setOnClickListener(new ActivityWithWifiEnabledListener(MainActivity.this, wifiChecker) {
            @Override
            public Intent createIntent() {
                Intent intent = new Intent(MainActivity.this, RegionActivity.class);
                intent.putExtra(RegionActivity.ACTUAL_LOCALIZATION, getPrimaryLocalization());
                return intent;
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
