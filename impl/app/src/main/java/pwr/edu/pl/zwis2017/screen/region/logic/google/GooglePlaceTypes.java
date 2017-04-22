package pwr.edu.pl.zwis2017.screen.region.logic.google;


import com.google.maps.model.PlaceType;

public enum GooglePlaceTypes {
    HEALTH(
            new GooglePlaceHolder("Zdrowie",
                    new GooglePlace("Dentysta", PlaceType.DENTIST),
                    new GooglePlace("Lekarz", PlaceType.DOCTOR),
                    new GooglePlace("Szpital", PlaceType.HOSPITAL))),
    AIRPORT(
            new GooglePlaceHolder("Lotniska",
                    new GooglePlace("Lotnisko", PlaceType.AIRPORT)
            )),
    COMMUNICATION(
            new GooglePlaceHolder("Komunikacja",
                    new GooglePlace("Autobus", PlaceType.BUS_STATION)
            )),
    SCHOOL(
            new GooglePlaceHolder("Szkolnictwo",
                    new GooglePlace("Szkoła", PlaceType.SCHOOL),
                    new GooglePlace("Uczelnia", PlaceType.UNIVERSITY)
            ));

    private final GooglePlaceHolder placeHolder;

    GooglePlaceTypes(GooglePlaceHolder placeHolder)
    {
        this.placeHolder = placeHolder;
    }

    public GooglePlaceHolder getPlaceHolder()
    {
        return placeHolder;
    }
}
