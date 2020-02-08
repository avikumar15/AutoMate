package com.example.automate;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.automate.models.RouteInterface;
import com.example.automate.routemodels.DirectionModel;
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
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnPolylineClickListener,
        GoogleMap.OnPolygonClickListener{

    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    FusedLocationProviderClient mFusedLocationClient;
    Float sourceLat = -36f, sourceLng = 81f;
    Float destLat = -36f, destLng = 81f;
    String location;
    Intent intent;
    TextView destinationText;
    TextView sourceText;
    String type = "";
    DirectionModel model;
    List<LatLng> latLngs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        destinationText = findViewById(R.id.et_destination);
        sourceText = findViewById(R.id.et_source);
        model = new DirectionModel();

        destinationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this,LocationSelectionActivity.class);
                intent.putExtra("type","Destination");
                startActivity(intent);
            }
        });
        sourceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this,LocationSelectionActivity.class);
                intent.putExtra("type","Source");
                startActivity(intent);
            }
        });

        if(getIntent()!=null) {
            intent = getIntent();
            float lat, lng;
            lat = intent.getFloatExtra("sourceLat", -36f);
            lng = intent.getFloatExtra("sourceLng", 81f);
            type = intent.getStringExtra("type");
            location = intent.getStringExtra("loc");

            System.out.println(type+"is the type");

            if(getIntent()!=null&&type!=null) {
                if(type.equals("Destination")) {
                    AppUtils.destination = location;
                    AppUtils.destinationLat = lat;
                    AppUtils.destinationLong = lng;
                }
                else {
                    AppUtils.sourceLat = lat;
                    AppUtils.sourceLong = lng;
                    AppUtils.source = location;
                }

                destinationText.setText(AppUtils.destination);
                destinationText.setTextColor(Color.parseColor("#000000"));
                sourceText.setText(AppUtils.source);
                sourceText.setTextColor(Color.parseColor("#000000"));
            }

            /*if(type!=null && type.equals("destination")) {
                AppUtils.destination = location;
                destinationText.setText(location);
                destinationText.setTextColor(Color.parseColor("#000000"));
            }
            else if(type!=null) {
                sourceText.setText(location);
                sourceText.setTextColor(Color.parseColor("#000000"));
            }*/
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

      /*  if(sourceLat==-36 && sourceLng==81f) {
            destinationText.setText("Destination");
            sourceText.setText("Source");
            sourceText.setTextColor(Color.parseColor("#808080"));
            destinationText.setTextColor(Color.parseColor("#808080"));
        }*/

        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }
    void showRoute(DirectionModel model) {
        try {
            for (int i = 0; i < model.getRoutes().get(0).getLegs().get(0).getSteps().size(); i++) {
                latLngs.add(new LatLng(model.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getStartLocation().getLat(), model.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getStartLocation().getLng()));
                latLngs.add(new LatLng(model.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getEndLocation().getLat(), model.getRoutes().get(0).getLegs().get(0).getSteps().get(i).getEndLocation().getLng()));
            }
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        Polyline polyline1 = mGoogleMap.addPolyline(new PolylineOptions()
                .clickable(true)
/*                .add(new LatLng(10.7618779,78.8174691))
                .add(new LatLng(10.7618433,78.8174696))
                .add(new LatLng(10.7618433,78.8174696))
                .add(new LatLng(10.761855,78.8184781)));*/
                .addAll(latLngs));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(120000); // two minute interval
        mLocationRequest.setFastestInterval(120000);
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


        latLngs = new ArrayList<>();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppUtils.ROUTE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RouteInterface routeInterface = retrofit.create(RouteInterface.class);
        if(!(AppUtils.sourceLat ==-36f || AppUtils.sourceLong ==81f || AppUtils.destinationLong ==81f || AppUtils.destinationLat ==81f)) {
            Call<DirectionModel> call = routeInterface.getDirectionDetails(AppUtils.sourceLat + "," + AppUtils.sourceLong, AppUtils.destinationLat + "," + AppUtils.destinationLong, AppUtils.API_KEY);

            call.enqueue(new Callback<DirectionModel>() {
                @Override
                public void onResponse(Call<DirectionModel> call, Response<DirectionModel> response) {
                    if (!response.isSuccessful()) {
                        Log.e("Fail", "Server Error.");
                        return;
                    }

                    try {
                        showRoute(response.body());
                    } catch (Exception e) {
                        Log.e("Fetching Exception", e.getMessage().toString());
                        Toast.makeText(MapActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DirectionModel> call, Throwable t) {
                    System.out.println("Failed to fetch");
                    Log.e("Fail", t.toString());
                }
            });
        }

        googleMap.setOnPolylineClickListener(this);
        googleMap.setOnPolygonClickListener(this);

    }

    LocationManager myLocManager;

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

                //Place current location marker
                LatLng sourceLatLng = new LatLng(AppUtils.sourceLat, AppUtils.sourceLong);
                MarkerOptions sourceMarker = new MarkerOptions();
                sourceMarker.position(sourceLatLng);
                sourceMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_pin));
                sourceMarker.title("Source Position");

                System.out.println("location is "+AppUtils.sourceLat+" "+ AppUtils.sourceLong);

                LatLng destLatLng = new LatLng(AppUtils.destinationLat, AppUtils.destinationLong);
                MarkerOptions destinationMarker = new MarkerOptions();
                destinationMarker.position(destLatLng);
                destinationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.red_pin));
                destinationMarker.title("Destination Position");

                System.out.println("location is "+AppUtils.destinationLat+" "+ AppUtils.destinationLong);

                if(!(AppUtils.sourceLat ==-36f && AppUtils.sourceLong ==81f))
                mCurrLocationMarker = mGoogleMap.addMarker(sourceMarker);

                if(!(AppUtils.destinationLat ==-36f && AppUtils.destinationLong ==81f))
                    mCurrLocationMarker = mGoogleMap.addMarker(destinationMarker);

                //move map camera
                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                Log.e("SL","#"+AppUtils.sourceLat+"$"+AppUtils.sourceLong+"#\n%"+AppUtils.destinationLat+"$"+AppUtils.destinationLong);
                if(!(AppUtils.sourceLat ==-36&& AppUtils.sourceLong ==81f)&&!(AppUtils.destinationLat ==-36&& AppUtils.destinationLong ==81f)) {
                    LatLng midPoint = new LatLng((AppUtils.destinationLat +AppUtils.sourceLat)/2f, (AppUtils.sourceLong +AppUtils.destinationLong)/2f);
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(midPoint, 15));
                }
                if(!(AppUtils.sourceLat ==-36&& AppUtils.sourceLong ==81f)||!(AppUtils.destinationLat ==-36&& AppUtils.destinationLong ==81f)) {
                    LatLng midPoint = new LatLng((AppUtils.destinationLat +AppUtils.sourceLat)/2f, (AppUtils.sourceLong +AppUtils.destinationLong)/2f);
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                } else{
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                }
            }
        }
    };

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

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
                                ActivityCompat.requestPermissions(MapActivity.this,
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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onPolygonClick(Polygon polygon) {

    }

    @Override
    public void onPolylineClick(Polyline polyline) {

    }
}
