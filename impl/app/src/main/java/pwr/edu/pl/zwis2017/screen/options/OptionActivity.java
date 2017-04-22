package pwr.edu.pl.zwis2017.screen.options;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import pwr.edu.pl.zwis2017.R;
import pwr.edu.pl.zwis2017.db.localization.LocalizationManagerDatabase;
import pwr.edu.pl.zwis2017.db.radius.RadiusManagerDatabase;
import pwr.edu.pl.zwis2017.utils.InputFilterMinMax;

public class OptionActivity extends AppCompatActivity implements OnLocalizationDeleted {

    private EditText radiusEditText;
    private ListView listView1;
    private LocalizationManagerDatabase localizationDatabase;
    private SavedLocalizationAdapter adapter;
    private static final String POSITION_REMOVED = "Usunięto pozycję ";
    private static final String PRIMARY_LOCALIZATION_CHANGED = "Zmieniono domyślną lokalizację na ";
    private RadiusManagerDatabase radiusManagerDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        prepareDatabase();
        prepareListViewWithLocalizations();
        prepareTextViewRadius();
    }

    private void prepareDatabase() {
        localizationDatabase = new LocalizationManagerDatabase(this);
        radiusManagerDatabase = new RadiusManagerDatabase(this);
    }

    private void prepareListViewWithLocalizations() {
        String[] data = localizationDatabase.getAllLocalizations();
        adapter = new SavedLocalizationAdapter(this, R.layout.listview_item_row, data);
        listView1 = (ListView) findViewById(R.id.listView1);
        View header = getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView1.addHeaderView(header);
        setChangeDefaultLocalizationOnClick();
        listView1.setAdapter(adapter);
    }

    private void prepareTextViewRadius() {
        radiusEditText = (EditText) findViewById(R.id.enteredRadius);
        int radius = radiusManagerDatabase.getRadius();
        radiusEditText.setText(String.valueOf(radius));
        radiusEditText.setFilters(new InputFilter[]{new InputFilterMinMax("1", "10000")});
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveRadius(getRadius());
    }

    private void saveRadius(int radius) {
        if (radius > 0 && radius < 10000) {
            radiusManagerDatabase.save(radius);
        }
    }

    private void setChangeDefaultLocalizationOnClick() {
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedLocalization = localizationDatabase.getAllLocalizations()[position - 1];
                changeDefaultLocalization(selectedLocalization);
            }
        });
    }

    private void changeDefaultLocalization(String selectedLocalization) {
        localizationDatabase.setPrimaryLocalization(selectedLocalization);
        Toast.makeText(OptionActivity.this, PRIMARY_LOCALIZATION_CHANGED + selectedLocalization, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemDeleted(String positionToDelete) {
        localizationDatabase.remove(positionToDelete);
        adapter.removeLocalization(positionToDelete);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, POSITION_REMOVED + positionToDelete, Toast.LENGTH_LONG).show();
    }

    public int getRadius() {
        try {
            return Integer.parseInt(radiusEditText.getText().toString().trim());
        } catch (NumberFormatException e) {
            return -1;
        }

    }
}
