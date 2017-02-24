package kiam.locationphotoboard;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationServices;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;



public class MapTestActivity extends Activity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    final String TAG = "MapTestActivity";
    final String MAPS_API_KEY = "AIzaSyBrBtIogaQ2lklgpqhAc3XXmEOqXdI_U4s";
    private GoogleApiClient GAC;
    private Post p;
    private int flag;
    private Marker testMarker, testMarker2;
    protected TextView mThumbnail;


    private static final int FINE_LOCATION_PERMISSION_REQUEST = 1;
    private static final int CONNECTION_RESOLUTION_REQUEST = 2;
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    private Location mLastLocation;
    private double mLatitudeText;
    private double mLongitudeText;
    protected ImageView mImage;

    /*
     * Initialize the Map and its Views are set to INVISIBLE
     * Generates data for the GoogleAPIClient
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Connects to activity_maps.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //creates the space (fragment) that the map will sit in
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Ready up the textView
        mThumbnail = (TextView) findViewById(R.id.thumbnail);
        mThumbnail.setVisibility(View.INVISIBLE);

        // Ready up the imageView
        mImage = (ImageView) findViewById(R.id.mp);
        mImage.setVisibility(View.INVISIBLE);

        buildGoogleAPIClient();
    }

    //Ready up the Map methods
    private void buildGoogleAPIClient() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    /*
     * When the map is ready to be displayed:
     *  - listeners are created
     *  - user's location is accessed
     */

    @Override
    public void onMapReady(GoogleMap map) {
        //instantiapes the map and a test post.
        mMap = map;

        //enables user's location to be manipulated by map methods
        try {
            map.setMyLocationEnabled(true);
        } catch (SecurityException e)
        {
            Log.d(TAG, "No permissons");
        }

        //sets the on click listener of the map to this class which impliments the onMarkerClickListener class.
        mMap.setOnMarkerClickListener(this);

        //displays text only when the marker is clicked, else it will hide it again
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (mThumbnail.getVisibility() == View.VISIBLE) {
                    mThumbnail.setVisibility(View.INVISIBLE);
                    mImage.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
    /*
    * Get's the marker's properties and set the image and text views VISIBLE once respective fields are obtained
    * Temporary Post object created to decode the passed Tag
    */

    @Override
    public boolean onMarkerClick(final Marker marker) {

        //gets the object associated with that marker and turns on the thumbnail
        if (marker.getTag() != null)
        {
            //sets the text part of the thumbnail
            Post temp = (Post) marker.getTag();
            mThumbnail.setText(temp.getTextContent());
            mThumbnail.setVisibility(View.VISIBLE);

            //sets the image part of the thumbnail
            mImage.setImageBitmap(temp.getImage());
            mImage.setVisibility(View.VISIBLE);
        }

        //NOTE TO SELF: checking if the post is visable and then turning it invisible overwrites what just happened above
        //              checking if the most is the same tag is useless because each time a marker is clicked theat tag is sent anyway
        //that shit down there does not work
//        if (mThumbnail.getText().equals(marker.getTag().toString()) && mThumbnail.getVisibility() == View.VISIBLE)
//        {
//            mThumbnail.setVisibility(View.INVISIBLE);
//        }

        return false;
    }

    /*
     * Once the map is connected to Google, permissions are requested and the
     * map camera pans to the user's current location -- thanks alvis
     *
     * Test Markers are created and dropped near the user's current location
     *  - initialized with random bs properties
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        findLocation();
    }

    private void findLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    FINE_LOCATION_PERMISSION_REQUEST);
        } else {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            LatLng latlng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 13));
            mMap.setMyLocationEnabled(true);
            mLatitudeText = mLastLocation.getLatitude();
            mLongitudeText = mLastLocation.getLongitude();

            //TEST MARKER GENERATOR
            addTestMarkers(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        }
    }

    //REQUIRED TO BE OVERWRITIEN METHODS

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connection suspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, CONNECTION_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                // There was an error with the resolution intent. Try again.
                mGoogleApiClient.connect();
            }
        } else {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(connectionResult.getErrorCode(), this, 1);
            dialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONNECTION_RESOLUTION_REQUEST && resultCode == RESULT_OK) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case FINE_LOCATION_PERMISSION_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    findLocation();
                }
            }
        }
    }

    /*
     * Creates test Marker objects filled with random stuff
     *   - the Post object associated with the Marker is created within the setting of the tag
     *   - TODO: this works for now but is subject to change upon backendless integration
     */
    public void addTestMarkers(double lat, double longi)
    {
        //adds a test marker
        testMarker = mMap.addMarker(new MarkerOptions()
                .title("test")
                .position(new LatLng(lat + 0.005, longi))
                .snippet("before test post")
        );

        testMarker2 = mMap.addMarker(new MarkerOptions()
                .title("test2")
                .position(new LatLng(lat - 0.005, longi - 0.005))
                .snippet("test numero dux")
        );

        //Checks the version of the user's andriod (if its >= 19)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        //
            testMarker.setTag(new Post("https://puu.sh/ugScO.png", "fuck this gay earth"));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            testMarker2.setTag(new Post("https://puu.sh/uiaJv.png", "i really want this hoodie lol"));
        }

    }

}
