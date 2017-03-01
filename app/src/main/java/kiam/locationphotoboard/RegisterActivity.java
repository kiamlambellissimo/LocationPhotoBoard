package kiam.locationphotoboard;

import android.content.Intent;
import android.net.Uri;
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
import com.backendless.exceptions.BackendlessException;
import com.backendless.exceptions.BackendlessFault;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.Bind;

public class RegisterActivity extends AppCompatActivity {


    final String TAG = "RegisterActivity";


    ////connecting the xml items into this java class and turning them into their according java object
    @Bind(R.id.registerButton)
    Button mRegisterButton;

    //@Bind(R.id.usernameTextField)
    EditText mUsernameTextField;

    //@Bind(R.id.emailAddressTextField)
    EditText mEmailAddressTextField;

    //@Bind(R.id.passwordTextField)
    EditText mPasswordTextField;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    public void Register(View view) {
        Log.d(TAG, "Check0");
        String username = mUsernameTextField.getText().toString();
        String emailAddress = mEmailAddressTextField.getText().toString();
        String password = mPasswordTextField.getText().toString();


        if (username == null || username.equals("")) {
            showToast("Name cannot be empty");
            return;
        }


        if (password == null || password.equals("")) {
            showToast("Password cannot be empty");
            return;
        }


        Log.d(TAG, "Check1");

        BackendlessUser user = new BackendlessUser(); //creating a new user object

        //setting the user parameters
        user.setPassword(password);
        user.setEmail(emailAddress);
        user.setProperty("username", username);
        user.setProperty("loginID", username);
        Log.d(TAG, "Check2");
//////ADD TESTERS

        //registering throug backendless


        Backendless.UserService.register( user, new DefaultCallback<BackendlessUser>( RegisterActivity.this )
        {
            @Override
            public void handleResponse( BackendlessUser response )
            {
                Log.d(TAG, "Check3");
                super.handleResponse( response );
                startActivity( new Intent( getBaseContext(), MainActivity.class ) );
                finish();
            }
        } );
        /*
        try {
            user = Backendless.UserService.register(user);
        } catch (BackendlessException exception) {
            Log.d(TAG, "It fked up");
 d
            // an error has occurred, the error code can be retrieved with fault.getCode()
        }
*/

    }

    @Override       //the on create is what is run automatically when activity is ran
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsernameTextField = (EditText) findViewById(R.id.usernameTextField);
        mEmailAddressTextField = (EditText) findViewById(R.id.emailAddressTextField);
        mPasswordTextField = (EditText) findViewById(R.id.passwordTextField);
        Log.d(TAG, "Check-0");
      /*
        mRegisterButton.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick( View view )
            {
               Register(null);

            }
        });*/


    /*
        setContentView(R.layout.activity_register);
        final String Tag = "RegisterActivity";
        BackendlessUser user = new BackendlessUser();
        user.setProperty(    "email", "mo@gmail.com" );
        user.setPassword( "iAmWatchingU" );
    */

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Register Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}


   // Backendless.( appId, secretKet, version ); // where to get the argument values for this call











