package pwr.edu.pl.zwis2017.utils.internet.restriction;


import android.app.Activity;
import android.content.Intent;

import pwr.edu.pl.zwis2017.utils.internet.checker.InternetChecker;

public abstract  class StartWithResultActivityWithInternet extends ActivityWithInternetEnabledListener {

    private final int returnCode;

    public StartWithResultActivityWithInternet(Activity activity, InternetChecker internetChecker, int returnCode) {
        super(activity, internetChecker);
        this.returnCode = returnCode;
    }

    @Override
    public void startMethod(Activity activity, Intent intentToStart) {
        activity.startActivityForResult(intentToStart, returnCode);
    }
}
