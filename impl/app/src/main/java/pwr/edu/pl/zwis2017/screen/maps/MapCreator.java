package pwr.edu.pl.zwis2017.screen.maps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.io.IOException;
import java.util.List;

public class MapCreator {

    private LatLngBounds BOUNDS_MOUNTAIN_VIEW;
    public static final int PLACE_PICKER_REQUEST = 1;

    public void setLatLngBounds(LatLng latLng)
    {
        this.BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
                new LatLng(latLng.latitude-0.0025f,latLng.longitude-0.0025f),
                new LatLng(latLng.latitude+0.0025f,latLng.longitude+0.0025f)
        );
    }

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

    public LatLng getLocationFromAddress(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;
        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return p1;
    }
}
