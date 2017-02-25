package kiam.locationphotoboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import butterknife.Bind;

public class LoginActivity extends AppCompatActivity {

    final String TAG = "LoginActivity";
    //binding the XML objects (buttons, textfields) to this java class

    @Bind(R.id.loginButton)
    Button mLoginButton;

  //  @Bind(R.id.loginActivityUsernameTF)
    EditText mUsernameTextField;

   // @Bind(R.id.loginActivityPasswordTF)
    EditText mPasswordTextField;

    //creating a login method to handle anything login related
    public void Login(View view)
    {
        Log.d(TAG, "Check0");
        String username = mUsernameTextField.getText().toString();
        String password = mPasswordTextField.getText().toString();
        Log.d(TAG, "Check1");


        Backendless.UserService.login( username, password, new DefaultCallback<BackendlessUser>( LoginActivity.this )
        {
            public void handleResponse( BackendlessUser backendlessUser )
            {
                Log.d(TAG, "Check2");
                super.handleResponse( backendlessUser );
                MainActivity.loginT = true;
                startActivity( new Intent( getBaseContext(), MainActivity.class ) );
            }
        });



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Backendless.initApp(this, MainActivity.YOUR_APP_ID, MainActivity.YOUR_SECRET_KEY, MainActivity.appVersion);
       mUsernameTextField = (EditText) findViewById(R.id.loginActivityUsernameTF);
        mPasswordTextField = (EditText) findViewById(R.id.loginActivityPasswordTF);

    }

}
