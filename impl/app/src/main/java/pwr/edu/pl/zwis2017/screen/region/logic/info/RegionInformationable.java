package pwr.edu.pl.zwis2017.screen.region.logic.info;

import java.util.List;

import pwr.edu.pl.zwis2017.screen.region.logic.google.GooglePlaceHolder;

public interface RegionInformationable {
    double getElevation();
    List<GooglePlaceHolder> getNumberOfPlaces(int radius);
}
