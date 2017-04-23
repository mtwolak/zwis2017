package pwr.edu.pl.zwis2017.screen.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import pwr.edu.pl.zwis2017.utils.WifiChecker;


public abstract class ActivityWithWifiEnabledListener implements View.OnClickListener {

    private final Context context;
    private WifiChecker wifiChecker;

    public ActivityWithWifiEnabledListener(Context context, WifiChecker wifiChecker) {
        this.context = context;
        this.wifiChecker = wifiChecker;
    }

    public abstract Intent createIntent();

    @Override
    public void onClick(View v) {

        if (wifiChecker.isWifiEnabled()) {
            context.startActivity(createIntent());
        } else {
            Toast.makeText(context, "Brak połączenia z internetem - nie można wykonać żądanej akcji", Toast.LENGTH_LONG).show();
        }


    }
}
