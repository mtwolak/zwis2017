package pwr.edu.pl.zwis2017.localization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import pwr.edu.pl.zwis2017.R;

public class ActivitySavedLocalization extends AppCompatActivity {

    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_localization);

        LocalizationWithSelection[] data = new LocalizationWithSelection[]
                {
                        new LocalizationWithSelection("a", "b"),
                        new LocalizationWithSelection("aaaa", "bbb")
                };

        SavedLocalizationAdapter adapter = new SavedLocalizationAdapter(this, R.layout.listview_item_row, data);
        listView1 = (ListView) findViewById(R.id.listView1);
        View header = getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView1.addHeaderView(header);
        listView1.setAdapter(adapter);

    }
}
