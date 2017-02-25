package kiam.locationphotoboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.backendless.Backendless;
import com.backendless.Persistence;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class CloudTestActivity extends AppCompatActivity
{
    final String TAG = "CloudTestActivity";
    Button mSaveThePostButton;

    public void saveNewPost(View view)
    {
        final Post testPost = new Post();
        testPost.setRating(5);

        Backendless.Persistence.save(testPost, new AsyncCallback<Post>() {
            @Override
            public void handleResponse(Post post) {
                Log.d(TAG, "works");
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                Log.d(TAG, "didnt work");
            }
        });

        /*Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {

                Log.d(TAG, "saveNewPost");
                try {
                    Log.d(TAG, "New Post Saved");


                    Post savedPost =  Backendless.Persistence.save(testPost);
                   *//* PostManager.saveEntity( testPost, CloudTestActivity.this, new InnerCallBack<Post>()
                    {
                        @Override
                        public void handleResponse( Post response )
                        {
                          Log.d(TAG, "worked!");
                        }
                    } );
                    *//*
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
