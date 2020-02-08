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
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        destinationText = findViewById(R.id.et_destination);
        sourceText = findViewById(R.id.et_source);

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
                destinationMarker.title("Destination Position");

                System.out.println("location is "+AppUtils.destinationLat+" "+ AppUtils.destinationLong);

                if(!(AppUtils.sourceLat ==-36f && AppUtils.sourceLong ==81f))
                mCurrLocationMarker = mGoogleMap.addMarker(sourceMarker);

                if(!(AppUtils.destinationLat ==-36f && AppUtils.destinationLong ==81f))
                    mCurrLocationMarker = mGoogleMap.addMarker(destinationMarker);

                //move map camera
                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

                if(!(sourceLat ==-36&& sourceLng ==81f)) {
                    LatLng midPoint = new LatLng((AppUtils.destinationLat +AppUtils.sourceLat)/2f, (AppUtils.sourceLong +AppUtils.destinationLong)/2f);
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(midPoint, 15));
                }else{
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16));
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
}
