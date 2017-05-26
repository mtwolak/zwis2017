package pwr.edu.pl.zwis2017.screen.region.logic;

import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;

import java.util.ArrayList;
import java.util.List;

public class MinimumDistanceBetweenTwoObjectsEvaluator {

    public static final double NO_DISTANCE = -1;
    private static final DistanceCalculator DISTANCE_CALCULATOR = new DistanceCalculator();

    public double getMinimumDistance(LatLng startLocalization, PlacesSearchResult[] placesFromGoogleWebApi) {
        return placesFromGoogleWebApi.length == 0 ? NO_DISTANCE : evaluateMinimumDistance(startLocalization, placesFromGoogleWebApi);
    }

    private double evaluateMinimumDistance(LatLng startLocalization, PlacesSearchResult[] placesFromGoogleWebApi) {
        List<Double> evaluatedDistances = getDistances(startLocalization, placesFromGoogleWebApi);
        return getMinimumNumber(evaluatedDistances);
    }

    private List<Double> getDistances(LatLng startLocalization, PlacesSearchResult[] placesFromGoogleWebApi) {
        List<Double> distances = new ArrayList<>(placesFromGoogleWebApi.length);
        for(PlacesSearchResult placesSearchResult : placesFromGoogleWebApi)
        {
            LatLng localizationOfPlaceFromGoogleWebApi = placesSearchResult.geometry.location;
            double difference = DISTANCE_CALCULATOR.calculate(startLocalization, localizationOfPlaceFromGoogleWebApi);
            distances.add(difference);
        }
        return distances;
    }

    private double getMinimumNumber(List<Double> evaluatedDistances) {
        double minimum = evaluatedDistances.get(0);
        for(double distance : evaluatedDistances)
        {
            if(distance < minimum)
            {
                minimum = distance;
            }
        }
        return minimum;
    }
}
