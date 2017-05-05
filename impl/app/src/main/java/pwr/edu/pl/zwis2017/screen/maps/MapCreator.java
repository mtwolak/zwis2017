package pwr.edu.pl.zwis2017.screen.maps;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class MapCreator {

    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));
    public static final int PLACE_PICKER_REQUEST = 1;

    public Intent createIntent(Activity activity) {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        builder.setLatLngBounds(BOUNDS_MOUNTAIN_VIEW);
        Intent intent;
        try {
            intent = builder.build(activity);
            return intent;
        } catch (Exception e) {
            throw new CannotCreateMapPickerException(e);
        }

    }
}
