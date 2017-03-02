package kiam.locationphotoboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.backendless.Backendless;

import butterknife.ButterKnife;
import butterknife.Bind;

public class MainActivity extends AppCompatActivity
{
    final String TAG = "MainActivity";
    public static boolean loginT;
    final static String YOUR_APP_ID = "A008628F-CA21-B27B-FFB9-3F872AB77900";
    final static String YOUR_SECRET_KEY = "A2C5C06C-9C3D-03A5-FF23-8BD591629C00";
    final static String appVersion = "v1";

    @Bind(R.id.mapActivityButton)
    Button mMapsActivityButton;

    @Bind(R.id.registerActivityButton)
    Button mRegisterActivityButton;

    @Bind(R.id.loginActivityButton)
    Button mLoginActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Backendless.initApp(this, YOUR_APP_ID, YOUR_SECRET_KEY, appVersion);
        Log.d(TAG, "LoginVariable = " + loginT);

    }

    public void toRegisterActivity(View view){
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void toLoginActivity(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

        Backendless.initApp(this, YOUR_APP_ID, YOUR_SECRET_KEY, appVersion);
        Log.d(TAG, "LoginVariable = " + loginT);

    public void toObjectStructureActivity(View view) {
        Intent i = new Intent(this, ObjectStructureActivity.class);
        startActivity(i);
    }
}
