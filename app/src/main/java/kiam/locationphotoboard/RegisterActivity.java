package kiam.locationphotoboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import butterknife.Bind;

public class RegisterActivity extends AppCompatActivity {

    final String YOUR_APP_ID = "A008628F-CA21-B27B-FFB9-3F872AB77900";
    final String YOUR_SECRET_KEY = "A2C5C06C-9C3D-03A5-FF23-8BD591629C00";
    final String appVersion = "v1";
    final String TAG = "CloudTestActivity";


    ////connecting the xml items into this java class and turning them into their according java object
    @Bind(R.id.registerButton)
    Button mRegisterButton;

    @Bind(R.id.usernameTextField)
    EditText mUsernameTextField;

    @Bind(R.id.emailAddressTextField)
    EditText mEmailAddressTextField;

    @Bind(R.id.passwordTextField)
    EditText mPasswordTextField;


    public void Register()
    {
        String username = mUsernameTextField.getText().toString();
        String emailAddress = mEmailAddressTextField.getText().toString();
        String password = mPasswordTextField.getText().toString();


        if( username == null || username.equals( "" ) )
        {
            showToast( "Name cannot be empty" );
            return;
        }


        if( password == null || password.equals( "" ) )
        {
            showToast( "Password cannot be empty" );
            return;
        }

        if( emailAddress == null || emailAddress.equals( "" ) )
        {
            showToast( "Email cannot be empty" );
            return;
        }




        BackendlessUser user = new BackendlessUser(); //creating a new user object

        //setting the user parameters
        user.setPassword( password );
        user.setEmail( emailAddress );
        user.setProperty("username", username);
        user.setProperty( "loginID", username );


        //registering throug backendless
        Backendless.UserService.register( user, new DefaultCallback<BackendlessUser>( RegisterActivity.this )
        {
            @Override
            public void handleResponse( BackendlessUser response )
            {
                super.handleResponse( response );
                startActivity( new Intent( getBaseContext(), MainActivity.class ) );
                finish();
            }
        } );
    }

    @Override       //the on create is what is run automatically when activity is ran
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick( View view )
            {
               Register();

            }
        });


    /*
        setContentView(R.layout.activity_register);
        final String Tag = "RegisterActivity";
        BackendlessUser user = new BackendlessUser();
        user.setProperty(    "email", "mo@gmail.com" );
        user.setPassword( "iAmWatchingU" );
    */

    }

    private void showToast( String msg )
    {
        Toast.makeText( this, msg, Toast.LENGTH_SHORT ).show();
    }



    }


   // Backendless.( appId, secretKet, version ); // where to get the argument values for this call











