package pwr.edu.pl.zwis2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import pwr.edu.pl.zwis2017.maps.MapActivity;

public class MainActivity extends AppCompatActivity {

    private Localization localization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVariables();
        setButtonsListeners();
    }

    private void setVariables() {
        localization = new Localization();
    }

    private void setButtonsListeners() {
        findViewById(R.id.btnMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
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
                localization.remember(getEnteredLocalization());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public CharSequence getEnteredLocalization() {
        return ((TextView) findViewById(R.id.actualLocalizationTxt)).getText();
    }
}
