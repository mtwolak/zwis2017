package pwr.edu.pl.zwis2017.screen.region;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import pwr.edu.pl.zwis2017.R;
import pwr.edu.pl.zwis2017.screen.region.logic.google.GooglePlace;
import pwr.edu.pl.zwis2017.screen.region.logic.google.GooglePlaceHolder;


public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private final LayoutInflater inflater;
    private final List<GooglePlaceHolder> itemList;
    private final int layoutResourceId;

    private static final class ViewHolder {
        TextView textLabel;
    }

    public ExpandableListViewAdapter(Context context, List<GooglePlaceHolder> itemList) {
        this.inflater = LayoutInflater.from(context);
        this.itemList = itemList;
        this.layoutResourceId = R.layout.listview_item_region_info;
    }

    @Override
    public GooglePlace getChild(int groupPosition, int childPosition) {
        return itemList.get(groupPosition).getGooglePlaces()[childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemList.get(groupPosition).getGooglePlaces().length;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
                             final ViewGroup parent) {
        View resultView = convertView;
        ViewHolder holder;

        if (resultView == null) {
            resultView = inflater.inflate(layoutResourceId, null);
            holder = new ViewHolder();
            holder.textLabel = (TextView) resultView.findViewById(R.id.headerListView);
            resultView.setTag(holder);
        } else {
            holder = (ViewHolder) resultView.getTag();
        }

        final GooglePlace item = getChild(groupPosition, childPosition);
        holder.textLabel.setText(item.toString());
        return resultView;
    }

    @Override
    public GooglePlaceHolder getGroup(int groupPosition) {
        return itemList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return itemList.size();
    }

    @Override
    public long getGroupId(final int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View theConvertView, ViewGroup parent) {
        View resultView = theConvertView;
        ViewHolder holder;

        if (resultView == null) {
            resultView = inflater.inflate(layoutResourceId, null);
            holder = new ViewHolder();
            holder.textLabel = (TextView) resultView.findViewById(R.id.headerListView);
            resultView.setTag(holder);
        } else {
            holder = (ViewHolder) resultView.getTag();
        }

        final GooglePlaceHolder item = getGroup(groupPosition);

        holder.textLabel.setText(item.toString());

        return resultView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
