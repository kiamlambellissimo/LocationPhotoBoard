package kiam.locationphotoboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //Hold reference to all the fields and buttons in the activity register
        //use variables
        final EditTest etAge = (EditText) findViewById(R.id.etAge);
        final EditTest etName = (EditText) findViewById(R.id.etName);
        final EditTest etUsername = (EditText) findViewById(R.id.etUserName);
        final EditTest etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

    }
}
