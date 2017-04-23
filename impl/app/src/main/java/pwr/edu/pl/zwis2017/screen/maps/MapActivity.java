package pwr.edu.pl.zwis2017.screen.maps;

import android.content.Context;
import android.location.Geocoder;
import android.location.Address;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.model.*;

import java.io.IOException;
import java.util.List;

import pwr.edu.pl.zwis2017.R;
import pwr.edu.pl.zwis2017.db.localization.LocalizationManagerDatabase;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private LocalizationManagerDatabase localizationDatabase;
    private String enteredLocalization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localizationDatabase = new LocalizationManagerDatabase(this);
        Bundle bundle = getIntent().getExtras();
        enteredLocalization = bundle.getString("enteredLocalization");
        setContentView(R.layout.activity_area_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.getUiSettings().setZoomControlsEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(getLocationFromAddress(MapActivity.this,
                enteredLocalization), 16));
        String[] data = localizationDatabase.getAllLocalizations();
        addMarkers(map, data);
    }

    public void addMarkers(GoogleMap map, String[] data)
    {
        for(String location : data) {
            map.addMarker(new MarkerOptions()
                   .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location_black_18dp))
                   .anchor(0.0f, 1.0f)
                   .position(getLocationFromAddress(MapActivity.this, location)));
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
