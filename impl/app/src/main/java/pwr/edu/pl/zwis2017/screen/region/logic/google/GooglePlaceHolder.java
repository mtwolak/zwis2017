package pwr.edu.pl.zwis2017.screen.region.logic.google;

import java.io.Serializable;

public class GooglePlaceHolder implements Serializable {
    private GooglePlace[] googlePlaces;
    private final String name;

    public GooglePlaceHolder(String name, GooglePlace... googlePlaces) {
        this.name = name;
        this.googlePlaces = googlePlaces;
    }

    public int getCount() {
        int count = 0;
        for (GooglePlace googlePlace : googlePlaces) {
            count += googlePlace.getCount();
        }
        return count;
    }

    public GooglePlace[] getGooglePlaces() {
        return googlePlaces;
    }

    @Override
    public String toString() {
        return name + " (" + getCount() + ")";
    }
}
