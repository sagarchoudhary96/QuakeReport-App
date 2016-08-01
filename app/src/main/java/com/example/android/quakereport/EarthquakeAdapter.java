package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        Date dateObject = new Date(currentEarthquake.getTime());

        //find the textView and set their text
        TextView magnitude_textview = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitude_textview.setText(currentEarthquake.getMagnitude());

        TextView location_textview = (TextView) listItemView.findViewById(R.id.location);
        location_textview.setText(currentEarthquake.getLocation());

        TextView date_textview = (TextView) listItemView.findViewById(R.id.date);

        String formattedDate = formatDate(dateObject);
        date_textview.setText(formattedDate);

        TextView time_textview = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        time_textview.setText(formattedTime);

        return listItemView;
    }

    private String formatDate(Date time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(time);
    }

    private String formatTime (Date time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(time);
    }
}
