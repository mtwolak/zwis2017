package pwr.edu.pl.zwis2017.logic.region;

import com.google.maps.model.PlaceType;

public enum ZwisPlaceType {
    AIRPORT(PlaceType.AIRPORT),
    COMMUNICATION(PlaceType.BUS_STATION),
    HEALTH(PlaceType.DENTIST, PlaceType.DOCTOR, PlaceType.HOSPITAL),
    FOOD(PlaceType.FOOD),
    SCHOOL(PlaceType.SCHOOL, PlaceType.UNIVERSITY);

    private final PlaceType[] places;

    ZwisPlaceType(PlaceType ... placeType) {
        this.places = placeType;
    }

    public PlaceType[] getPlaceTypes()
    {
        return places;
    }
}
