package pwr.edu.pl.zwis2017.logic.region;

public class CannotGetGeoApiException extends RuntimeException {

    public CannotGetGeoApiException(Exception e) {
        super(e);
    }
}
