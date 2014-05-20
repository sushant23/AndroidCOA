package com.exple.customlistview.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sushant on 5/18/14.
 */
public class AddActivity extends Activity implements View.OnClickListener {
    EditText etTitle;
    EditText etDescription;
    EditText etImage;
    Button btnSave;
    DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addactivity);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etImage = (EditText) findViewById(R.id.etImage);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSave:
                ListModel info = new ListModel();
                info.setTitle((etTitle.getText()).toString());
                info.setDescription((etDescription.getText()).toString());
                info.setImage((etImage.getText()).toString());
                dbHandler.addInfo(info);
                Intent i = new Intent(this, CustomListView.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
