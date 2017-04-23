package pwr.edu.pl.zwis2017.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class WifiChecker {

    private final Context context;

    public WifiChecker(Activity activity) {
        this.context = activity.getApplicationContext();
    }

    public boolean isWifiEnabled() {
        ConnectivityManager man = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo wifi = man.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return wifi.isConnected();
    }
    
}
