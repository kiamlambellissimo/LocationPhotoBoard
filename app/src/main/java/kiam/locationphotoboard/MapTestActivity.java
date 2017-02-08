package kiam.locationphotoboard;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class MapTestActivity extends Activity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    final String TAG = "MapTestActivity";
    final String MAPS_API_KEY = "AIzaSyBrBtIogaQ2lklgpqhAc3XXmEOqXdI_U4s";
    private GoogleMap mMap;
    private GoogleApiClient GAC;
    private Post p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        //p = new Post("Test");
        LatLng sydney = new LatLng(-33.867, 151.206);
        try {
            map.setMyLocationEnabled(true);
        } catch (SecurityException e)
        {
            Log.d(TAG, "No permissons");
        }        mMap.addMarker(new MarkerOptions()
                        .title("Sydney")
                        .snippet("Click here to see a Post!")
                        .position(sydney));
                        //.setTag());
                mMap.setOnMarkerClickListener(this);



        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

    }


    @Override
    public boolean onMarkerClick(final Marker marker) {

        //marker.set

        return false;
    }
}

/*

package kiam.locationphotoboard;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Kiam on 2017-01-12.
 */
/*
public class MapTestActivity extends AppCompatActivity implements OnMapReadyCallback{}
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
    }




}
*/