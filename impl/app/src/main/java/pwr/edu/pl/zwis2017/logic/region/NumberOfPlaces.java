package pwr.edu.pl.zwis2017.logic.region;

import java.util.EnumMap;
import java.util.Map;

public class NumberOfPlaces {

    private Map<ZwisPlaceType, Integer> numberOfPlaceTypes = new EnumMap<>(ZwisPlaceType.class);

    public void add(ZwisPlaceType zwisPlaceType, int numberOfPlaces) {
        Integer oldNumberOfPlaces = getNumberOfPlaces(zwisPlaceType);
        numberOfPlaceTypes.put(zwisPlaceType, oldNumberOfPlaces+numberOfPlaces);
    }

    public int getNumberOfPlaces(ZwisPlaceType zwisPlaceType)
    {
        return numberOfPlaceTypes.containsKey(zwisPlaceType) ? numberOfPlaceTypes.get(zwisPlaceType) : 0;
    }
}
