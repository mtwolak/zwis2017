package pwr.edu.pl.zwis2017.utils.internet.restriction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import pwr.edu.pl.zwis2017.utils.internet.checker.InternetChecker;


public abstract class ActivityWithInternetEnabledListener implements View.OnClickListener {

    private final Activity activity;
    private InternetChecker internetChecker;

    public ActivityWithInternetEnabledListener(Activity activity, InternetChecker internetChecker) {
        this.activity = activity;
        this.internetChecker = internetChecker;
    }

    public abstract Intent createIntent();
    public abstract void startMethod(Activity activity, Intent intentToStart);

    @Override
    public void onClick(View v) {

        if (internetChecker.isInternetEnabled()) {
            startMethod(activity, createIntent());
        } else {
            Toast.makeText(activity, "Brak połączenia z internetem - nie można wykonać żądanej akcji", Toast.LENGTH_LONG).show();
        }


    }
}
