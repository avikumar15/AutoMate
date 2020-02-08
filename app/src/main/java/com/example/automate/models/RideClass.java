package com.example.automate.models;

import java.util.List;

public class RideClass {
    DriverClass driver;
    List<PassengerClass> passengers;
    String startTime;
    String endTime;
    List<String> stops;
    String nextStop;
}