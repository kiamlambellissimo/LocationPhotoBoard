package kiam.locationphotoboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditTest etUsername = (EditText) findViewById(R.id.etUserName);
        final EditTest etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        registerLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void (onClick(View v))
            {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class); //create intent that opens register activity
                LoginActivity.this.startActivity(registerIntent); //we tell current activity were on to open ---perform intent
            }
        });


    }
}
