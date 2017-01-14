package kiam.locationphotoboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Bind;

public class ObjectStructureActivity extends AppCompatActivity
{

    private Post theContent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_structure);

        //idk what i'm doing, does this let you use the things?
        final Button addtotext = (Button) findViewById(R.id.addtotext); //button to add to the post
        final TextView thePost = (TextView) findViewById(R.id.textpost); //the post
        final EditText putpost = (EditText) findViewById(R.id.putpost); // the text field that gets posted
        final TextView theDate = (TextView) findViewById(R.id.theDate);


        addtotext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //does the string length check and if passes uploads the text post.
                theContent = new Post(putpost.getText().toString());
                thePost.setText(theContent.getTextContent());

                //sets the date of the post
                //janky way to test if the message is too long, don't put a date out.
                if (!thePost.getText().equals("MESSAGE TOO LONG"))
                theDate.setText(theContent.getDate().toString());
            }

        });
    }



}
