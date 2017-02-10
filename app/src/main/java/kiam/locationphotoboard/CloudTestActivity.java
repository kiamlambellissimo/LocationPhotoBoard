package kiam.locationphotoboard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.Files;
import com.backendless.Persistence;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;

import java.io.File;

import butterknife.Bind;

public class CloudTestActivity extends AppCompatActivity
{


    @Bind(R.id.imageButton)
    Button mImageButton;

    //@Bind(R.id.savePostButton)
    Button mSaveThePostButton;

    @Bind(R.id.setIDButton)
    Button mSetIDButton;

    //@Bind(R.id.setIDField)
    EditText mSetIDField;



    final String YOUR_APP_ID = "A008628F-CA21-B27B-FFB9-3F872AB77900";
    final String YOUR_SECRET_KEY = "A2C5C06C-9C3D-03A5-FF23-8BD591629C00";
    final String appVersion = "v1";
    final String TAG = "CloudTestActivity";

    String mPostID;
    Bitmap mImage;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_test);
        Backendless.initApp(this, YOUR_APP_ID, YOUR_SECRET_KEY, appVersion);

        mImage = null;
        mPostID = null;

        mSetIDField = (EditText) findViewById(R.id.setIDField);
        mSaveThePostButton = (Button) findViewById(R.id.savePostButton);
        mSaveThePostButton.setEnabled(false);

       // mSaveThePostButton.setEnabled(false);
    }

    public void saveNewPost(View view)
    {
        final Post testPost = new Post();
        testPost.setName("Test");

        Backendless.Persistence.save(testPost, new AsyncCallback<Post>()
        {
            @Override
            public void handleResponse(Post post)
            {
                Log.d(TAG, "It worked");
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault)
            {
                Log.d(TAG, "It didn't work");
            }
        });

        AsyncCallback responder = new AsyncCallback()
        {
            @Override
            public void handleResponse(Object o)
            {
                Log.d(TAG, "It saved");
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault)
            {
                Log.d(TAG, "it didnt save");
            }
        };

        Log.d(TAG, mPostID);
        Backendless.Files.Android.upload(mImage, Bitmap.CompressFormat.JPEG, 100, mPostID, "media", responder);
    }

    public void selectImage(View view)
    {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");
        startActivityForResult(i, 100);
    }

    public void setPostID(View view)
    {
        mPostID = mSetIDField.getText().toString();
        Log.d(TAG, mSetIDField.getText().toString() + " | " + mPostID);

        mSaveThePostButton.setEnabled(true);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK)
        {
            try {
                final Uri imageUri = data.getData();
                mImage = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
            } catch(Exception e) { e.printStackTrace();}

        }
    }
}

