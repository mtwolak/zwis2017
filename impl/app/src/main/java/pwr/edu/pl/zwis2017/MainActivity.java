package pwr.edu.pl.zwis2017;

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

import pwr.edu.pl.zwis2017.db.localization.primary.PrimaryLocalization;
import pwr.edu.pl.zwis2017.localization.ActivitySavedLocalization;
import pwr.edu.pl.zwis2017.db.localization.saved.Localization;
import pwr.edu.pl.zwis2017.maps.MapActivity;

public class MainActivity extends AppCompatActivity {

    private Localization localization;
    private PrimaryLocalization primaryLocalization;
    private TextView actualLocalizationLbl;
    private EditText enteredLocalizationEditText;
    private static final String LOCALIZATION_REMEMBERED = "Lokalizacja zapamiętana";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVariables();
        setButtonsListeners();
        setInitView();
    }

    private void setInitView() {
        actualLocalizationLbl.setText(getSavedLocalization());
        enteredLocalizationEditText.setText(getSavedLocalization());
    }

    private void setVariables() {
        enteredLocalizationEditText = (EditText) findViewById(R.id.actualLocalizationTxt);
        actualLocalizationLbl = (TextView) findViewById(R.id.actualLocalizationLbl);
        localization = new Localization(this);
        primaryLocalization = new PrimaryLocalization(this);
    }

    private void setButtonsListeners() {
        findViewById(R.id.btnMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                intent.putExtra("enteredLocalization", getEnteredLocalization().toString());
                startActivity(intent);
            }
        });
        findViewById(R.id.rememberedLocalizationBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivitySavedLocalization.class);
                startActivity(intent);

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
        localization.remember(getEnteredLocalization());
        Toast.makeText(MainActivity.this, LOCALIZATION_REMEMBERED, Toast.LENGTH_LONG).show();
        actualLocalizationLbl.setText(getEnteredLocalization());
    }

    public String getEnteredLocalization() {
        return primaryLocalization.get();
    }

    public String getSavedLocalization() {
        return localization.getSavedLocalization();
    }
}
