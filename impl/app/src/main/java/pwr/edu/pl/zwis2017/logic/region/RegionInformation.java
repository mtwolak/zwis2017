package pwr.edu.pl.zwis2017.logic.region;

import com.google.maps.ElevationApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import pwr.edu.pl.zwis2017.logic.region.google.GoogleApi;

public class RegionInformation implements RegionInformationable {

    private LatLng latLng;

    public void init(String localizationName)
    {
        latLng = getLatLng(localizationName);
    }

    private LatLng getLatLng(String localizationName) {
        try {
            GeocodingResult[] results = GeocodingApi.geocode(GoogleApi.GEOCODING.getContext(), localizationName).await();
            return results[0].geometry.location;
        } catch (Exception e) {
           throw new CannotGetGeoApiException(e);
        }

    }


    @Override
    public double getElevation() {
        try {
            return ElevationApi.getByPoint(GoogleApi.ELEVATION.getContext(), latLng).await().elevation;
        } catch(Exception e)
        {
            throw new CannotGetGeoApiException(e);
        }

    }
}
