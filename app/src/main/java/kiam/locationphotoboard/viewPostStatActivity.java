package kiam.locationphotoboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class viewPostStatActivity extends MapTestActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post_stat);
       ImageView mImage = (ImageView) findViewById(R.id.mp);
        mImage.setImageBitmap(temp.getImage());
    }
}
