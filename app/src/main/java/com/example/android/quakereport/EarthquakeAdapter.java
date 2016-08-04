package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 31-07-2016.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR = "of";

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
        String formatted_magnitude = formatMagnitude(currentEarthquake.getMagnitude());
        magnitude_textview.setText(formatted_magnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude_textview.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        // to get the location of the current earthquake
        String currentLocation = currentEarthquake.getLocation();

        // variables for storing offset location and primary location
        String location_primary;
        String location_offset;

        if (currentLocation.contains(LOCATION_SEPARATOR)) {
            String[] location_parts = currentLocation.split(LOCATION_SEPARATOR);

            location_offset = location_parts[0] +  " of";
            location_primary = location_parts[1];
        }
        else {
            // if there are no km's gives then offset location is set to "Near the"
            location_offset = getContext().getString(R.string.near_the);
            location_primary = currentLocation;
        }

        TextView offsetLocation_textview = (TextView) listItemView.findViewById(R.id.location_offset);
        offsetLocation_textview.setText(location_offset);

        TextView primaryLocation_textview = (TextView) listItemView.findViewById(R.id.location_primary);
        primaryLocation_textview.setText(location_primary);

        TextView date_textview = (TextView) listItemView.findViewById(R.id.date);

        String formattedDate = formatDate(dateObject);
        date_textview.setText(formattedDate);

        TextView time_textview = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        time_textview.setText(formattedTime);

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeResourceId;
        int floorMagnitude = (int)Math.floor(magnitude);
        switch(floorMagnitude) {

            case 0:
            case 1:
                magnitudeResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeResourceId);
    }

    private String formatDate(Date time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(time);
    }

    private String formatTime (Date time) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(time);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitude);

    }
}
