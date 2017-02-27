package kiam.locationphotoboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class viewPostStatActivity extends MapTestActivity {

    private int allowance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post_stat);

        allowance = 0;

        final ImageView mImage = (ImageView) findViewById(R.id.mp);
        final TextView theRating = (TextView) findViewById(R.id.rating);
        final TextView mDate = (TextView) findViewById(R.id.theDate);
        final Button upButton = (Button) findViewById(R.id.up); // up and downvote buttons
        final Button downButton = (Button) findViewById(R.id.down);
        final TextView mTitle = (TextView) findViewById(R.id.title);

        mImage.setImageBitmap(temp.getImage());
        theRating.setText("Rating: " + temp.getRating());
        mDate.setText("Posted on: " + temp.getDate().toString());
        mTitle.setText(temp.getTextContent());

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allowance == 0) {
                    temp.addRating();
                    theRating.setText(Integer.toString(temp.getRating()));
                    allowance = 1; //the check to only allow 1 up or down vote
                } else if (allowance == 2) {
                    temp.addRating();
                    temp.addRating();
                    theRating.setText(Integer.toString(temp.getRating()));
                    allowance = 1; //the check to only allow 1 up or down vote
                } else if (allowance == 1) {
                    temp.subRating();
                    theRating.setText(Integer.toString(temp.getRating()));
                    allowance = 0;
                } else {
                    theRating.setText(theRating.getText()); //sets the same changed rating if they press it more than once
                }
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allowance == 0) {
                    temp.subRating();
                    theRating.setText(Integer.toString(temp.getRating()));
                    allowance = 2; //the check to only allow 1 up or down vote

                } else if (allowance == 1) {
                    temp.subRating();
                    temp.subRating();
                    theRating.setText(Integer.toString(temp.getRating()));
                    allowance = 2; //the check to only allow 1 up or down vote
                } else if (allowance == 2) {
                    temp.addRating();
                    theRating.setText(Integer.toString(temp.getRating()));
                    allowance = 0;
                } else {
                    theRating.setText(theRating.getText()); //sets the same changed rating if they press it more than once
                }
            }
        });

    }
}
