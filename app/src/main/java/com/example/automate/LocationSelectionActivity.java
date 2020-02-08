package com.example.automate;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class LocationSelectionActivity extends AppCompatActivity {
    LocationAdapter locationAdapter;
    List<String> location = new Vector<String>();
    List<Float> latitude = new Vector<Float>();
    List<Float> longitude = new Vector<Float>();
    String type;
    RecyclerView loclist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");

        setContentView(R.layout.activity_location_search);
        loclist = findViewById(R.id.location_list);
        loclist.setHasFixedSize(true);
        loclist.setLayoutManager(new LinearLayoutManager(this));
        location = new ArrayList<String>(Arrays.asList("Ojas", "Orion",
                "Opal", "Diamond", "Agate", "Jade", "Emerald", "Pearl", "Garnet",
                "Beryl", "Aquamarine", "Amber", "Zircon", "Shopping Complex", "Guest House",
                "Library", "Sports Center", "Main Gate", "Hospital", "Mega Mess 1",
                "Mega Mess 2", "F Mess", "DoMS"));
        latitude = new ArrayList<Float>(Arrays.asList(10.760575f, 10.759848f,
                10.757727f, 10.763066f, 10.761669f, 10.763229f, 10.763133f, 10.764208f, 10.762964f,
                10.761878f, 10.767950f, 10.767847f, 10.766150f, 10.760996f, 10.753957f,
                10.758213f, 10.758150f, 10.757031f, 10.762377f, 10.765167f,
                10.764594f, 10.764134f, 10.761326f));
        longitude = new ArrayList<Float>(Arrays.asList(78.808391f, 78.811164f,
                78.820660f, 78.814409f, 78.813316f, 78.817823f, 78.816337f, 78.815447f, 78.812003f,
                78.817475f, 78.817672f, 78.813085f, 78.817133f, 78.818521f, 78.819815f,
                78.818068f, 78.816590f, 78.813336f, 78.818472f, 78.815113f,
                78.813110f, 78.815354f, 78.816446f));
        locationAdapter = new LocationAdapter(this, location, latitude, longitude, type);
        loclist.setAdapter(locationAdapter);

        EditText search = (EditText) findViewById(R.id.location_search);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                serchaction(s.toString());
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();
        if (locationAdapter != null)
            locationAdapter.notifyDataSetChanged();
    }
    public void serchaction(String s) {
        List<String> searchLocation = new Vector<String>();
        List<Float> searchLatitude = new Vector<Float>();
        List<Float> searchLongitude = new Vector<Float>();
        if (!s.equals("")) {
            for (int i = 0; i < location.size(); i++) {
                if (location.get(i).toLowerCase().contains(s.toLowerCase())) {
                    searchLocation.add(location.get(i));
                    searchLatitude.add(latitude.get(i));
                    searchLongitude.add(longitude.get(i));
                }
            }

            locationAdapter = new LocationAdapter(this, searchLocation, searchLatitude, searchLongitude,type);
            locationAdapter.notifyDataSetChanged();
            loclist.setAdapter(locationAdapter);
        } else {

            locationAdapter = new LocationAdapter(this, location, latitude, longitude,type);
            locationAdapter.notifyDataSetChanged();
            loclist.setAdapter(locationAdapter);

        }
    }
}
