package com.example.johnywalker.adventure_go.frontEnd;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;
import com.example.johnywalker.adventure_go.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity extends FragmentActivity
        implements
            OnMapReadyCallback,
            LocationListener,
            GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener
{
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public static final String TAG="MapsActivity";
    public static final String MY_GEOFENCE_ID = "MyGeofenceID";

    GoogleApiClient googleApiClient = null;

    private static final int waitTime = 2000;
    private long mBackPressed;
    private Toast mExitToast;

    private GoogleMap mMap;
    private SupportMapFragment mapFrag;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        //Checking if it need diffrent permission access
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();
        }
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);
        GeoFenceMonitoring();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 100, this);
        GeoFenceMonitoring();

    }

    @Override
    public void onBackPressed()
    {
        if(mBackPressed + waitTime > System.currentTimeMillis())
        {
            mExitToast.cancel();
            finish();
            super.onBackPressed();
            return;
        }
        else
        {
            mExitToast = Toast.makeText(getBaseContext(), "Tap again to exit", Toast.LENGTH_SHORT);
            mExitToast.show();
        }
        mBackPressed = System.currentTimeMillis();
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        //checking again about sdk
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                mMap.setMyLocationEnabled(true);
            }
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION))
            {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                //  TODO: Prompt with explanation!

                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);

            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else
        {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_LOCATION:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    // permission was granted, yay!
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        mMap.setMyLocationEnabled(true);
                    }
                } else
                {
                    // permission denied, shit...
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }


    @Override
    public void onLocationChanged(Location location)
    {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 17);
        mMap.animateCamera(cameraUpdate);
        Log.d(TAG, "Location update lat/lot: " + location.getLatitude() + " " + location.getLongitude());
    }

    @Override
    public void onResume()
    {
        Log.d(TAG, "onResume called");
        super.onResume();
        int response = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if (response != ConnectionResult.SUCCESS)
        {
            Log.d(TAG, "Google play Services not Available - show dialog to ask user to download it ");
            GoogleApiAvailability.getInstance().getErrorDialog(this, response, 1).show();
        } else
        {
            Log.d(TAG, "Google play Services is available - no action required");
        }
    }

    @Override
    public void onStart()
    {
        Log.d(TAG, "onStart called");
        super.onStart();
        googleApiClient.reconnect();
    }

    @Override
    public void onStop()
    {
        Log.d(TAG, "onStop called");
        super.onStop();
        googleApiClient.disconnect();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle)
    {
    }

    @Override
    public void onProviderEnabled(String s)
    {
    }

    @Override
    public void onProviderDisabled(String s)
    {
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        Log.d(TAG,"Connected to Google Api Client");
        GeoFenceMonitoring();
    }

    @Override
    public void onConnectionSuspended(int i)
    {
        Log.d(TAG,"Suspended connection to Google Api Client");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        Log.d(TAG, "Failed to connect to  Google Api Client - " + connectionResult.getErrorMessage());
        googleApiClient.reconnect();
    }

    public void GeoFenceMonitoring()
    {
        Geofence geofence = new Geofence.Builder()
                .setRequestId(MY_GEOFENCE_ID)
                .setCircularRegion(41.086671, 23.534352, 100)
                .setExpirationDuration(Geofence.NEVER_EXPIRE)
                .setNotificationResponsiveness(1000)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build();

        GeofencingRequest geofencingRequest = new GeofencingRequest.Builder()
                .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                .addGeofence(geofence)
                .build();

        Intent intent = new Intent(this, GeofenceService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (!googleApiClient.isConnected())
        {
            Log.d(TAG, "!!!!_________________________________________ GoogleApiClient is not connected");

        } else
        {
            Log.d(TAG, "!!!! _________________________________________!!!!!!!!");

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                checkLocationPermission();
            }


            LocationServices.GeofencingApi.addGeofences(googleApiClient, geofencingRequest, pendingIntent)
                    .setResultCallback(new ResultCallback<Status>()
                    {
                        @Override
                        public void onResult(@NonNull Status status)
                        {
                            checkLocationPermission();

                            if (status.isSuccess())
                            {
                                Log.d(TAG, String.valueOf(status.getStatusCode()));
                                Log.d(TAG, "!!!! Successfully added to geofence");
                            } else
                            {
                                Log.d(TAG, "!!!! Failed to add geofence");
                                Log.d(TAG, "Called... FAILURE: " + status.getStatusMessage() + " code: " + status.getStatusCode());
                            }
                        }
                    });
        }
    }
}
