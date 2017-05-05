package pwr.edu.pl.zwis2017.utils.internet.checker;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class InternetChecker {

    private final Context context;

    public InternetChecker(Activity activity) {
        this.context = activity.getApplicationContext();
    }

    public boolean isInternetEnabled() {
        ConnectivityManager man = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo wifi = man.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = man.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return wifi.isConnected() || mobile.isConnected();
    }

}
