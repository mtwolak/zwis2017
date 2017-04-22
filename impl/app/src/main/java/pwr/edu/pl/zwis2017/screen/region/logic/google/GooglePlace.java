package pwr.edu.pl.zwis2017.screen.region.logic.google;

import com.google.maps.model.PlaceType;

public class GooglePlace {

    private final String name;
    private final PlaceType placeType;
    private int count;

    public GooglePlace(String name, PlaceType placeType)
    {
        this.name = name;
        this.placeType = placeType;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public int getCount()
    {
        return count;
    }

    public PlaceType getPlaceType()
    {
        return placeType;
    }
}
