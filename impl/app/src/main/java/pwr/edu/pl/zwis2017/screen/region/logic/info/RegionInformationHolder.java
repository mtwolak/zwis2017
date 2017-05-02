package pwr.edu.pl.zwis2017.screen.region.logic.info;


import java.io.Serializable;
import java.util.List;

import pwr.edu.pl.zwis2017.screen.region.logic.google.GooglePlaceHolder;

public class RegionInformationHolder implements Serializable {

    private transient final RegionInformationable regionInformationable;
    double elevation;
    private List<GooglePlaceHolder> numberOfPlaces;


    public RegionInformationHolder(RegionInformationable regionInformationable) {
        this.regionInformationable = regionInformationable;
    }

    public RegionInformationHolder getInfo(int radius) {
        this.elevation = regionInformationable.getElevation();
        this.numberOfPlaces = regionInformationable.getNumberOfPlaces(radius);
        return this;
    }

    public List<GooglePlaceHolder> getPlaces() {
        return numberOfPlaces;
    }
}
