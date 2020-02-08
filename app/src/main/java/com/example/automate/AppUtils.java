package com.example.automate;

import com.example.automate.models.DriverClass;
import com.example.automate.models.PassengerClass;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {

    public static String source="Source";
    public static String destination="Destination";
    public static float sourceLat=-36f;
    public static float destinationLat=-36f;
    public static float sourceLong=81f;
    public static float destinationLong=81f;
    public static String ROUTE_BASE_URL="https://maps.googleapis.com/maps/api/directions/";
    public static String AUTO_BASE_URL="https://130f3a9f.ngrok.io/";
    public static String API_KEY="AIzaSyCToMpy2_HkkgIPeadh3mhZgFRF8dbR2T0";
    public static List<DriverClass> drivers = new ArrayList<>();
    public static List<PassengerClass> passengers = new ArrayList<>();
}
