package kiam.locationphotoboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.backendless.Backendless;
import com.backendless.Persistence;

public class CloudTestActivity extends AppCompatActivity
{

    final String YOUR_APP_ID = "A008628F-CA21-B27B-FFB9-3F872AB77900";
    final String YOUR_SECRET_KEY = "A2C5C06C-9C3D-03A5-FF23-8BD591629C00";
    final String appVersion = "v1";
    final String TAG = "CloudTestActivity";
    Button mSaveThePostButton;

    public void saveNewPost(View view)
    {
        final Post testPost = new Post();
        testPost.testString("The Post.");



       /* Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                Log.d(TAG, "saveNewPost");
                try {
                    Log.d(TAG, "New Post Saved");
                    Post savedPost =  Backendless.Persistence.save(testPost);
                } catch (Exception e) {
                    Log.d(TAG, "New Post Failed");
                    e.printStackTrace();
                }
            }
        });
        t.run();*/

    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_test);
        mSaveThePostButton = (Button) findViewById(R.id.savePost);
        Backendless.initApp(this, YOUR_APP_ID, YOUR_SECRET_KEY, appVersion);

        mSaveThePostButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                saveNewPost(null);

            }
        });


    }

}
