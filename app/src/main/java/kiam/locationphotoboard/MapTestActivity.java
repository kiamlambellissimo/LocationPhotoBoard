package kiam.locationphotoboard;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
//implements OnMapReadyCallback, ConnectionCallback and OnConnectionFailedListener
//ConnectionCallback provides callbacks when user is connected/disconnected
//OnConnectionFailedListener provides callbacks when a failed connection attempt is made by the client to the server
public class MapTestActivity extends Activity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    final String TAG = "MapTestActivity";
    final String MAPS_API_KEY = "AIzaSyBrBtIogaQ2lklgpqhAc3XXmEOqXdI_U4s";
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;

    private String mLatitudeText;
    private String mLongitudeText;
    private FusedLocationProviderApi locationProvider;
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;

    public MapTestActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //creates a mapfragment
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //This statement basically lets us access GoogleApiClient and that allows us to do things like
        // let us have callbacks that let us use methods that tell the user if they are
        // connected/disconnected/suspended etc.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }
    //Connects the user to the GoogleApiClient on start
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    //Disconnects the user from the GoogleApiClient after closing
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        LatLng toronto = new LatLng(43.6532, 79.3832);
        try {
            map.setMyLocationEnabled(true);
        } catch (SecurityException e)
        {
            Log.d(TAG, "No permissons");
        }
        LatLng latlng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 13));

       // mMap.addMarker(new MarkerOptions().title("Sydney").snippet("The most populous city in Australia.").position(toronto));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
       //Asks the user for permission to use the GPS
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_ACCESS_COARSE_LOCATION);
        }
        //gets last location of user
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);;
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        /*if (mLastLocation != null) {
            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }**/

    }
    //needs to be implemented but this is where we throw stuff if the connection is suspended
    @Override
    public void onConnectionSuspended(int i) {

    }
    //needs to be implemented but this is where we throw stuff if the connection failed
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

