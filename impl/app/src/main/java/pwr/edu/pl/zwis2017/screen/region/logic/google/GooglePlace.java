package pwr.edu.pl.zwis2017.screen.region.logic.google;

import com.google.maps.model.PlaceType;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;

import pwr.edu.pl.zwis2017.screen.region.logic.MinimumDistanceBetweenTwoObjectsEvaluator;

public class GooglePlace implements Serializable {

    private static final String EMPTY_STRING = "";
    private final String name;
    private final PlaceType placeType;
    private int count;
    private double minimumDistance;

    public GooglePlace(String name, PlaceType placeType) {
        this.name = name;
        this.placeType = placeType;
    }

    public void setMinimumDistance(double minimumDistance)
    {
        this.minimumDistance = minimumDistance;
    }

    public double getMinimumDistance()
    {
        return minimumDistance;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public PlaceType getPlaceType() {
        return placeType;
    }

    @Override
    public String toString() {
        return name + getFormattedMinimumDistance() + ": " + getCount();
    }

    private String getFormattedMinimumDistance() {
        double minimumDistance = getMinimumDistance();
        return minimumDistance == MinimumDistanceBetweenTwoObjectsEvaluator.NO_DISTANCE ? EMPTY_STRING : formatNotEmptyMinimumDistance(minimumDistance);
    }

    private String formatNotEmptyMinimumDistance(double minimumDistance) {
        return "[" + (int) minimumDistance + "m]";
    }
}
