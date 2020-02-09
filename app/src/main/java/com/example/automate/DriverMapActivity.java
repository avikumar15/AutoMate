package com.example.automate;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.automate.models.AutoClass;
import com.example.automate.models.DriverClass;
import com.example.automate.models.DriverHistoryClass;
import com.example.automate.models.RiderClass;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.automate.MapActivity.MY_PERMISSIONS_REQUEST_LOCATION;

public class DriverMapActivity extends FragmentActivity implements OnMapReadyCallback, Callback<AutoClass> {

    private GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    Marker mCurrLocationMarker;
    FusedLocationProviderClient mFusedLocationClient;
    View mapView;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    AutoInterface autoInterface;
    String driverUserId;
    int id;
    RecyclerView passList;
    PassangerAdapter passangerAdapter;
    List<String> passId = new Vector<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_map);
        passList = findViewById(R.id.passengerList);
        passList.setHasFixedSize(true);
        passList.setLayoutManager(new LinearLayoutManager(this));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        driverUserId = getIntent().getStringExtra("mode");
        id=Integer.parseInt(driverUserId);

        System.out.println("id is "+id);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Toast.makeText(getApplicationContext(),"%%",Toast.LENGTH_SHORT).show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppUtils.AUTO_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        autoInterface = retrofit.create(AutoInterface.class);

        Retrofit retrofitRide = new Retrofit.Builder()
                .baseUrl(AppUtils.AUTO_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RiderInterface riderInterface = retrofitRide.create(RiderInterface.class);

        Call<List<RiderClass>> call = riderInterface.getRiderClass();

        call.enqueue(new Callback<List<RiderClass>>() {
            @Override
            public void onResponse(Call<List<RiderClass>> call, Response<List<RiderClass>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Fail", "Server Error.");
                    return;
                }try {
                    List<RiderClass> riderClasses = response.body();
                    int f = 0;
                    for (RiderClass riderClass : riderClasses) {
                        if (riderClass.getDriverId() == 1) {
                            f=1;

                            passId= Arrays.asList(riderClass.getPassengerId().split(", "));


                            Log.e("Success", "\n" + passId);

                            Toast.makeText(getApplicationContext(), "\n" + riderClass.getPassengerId(), Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    Log.e("Fetching Exception", e.getMessage().toString());
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RiderClass>> call, Throwable t) {

            }
        });

        passangerAdapter = new PassangerAdapter(getApplicationContext(),passId);
        passList.setAdapter(passangerAdapter);
        mapFrag = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapDriver);
        mapView = mapFrag.getView();
        mapFrag.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000); // two minute interval
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            mGoogleMap.setMyLocationEnabled(true);
        }

        // Add a marker in Sydney and move the camera
/*        LatLng sydney = new LatLng(-34, 151);
        mGoogleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission Needed")
                        .setMessage("This app needs the Location permission, please accept to use location functionality")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(DriverMapActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            List<Location> locationList = locationResult.getLocations();
            if (locationList.size() > 0) {
                //The last location in the list is the newest
                Location location = locationList.get(locationList.size() - 1);

                mLastLocation = location;
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }

                //move map camera
                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
//todo
                try {
                    JSONObject paramObject = new JSONObject();

                    paramObject.put("autoId", id);
                    paramObject.put("autoLatitude", (float)location.getLatitude());
                    paramObject.put("autoLongitude", (float)location.getLongitude());
                    paramObject.put("lastUpdatedAt","");

                    Call<AutoClass> auto = autoInterface.setLocation(id,paramObject.toString());
                    auto.enqueue(DriverMapActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void onResponse(Call<AutoClass> call, Response<AutoClass> response) {

    }

    @Override
    public void onFailure(Call<AutoClass> call, Throwable t) {

    }
}
