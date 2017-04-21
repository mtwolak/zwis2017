package pwr.edu.pl.zwis2017.screen.localization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import pwr.edu.pl.zwis2017.R;
import pwr.edu.pl.zwis2017.db.localization.LocalizationManagerDatabase;

public class ActivitySavedLocalization extends AppCompatActivity implements OnLocalizationDeleted {

    private ListView listView1;
    private LocalizationManagerDatabase localizationDatabase;
    private SavedLocalizationAdapter adapter;
    private static final String POSITION_REMOVED = "Usunięto pozycję ";
    private static final String PRIMARY_LOCALIZATION_CHANGED = "Zmieniono domyślną lokalizację na ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_localization);
        localizationDatabase = new LocalizationManagerDatabase(this);

        String[] data = localizationDatabase.getAllLocalizations();
        adapter = new SavedLocalizationAdapter(this, R.layout.listview_item_row, data);
        listView1 = (ListView) findViewById(R.id.listView1);
        View header = getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView1.addHeaderView(header);
        setChangeDefaultLocalizationOnClick();
        listView1.setAdapter(adapter);

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
        Toast.makeText(ActivitySavedLocalization.this, PRIMARY_LOCALIZATION_CHANGED + selectedLocalization, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemDeleted(String positionToDelete) {
        localizationDatabase.remove(positionToDelete);
        adapter.removeLocalization(positionToDelete);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, POSITION_REMOVED + positionToDelete, Toast.LENGTH_LONG).show();
    }
}
