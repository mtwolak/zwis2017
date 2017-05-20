package pwr.edu.pl.zwis2017.screen.region.logic.google;


import com.google.maps.model.PlaceType;

public enum GooglePlaceTypes {
    HEALTH(
            new GooglePlaceHolder("Zdrowie",
                    new GooglePlace("Dentysta", PlaceType.DENTIST),
                    new GooglePlace("Lekarz", PlaceType.DOCTOR),
                    new GooglePlace("Szpital", PlaceType.HOSPITAL),
                    new GooglePlace("Siłownia", PlaceType.GYM))),
    COMMUNICATION(
            new GooglePlaceHolder("Komunikacja",
                    new GooglePlace("Lotnisko", PlaceType.AIRPORT),
                    new GooglePlace("Autobus", PlaceType.BUS_STATION)
            )),
    SCHOOL(
            new GooglePlaceHolder("Szkolnictwo",
                    new GooglePlace("Szkoła", PlaceType.SCHOOL),
                    new GooglePlace("Uczelnia", PlaceType.UNIVERSITY)
            )),
    ENTERTAINMENT(
            new GooglePlaceHolder("Rozrywka",
                    new GooglePlace("Zoo", PlaceType.ZOO),
                    new GooglePlace("Park rozrywki", PlaceType.AMUSEMENT_PARK)
            )),
    GASTRONOMY(
            new GooglePlaceHolder("Gastronomia",
                    new GooglePlace("Bar", PlaceType.BAR),
                    new GooglePlace("Kawiarnia", PlaceType.CAFE))),
    OTHER(
            new GooglePlaceHolder("Inne",
                    new GooglePlace("Fryzjer", PlaceType.HAIR_CARE),
                    new GooglePlace("Mechanik", PlaceType.CAR_RENTAL),
                    new GooglePlace("Księgarnie", PlaceType.BOOK_STORE)
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
