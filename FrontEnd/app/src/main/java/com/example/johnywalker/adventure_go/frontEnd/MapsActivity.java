package com.example.johnywalker.adventure_go.frontEnd;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.johnywalker.adventure_go.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
//GAM6
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, OnConnectionFailedListener, ConnectionCallbacks, LocationListener {

    private GoogleMap mMap;
    //GAM6
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private FusedLocationProviderApi locationProvider = LocationServices.FusedLocationApi;
    private Double myLatitude;
    private Double myLongitude;
    TextView showLong,showLat;
    public static final String GEOFENCE_ID="GeofenceId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        showLong = (TextView) findViewById(R.id.tvDuration);
        showLat = (TextView) findViewById(R.id.tvDistance);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(60 * 1000);
        mLocationRequest.setFastestInterval(15 * 1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Location location =locationProvider.getLastLocation(mGoogleApiClient);
        myLatitude = location.getLatitude();
        myLongitude = location.getLongitude();
        //String url = "http://83.212.100.247:8090/quest/getQuests?latitude=15.23&longitude=14.52&score=5";
        String url = "http://83.212.100.247:8090/quest/getQuests?latitude="+Double.toString(myLatitude)+"&longitude="+Double.toString(myLongitude)+"&score=5";
        getJsonrequest(url);


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    //GAM6
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //GAM6
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        requestLocationUpdates();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }



    }

    private void requestLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling, if not granted.

            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    //GAM6
    @Override
    public void onConnectionSuspended(int i) {

    }

    //GAM6
    @Override
    public void onLocationChanged(Location location) {
        myLatitude = location.getLatitude();
        myLongitude = location.getLongitude();
        showLong.setText(Double.toString(myLongitude));
        showLong.setText(Double.toString(myLatitude));

       // updateMarkers(myLatitude,myLongitude);

    }

    //GAM6
    @Override
    protected void onStart(){
        super.onStart();
        mGoogleApiClient.connect();

    }

    //GAM6
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    //GAM6
    @Override
    protected void onPause() {
        super.onPause();
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
    }

    //GAM6
    @Override
    protected void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()){
            requestLocationUpdates();
        }
    }
    //GAM6


    //GAM6

    private void getJsonrequest (String URL){
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            int count = 0;
                            while(count<response.length()){
                                try {
                                    JSONObject jsonObject = response.getJSONObject(count);
                                    Quest quest = new Quest(jsonObject.getDouble("latitude"),jsonObject.getDouble("longitude"));

                                    JSONObject riddle = jsonObject.getJSONObject("riddle");
                                    Riddle riddles = new Riddle(riddle.getInt("id"),
                                            riddle.getInt("points"),
                                            riddle.getString("question"),
                                            riddle.getString("answer"),
                                            riddle.getString("hint"),
                                            riddle.getString("category"),
                                            riddle.getString("difficulty"));
                                    String Question = riddles.getQuestion();
                                    Double Longitude = quest.getLongitude();
                                    LatLng Point = new LatLng(quest.getLatitude(),quest.getLongitude());
                                    mMap.addMarker(new MarkerOptions().position(Point).title(Question));
                                    //Geofence monitoring

                                    count++;
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MapsActivity.this,"WRONG",Toast.LENGTH_SHORT).show();
                            error.printStackTrace();
                        }
                    }

            );
        MySingleton.getInstance(MapsActivity.this).addToRequestqueue(jsonArrayRequest);



          }//Method end



}
