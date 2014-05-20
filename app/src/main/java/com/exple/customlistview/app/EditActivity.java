package com.exple.customlistview.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by sushant on 5/19/14.
 */
public class EditActivity extends Activity{
    EditText editTitle;
    EditText editDesc;
    EditText editImage;
    Button btnSave;
    DBHandler db = new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editactivity);
        editTitle = (EditText) findViewById(R.id.etEditTitle);
        editDesc = (EditText) findViewById(R.id.etEditDesc);
        editImage = (EditText) findViewById(R.id.etEditImage);
        btnSave = (Button) findViewById(R.id.btnEditSave);
        final Bundle extras = getIntent().getExtras();
        String title = extras.getString("title");
        String desc = extras.getString("description");
        String image = extras.getString("image");
        editTitle.setText(title);
        editDesc.setText(desc);
        editImage.setText(image);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListModel updatedData = new ListModel();
                updatedData.setId(extras.getInt("id"));
                updatedData.setTitle(String.valueOf(editTitle.getText()));
                updatedData.setDescription(String.valueOf(editDesc.getText()));
                updatedData.setImage(String.valueOf(editImage.getText()));

                db.updateInfo(updatedData);
                Intent intent = new Intent(EditActivity.this, CustomListView.class);
                startActivity(intent);
            }
        });
    }
}
