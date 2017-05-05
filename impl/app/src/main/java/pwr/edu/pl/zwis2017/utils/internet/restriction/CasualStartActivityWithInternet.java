package pwr.edu.pl.zwis2017.utils.internet.restriction;


import android.app.Activity;
import android.content.Intent;

import pwr.edu.pl.zwis2017.utils.internet.checker.InternetChecker;

public abstract class CasualStartActivityWithInternet extends ActivityWithInternetEnabledListener {

    public CasualStartActivityWithInternet(Activity activity, InternetChecker internetChecker) {
        super(activity, internetChecker);
    }

    @Override
    public void startMethod(Activity activity, Intent intentToStart) {
        activity.startActivity(intentToStart);
    }

}
