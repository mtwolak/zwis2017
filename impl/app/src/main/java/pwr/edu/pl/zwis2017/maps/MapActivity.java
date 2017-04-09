package pwr.edu.pl.zwis2017.maps;

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

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String enteredLocalization;
    private static final double WROCLAW_LATITUDE = 51.1078852;
    private static final double WROCLAW_LONGITUDE = 17.0385376;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        enteredLocalization = b.getString("enteredLocalization");
        setContentView(R.layout.activity_area_map);
        // Obtain the SupportMapFragment and getPrimaryLocalization notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(getLocationFromAddress(MapActivity.this,
                enteredLocalization), 16));
        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps.
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location_black_18dp))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(getLocationFromAddress(MapActivity.this, enteredLocalization)));
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
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
/*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng wroclaw = new LatLng(WROCLAW_LATITUDE, WROCLAW_LONGITUDE);
        mMap.addMarker(new MarkerOptions().position(wroclaw).title("Marker in Wrocław"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(wroclaw));
    }
 */
}
