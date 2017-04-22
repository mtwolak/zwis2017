package pwr.edu.pl.zwis2017.screen.options;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import pwr.edu.pl.zwis2017.R;

public class SavedLocalizationAdapter extends ArrayAdapter<String> {

    private ActivityOptions parentActivity;
    private int layoutResourceId;
    private List<String> data;

    public SavedLocalizationAdapter(ActivityOptions parent, int layoutResourceId, String[] data) {
        super(parent, layoutResourceId, data);
        this.parentActivity = parent;
        this.layoutResourceId = layoutResourceId;
        this.data = new LinkedList(Arrays.asList(data));
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View row = convertView;
        LocalizationHolder localizationHolder;
        if (row == null) {
            LayoutInflater inflater = parentActivity.getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            localizationHolder = new LocalizationHolder();
            localizationHolder.localization = (TextView) row.findViewById(R.id.rememberedLocalizationTxt);
            row.setTag(localizationHolder);
        } else {
            localizationHolder = (LocalizationHolder) row.getTag();
        }

        addDeleteAction(position, row);
        setTextInRow(position, localizationHolder);
        return row;
    }

    private void setTextInRow(int position, LocalizationHolder localizationHolder) {
        String localization = data.get(position);
        localizationHolder.localization.setText(localization);
    }

    private void addDeleteAction(final int position, View row) {
        Button deleteLocalization = (Button) row.findViewById(R.id.deleteLocalizationBtn);
        deleteLocalization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.onItemDeleted(data.get(position));
            }
        });
    }

    public void removeLocalization(String positionToDelete) {
        data.remove(positionToDelete);
    }

    static class LocalizationHolder {
        TextView localization;
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
