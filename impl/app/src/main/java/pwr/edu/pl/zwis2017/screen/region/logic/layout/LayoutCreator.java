package pwr.edu.pl.zwis2017.screen.region.logic.layout;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class LayoutCreator {


    private final TableLayout tableLayout;
    private final Context context;

    public LayoutCreator(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
    }

    public void add(String ... rows) {
        tableLayout.addView(createTableRow(rows), new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private TableRow createTableRow(String... rows) {
        TableRow tableRow = new TableRow(context);
        for (String text : rows) {
            tableRow.addView(createTextView(text));
        }
        return tableRow;
    }

    private View createTextView(String text) {
        TextView tv = new TextView(context);
        tv.setText(text);
        return tv;
    }
}
