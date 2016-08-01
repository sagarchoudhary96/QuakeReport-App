package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 31-07-2016.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        //find the textView and set their text
        TextView magnitude_textview = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitude_textview.setText(currentEarthquake.getMagnitude());

        TextView location_textview = (TextView) listItemView.findViewById(R.id.location);
        location_textview.setText(currentEarthquake.getLocation());

        TextView date_textview = (TextView) listItemView.findViewById(R.id.date);
        date_textview.setText(currentEarthquake.getDate());

        return listItemView;
    }
}
