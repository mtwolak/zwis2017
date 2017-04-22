package pwr.edu.pl.zwis2017.screen.region.logic;

import com.google.maps.ElevationApi;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.RadarSearchRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;

import java.util.List;

import pwr.edu.pl.zwis2017.screen.region.logic.google.GoogleApi;
import pwr.edu.pl.zwis2017.screen.region.logic.google.GooglePlaceHolder;
import pwr.edu.pl.zwis2017.screen.region.logic.google.GooglePlaceTypes;

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
    public List<GooglePlaceHolder> getNumberOfPlaces(int radius) {
        try {
            GooglePlacesCreator creator = new GooglePlacesCreator(latLng, radius);
            creator
                    .add(GooglePlaceTypes.AIRPORT)
                    .add(GooglePlaceTypes.COMMUNICATION)
                    .add(GooglePlaceTypes.HEALTH)
                    .add(GooglePlaceTypes.SCHOOL);
            creator.retrievePlaces();
            return creator.getPlaces();
        } catch (Exception e) {
           throw new CannotGetGeoApiException(e);
        }
    }

}
