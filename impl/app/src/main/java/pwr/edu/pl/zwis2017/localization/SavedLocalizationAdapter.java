package pwr.edu.pl.zwis2017.localization;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import pwr.edu.pl.zwis2017.R;

public class SavedLocalizationAdapter extends ArrayAdapter<String> {

    private Context context;
    private int layoutResourceId;
    private String[] data;

    public SavedLocalizationAdapter(Context context, int layoutResourceId, String[] data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LocalizationHolder localizationHolder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            localizationHolder = new LocalizationHolder();
            localizationHolder.localization = (TextView) row.findViewById(R.id.rememberedLocalizationTxt);
            row.setTag(localizationHolder);
        } else {
            localizationHolder = (LocalizationHolder) row.getTag();
        }

        String localization = data[position];
        localizationHolder.localization.setText(localization);

        return row;
    }

    static class LocalizationHolder {
        TextView localization;
    }

}
