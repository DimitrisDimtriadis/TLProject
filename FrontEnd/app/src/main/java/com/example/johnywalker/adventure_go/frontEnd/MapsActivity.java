package com.example.johnywalker.adventure_go.frontEnd;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;
import com.example.johnywalker.adventure_go.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener
{

    private GoogleMap mMap;
    private SupportMapFragment mapFrag;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Checking if it need diffrent permission access
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkLocationPermission();
        }
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 100, this);
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
//        LatLng myHome = new LatLng(41.088405, 23.545148);
//        mMap.addMarker(new MarkerOptions().position(myHome).title("Marker in my home"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myHome, 14));

//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

//        mMap.addPolyline(new PolylineOptions().add(
//                myHome,
//                teiCM,
//                militaryBase,
//                myHome
//                ).color(Color.RED).width(10)
//        );
    }
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

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
        } else {
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
}
