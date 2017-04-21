package pwr.edu.pl.zwis2017.logic.region;

import com.google.maps.ElevationApi;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.RadarSearchRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;

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

    @Override
    public NumberOfPlaces getNumberOfPlaces(int radius) {
        try {
            NumberOfPlaces numberOfPlaces = new NumberOfPlaces();
            addPlaceTypes(numberOfPlaces, radius);
            return numberOfPlaces;
        } catch (Exception e) {
           throw new CannotGetGeoApiException(e);
        }
    }

    private void addPlaceTypes(NumberOfPlaces numberOfPlaces, int radius) throws com.google.maps.errors.ApiException, InterruptedException, java.io.IOException {
        for(ZwisPlaceType zwisPlaceType : ZwisPlaceType.values())
        {
            PlaceType[] googlePlaceTypes = zwisPlaceType.getPlaceTypes();
            addGoogleType(numberOfPlaces, zwisPlaceType, googlePlaceTypes, radius);
        }
    }

    private void addGoogleType(NumberOfPlaces numberOfPlaces, ZwisPlaceType zwisPlaceType, PlaceType[] googlePlaceTypes, int radius) throws com.google.maps.errors.ApiException, InterruptedException, java.io.IOException {
        for(PlaceType pt : googlePlaceTypes)
        {
            RadarSearchRequest radarSearchRequest = PlacesApi.radarSearchQuery(GoogleApi.PLACES.getContext(), latLng, radius);
            radarSearchRequest.type(pt);
            PlacesSearchResponse await = radarSearchRequest.await();
            numberOfPlaces.add(zwisPlaceType, await.results.length);
        }
    }
}
