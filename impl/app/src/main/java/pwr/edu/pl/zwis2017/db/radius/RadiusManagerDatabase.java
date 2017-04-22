package pwr.edu.pl.zwis2017.db.radius;

import android.content.Context;

import pwr.edu.pl.zwis2017.db.AbstractDatabaseManager;

public class RadiusManagerDatabase extends AbstractDatabaseManager {

    private static final int DEFAULT_RADIUS = 300;

    public RadiusManagerDatabase(Context context)
    {
        super(context);
    }

    public void save(int radius) {
        optionsDb.setRadius(String.valueOf(radius));
    }

    public int getRadius() {
        String radius = optionsDb.getRadius();
        return null == radius ? DEFAULT_RADIUS : Integer.parseInt(radius);
    }
}
