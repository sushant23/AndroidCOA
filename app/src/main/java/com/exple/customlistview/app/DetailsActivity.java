package com.exple.customlistview.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sushant on 5/19/14.
 */
public class DetailsActivity extends Activity {
    TextView detailsTitle;
    TextView detailsDesc;
    ImageView detailsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailsactivity);
        detailsTitle = (TextView) findViewById(R.id.tvDetailsTitle);
        detailsDesc = (TextView) findViewById(R.id.tvDetailsDesc);
        detailsImage = (ImageView) findViewById(R.id.detailsImage);
        Bundle extras = getIntent().getExtras();
        String title = extras.getString("title");
        String desc = extras.getString("description");
        String image = extras.getString("image");
        detailsTitle.setText(title);
        detailsDesc.setText(desc);
        detailsImage.setImageResource(getResources().getIdentifier(
                "com.exple.customlistview.app:drawable/" + image
                , null, null));
    }
}
