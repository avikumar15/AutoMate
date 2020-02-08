package com.example.automate;

import android.os.Bundle;

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
    RecyclerView loclist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);
        loclist = findViewById(R.id.location_list);
        loclist.setHasFixedSize(true);
        loclist.setLayoutManager(new LinearLayoutManager(this));
        location = new ArrayList<String>(Arrays.asList("Ojas", "Orion",
                "Opal", "Diamond", "Agate", "Jade", "Emerald", "Pearl", "Garnet",
                "Beryl", "Aquamarine", "Amber", "Zircon", "Shopping Complex", "Guest House",
                "Library", "Sports Center", "Main Gate", "Hospital", "Mega Mess 1",
                "Mega Mess 2", "F Mess", "DoMS"));
        latitude = new ArrayList<Float>(Arrays.asList(10.760995f, 10.759899f,
                10.758079f, 10.764033f, 10.762894f, 10.763819f, 10.764254f, 10.764134f, 10.762963f,
                10.761845f, 10.767950f, 10.767847f, 10.766150f, 10.760996f, 10.753957f,
                10.758213f, 10.758150f, 10.757031f, 10.762377f, 10.765167f,
                10.764594f, 10.764134f, 10.761326f));
        longitude = new ArrayList<Float>(Arrays.asList(78.808740f, 78.810872f,
                78.821780f, 78.814994f, 78.813449f, 78.817760f, 78.817576f, 78.815354f, 78.812004f,
                78.817473f, 78.817672f, 78.813085f, 78.817133f, 78.818521f, 78.819815f,
                78.818068f, 78.816590f, 78.813336f, 78.818472f, 78.815113f,
                78.813110f, 78.815354f, 78.816446f));
        locationAdapter = new LocationAdapter(this, location, latitude, longitude);
        loclist.setAdapter(locationAdapter);
    }
}
