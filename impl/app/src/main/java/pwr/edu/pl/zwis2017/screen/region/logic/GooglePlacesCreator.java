package pwr.edu.pl.zwis2017.screen.region.logic;


import com.google.maps.PlacesApi;
import com.google.maps.RadarSearchRequest;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

import java.util.ArrayList;
import java.util.List;

import pwr.edu.pl.zwis2017.screen.region.logic.google.GoogleApi;
import pwr.edu.pl.zwis2017.screen.region.logic.google.GooglePlace;
import pwr.edu.pl.zwis2017.screen.region.logic.google.GooglePlaceHolder;
import pwr.edu.pl.zwis2017.screen.region.logic.google.GooglePlaceTypes;

public class GooglePlacesCreator {

    private final LatLng localization;
    private final int radius;
    private List<GooglePlaceTypes> placeTypes;
    private List<GooglePlaceHolder> googlePlaceHolderWithCount;
    private static final MinimumDistanceBetweenTwoObjectsEvaluator DISTANCE_EVALUATOR = new MinimumDistanceBetweenTwoObjectsEvaluator();

    public GooglePlacesCreator(LatLng latLng, int radius) {
        this.localization = latLng;
        this.radius = radius;
        this.placeTypes = new ArrayList<>();
        this.googlePlaceHolderWithCount = new ArrayList<>();
    }

    public GooglePlacesCreator add(GooglePlaceTypes googlePlaceTypes) {
        this.placeTypes.add(googlePlaceTypes);
        return this;
    }

    public void retrievePlaces() {
        for (GooglePlaceTypes googlePlaceHolder : placeTypes) {
            addCountForPlaces(googlePlaceHolder);
        }
    }

    private void addCountForPlaces(GooglePlaceTypes googlePlaceHolder) {
        GooglePlaceHolder placeHolder = googlePlaceHolder.getPlaceHolder();
        for (GooglePlace place : placeHolder.getGooglePlaces()) {
            PlacesSearchResult[] placesFromGoogleWebApi = getRetrievedPlacesFromGoogleWebApi(place.getPlaceType());
            place.setCount(placesFromGoogleWebApi.length);
            place.setMinimumDistance(DISTANCE_EVALUATOR.getMinimumDistance(localization, placesFromGoogleWebApi));
        }
        googlePlaceHolderWithCount.add(placeHolder);
    }

    private PlacesSearchResult[] getRetrievedPlacesFromGoogleWebApi(PlaceType placeType) {
        try {
            RadarSearchRequest radarSearchRequest = PlacesApi.radarSearchQuery(GoogleApi.PLACES.getContext(), localization, radius);
            radarSearchRequest.type(placeType);
            PlacesSearchResponse await = radarSearchRequest.await();
            return await.results;
        } catch (Exception e) {
            throw new CannotGetGeoApiException(e);
        }

    }

    public List<GooglePlaceHolder> getPlaces() {
        return googlePlaceHolderWithCount;
    }
}


