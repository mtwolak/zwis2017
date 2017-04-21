package pwr.edu.pl.zwis2017.logic.region.google;

import com.google.maps.GeoApiContext;

public enum GoogleApi {
    GEOCODING("AIzaSyBPk_TevDnbmtzHmekv0ydHB_8E7U7lluI"),
    ELEVATION("AIzaSyBtkXafXf8vk60nCWCJyzZpt2iQYr-4XJ8"),
    PLACES("AIzaSyCKFRhEqIxbcvMYBDbtu0JtR5Y8vVT-u00");

    private final String apiKey;

    GoogleApi(String apiKey)
    {
        this.apiKey = apiKey;
    }

    public GeoApiContext getContext()
    {
        return new GeoApiContext().setApiKey(apiKey);
    }
}
