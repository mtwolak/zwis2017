package pwr.edu.pl.zwis2017.screen.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import pwr.edu.pl.zwis2017.utils.InternetChecker;


public abstract class ActivityWithInternetEnabledListener implements View.OnClickListener {

    private final Context context;
    private InternetChecker internetChecker;

    public ActivityWithInternetEnabledListener(Context context, InternetChecker internetChecker) {
        this.context = context;
        this.internetChecker = internetChecker;
    }

    public abstract Intent createIntent();

    @Override
    public void onClick(View v) {

        if (internetChecker.isInternetEnabled()) {
            context.startActivity(createIntent());
        } else {
            Toast.makeText(context, "Brak połączenia z internetem - nie można wykonać żądanej akcji", Toast.LENGTH_LONG).show();
        }


    }
}
