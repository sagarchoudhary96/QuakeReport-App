/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    /** URL for earthquake data from the USGS dataset */
    private static final String REQUEST_URL = " http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    /** Adapter for the list of earthquakes */
    private EarthquakeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        final ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        adapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        //on listItem click Listener
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // gets the current earthquake
                Earthquake currentEarthquake = adapter.getItem(position);

                // get the url
                Uri currentEartkquakeUri = Uri.parse(currentEarthquake.getUrl());

                //start the intent
                Intent launchUrl = new Intent(Intent.ACTION_VIEW, currentEartkquakeUri);
                startActivity(launchUrl);
            }
        });


        //Execute Async Task
        new EarthquakeAsyncTask().execute(REQUEST_URL);

    }


    /**
     *  Async Task to fetch earthquake Date and update the UI
     */
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>>{

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {

            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<Earthquake> Earthquakes = QueryUtils.fetchEarthquakeList(urls[0]);

            return Earthquakes;

        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {

            // clears the data of previous list
            adapter.clear();
            if (earthquakes.isEmpty()){
                return;
            }
            // update the UI with the list of Earthquakes
            adapter.addAll(earthquakes);
        }
    }
}
