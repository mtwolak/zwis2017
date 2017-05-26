package pwr.edu.pl.zwis2017.screen.region.logic;

import com.google.maps.model.LatLng;

public class DistanceCalculator {

    public double calculate(LatLng pos1, LatLng pos2) {
        return distFrom(pos1.lat, pos1.lng, pos2.lat, pos2.lng);
    }

    //https://stackoverflow.com/questions/837872/calculate-distance-in-meters-when-you-know-longitude-and-latitude-in-java
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000;
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (earthRadius * c);

        return dist;
    }
}
