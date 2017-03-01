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

public class ObjectStructureActivity extends AppCompatActivity {

    private int allowance;
    private Post theContent;
    private Post comment;
    private boolean goodLength;

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_structure);

        //initialize allowance for eating
        allowance = 0;
<<<<<<< HEAD
//        theContent = new Post();
//        comment = new Post();
=======

        theContent = new Post();
        comment = new Post();
>>>>>>> origin/LoginBranch
        goodLength = true;

        //idk what i'm doing, does this let you use the things?
        final Button addtotext = (Button) findViewById(R.id.addtotext); //button to add to the post
        final TextView thePost = (TextView) findViewById(R.id.textpost); //the post
        final EditText putpost = (EditText) findViewById(R.id.putpost); // the text field that gets posted
        final TextView theDate = (TextView) findViewById(R.id.theDate); //the date of the post, won't show up if post too long
        final Button upButton = (Button) findViewById(R.id.up); // up and downvote buttons
        final Button downButton = (Button) findViewById(R.id.down);
        final Button addToComments = (Button) findViewById(R.id.postcomment); //button for entering the comment
        final EditText putComment = (EditText) findViewById(R.id.entercomment); //field for entering comment
        final TextView theRating = (TextView) findViewById(R.id.rating);

        //initalize theContent to be global

        //adding text post system:
        addtotext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                theContent.testString(putpost.getText().toString());
                //does the string length check and if passes uploads the text post.
                thePost.setText(theContent.getTextContent());

                //sets the date of the post
                //janky way to test if the message is too long, don't put a date out.
                if (!thePost.getText().equals("MESSAGE TOO LONG")) {
                    theDate.setText(theContent.getDate().toString());
                }

                else {
                    //initialize rating display to 0
                    theRating.setText("0");
                    goodLength = false;
                }
            }
        });

        //rating system: (can only up or down vote once)
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodLength) {
                    if (allowance == 0) {
                        theContent.addRating();
                        theRating.setText(Integer.toString(theContent.getRating()));
                        allowance = 1; //the check to only allow 1 up or down vote
                    } else if (allowance == 2) {
                        theContent.addRating();
                        theContent.addRating();
                        theRating.setText(Integer.toString(theContent.getRating()));
                        allowance = 1; //the check to only allow 1 up or down vote
                    } else if (allowance == 1) {
                        theContent.subRating();
                        theRating.setText(Integer.toString(theContent.getRating()));
                        allowance = 0;
                    } else {
                        theRating.setText(theRating.getText()); //sets the same changed rating if they press it more than once
                    }
                }
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodLength) {
                    if (allowance == 0) {
                        theContent.subRating();
                        theRating.setText(Integer.toString(theContent.getRating()));
                        allowance = 2; //the check to only allow 1 up or down vote

                    } else if (allowance == 1) {
                        theContent.subRating();
                        theContent.subRating();
                        theRating.setText(Integer.toString(theContent.getRating()));
                        allowance = 2; //the check to only allow 1 up or down vote
                    } else if (allowance == 2) {
                        theContent.addRating();
                        theRating.setText(Integer.toString(theContent.getRating()));
                        allowance = 0;
                    } else {
                        theRating.setText(theRating.getText()); //sets the same changed rating if they press it more than once
                    }
<<<<<<< HEAD
                }
            }
        });
    }
                }
            }
        });

        addToComments.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                comment.testString(putComment.getText().toString());
                comment.addToComments(comment);
                for (Post p : comment.getComments())
                {
                    theComments.setText(p.getTextContent());
                }
            }
        });
        */
    //}
}