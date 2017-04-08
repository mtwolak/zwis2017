package pwr.edu.pl.zwis2017.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.model.*;

import pwr.edu.pl.zwis2017.R;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final double WROCLAW_LATITUDE = 51.1078852;
    private static final double WROCLAW_LONGITUDE = 17.0385376;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        String enteredLocalization = b.getString("enteredLocalization");
        setContentView(R.layout.activity_area_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(WROCLAW_LATITUDE, WROCLAW_LONGITUDE), 16));

        // You can customize the marker image using images bundled with
        // your app, or dynamically generated bitmaps.
        map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.church))
                .anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
                .position(new LatLng(WROCLAW_LATITUDE, WROCLAW_LONGITUDE)));
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
