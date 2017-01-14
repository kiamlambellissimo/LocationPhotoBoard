package kiam.locationphotoboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.Bind;

public class MainActivity extends AppCompatActivity
{

    final EditTest etName = (EditText) findViewById(R.id.etName);
    final EditTest etAge = (EditText) findViewById(R.id.etAge);
    final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);

    @Bind(R.id.mapActivityButton)
    Button mMapsActivityButton;

    @Bind(R.id.cameraActivityButton)
    Button mCameraActivityButton;

    @Bind(R.id.cloudActivityButton)
    Button mCloudActivityButton;

    @Bind(R.id.objectActivityButton)
    Button mObjectActivityButton;

    @Bind(R.id.uiActivityButton)
    Button mUIActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toMapsActivity(View view){
        Intent i = new Intent(this, MapTestActivity.class);
        startActivity(i);
    }

    public void toCameraActivity(View view){
        Intent i = new Intent(this, CameraTestActivity.class);
        startActivity(i);
    }

    public void toCloudActivity(View view){
        Intent i = new Intent(this, CloudTestActivity.class);
        startActivity(i);
    }

    public void toUIActivity(View view){
        Intent i = new Intent(this, UITestActivity.class);
        startActivity(i);
    }

    public void toObjectStructureActivity(View view){
        Intent i = new Intent(this, ObjectStructureActivity.class);
        startActivity(i);
    }
}
