package kiam.locationphotoboard;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MapTestActivity extends Activity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    final String TAG = "MapTestActivity";
    final String MAPS_API_KEY = "AIzaSyBrBtIogaQ2lklgpqhAc3XXmEOqXdI_U4s";
    private GoogleMap mMap;
    private GoogleApiClient GAC;
    private Post p;
    private int flag;
    private Marker testMarker, testMarker2;
    protected TextView mThumbnail;

    protected ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Connects to activity_maps.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //creates the space (fragment) that the map will sit in
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mThumbnail = (TextView) findViewById(R.id.thumbnail);
        mThumbnail.setVisibility(View.VISIBLE);

        // Display Image
        mImage = (ImageView) findViewById(R.id.mp);
        mImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        //instantiapes the map and a test post.
        mMap = map;

        //sets the location arbitrarily to sydney
        LatLng sydney = new LatLng(-33.867, 151.206);
        LatLng sydney2 = new LatLng(-33.860, 151.150);

        try {
            map.setMyLocationEnabled(true);
        } catch (SecurityException e)
        {
            Log.d(TAG, "No permissons");
        }

        //adds a test marker
        testMarker = map.addMarker(new MarkerOptions()
                .title("test")
                .position(sydney)
                .snippet("before test post")
        );

        testMarker2 = map.addMarker(new MarkerOptions()
                .title("test2")
                .position(sydney2)
                .snippet("test numero dux")
        );

        //sets the tag object accossiated with the marker to an arbitrary string
        testMarker.setTag(new Post(BitmapFactory.decodeFile("/Users/angietong/aakim1/LocationPhotoBoard/app/src/main/res/drawable/mp.png"), "fuck this gay earth"));
        testMarker2.setTag(new Post(BitmapFactory.decodeFile("/Users/angietong/aakim1/LocationPhotoBoard/app/src/main/res/drawable/mp2.png"), "i really want this hoodie lol"));

        //sets the on click listener of the map to this class which impliments the onMarkerClickListener class.
        mMap.setOnMarkerClickListener(this);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        //displays text only when the marker is clicked, else it will hide it again
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (mThumbnail.getVisibility() == View.VISIBLE) {
                    mThumbnail.setVisibility(View.INVISIBLE);
                    //mImage.setVisibility(View.INVISIBLE);
                    }
            }
        });

    }


    //excecutes when a marker is clicked. returns that marker.
    @Override
    public boolean onMarkerClick(final Marker marker) {

        //gets the object associated with that marker and turns on the thumbnail
        if (marker.getTag() != null)
        {
            Post temp = (Post) marker.getTag();
            mThumbnail.setText(temp.getTextContent());
            mThumbnail.setVisibility(View.VISIBLE);

            //mImage.set
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
}
