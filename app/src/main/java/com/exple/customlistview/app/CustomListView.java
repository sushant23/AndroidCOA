package com.exple.customlistview.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CustomListView extends ActionBarActivity {
    ListView list;
    CustomAdapter adapter;
    public ArrayList<ListModel> customArrayList;
    public CustomListView customListView = null;
    DBHandler db = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);
        /*setListData();*/
        customListView = this;
        Resources res = getResources();
        list = (ListView) findViewById(R.id.list);
        adapter = new CustomAdapter(customListView, db.getAllInfo(), res);
        list.setAdapter(adapter);
    }

       void endActivity(){
           finish();
       }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.custom_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add:
                Intent i = new Intent();
                i.setAction("android.intent.action.addActivity");
                startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

   /* public void setListData() {
        customArrayList = db.getAllInfo();
    }*/

    public void onItemClick(int id) {
        ListModel selectedData = db.getSingleInfo(id);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("title", selectedData.getTitle());
        intent.putExtra("description", selectedData.getDescription());
        intent.putExtra("image", selectedData.getImage());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

    public void onItemLongClick(final int id) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(CustomListView.this);
        dialog.setIcon(R.drawable.ic_launcher);
        dialog.setTitle("Choose Option");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                CustomListView.this,
                android.R.layout.select_dialog_item);
        arrayAdapter.add("Edit");
        arrayAdapter.add("Delete");
        dialog.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );

        dialog.setAdapter(arrayAdapter,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int postion) {
                        switch (postion){
                            case 0:
                                ListModel selectedData = db.getSingleInfo(id);
                                Intent intent = new Intent(CustomListView.this, EditActivity.class);
                                intent.putExtra("id",selectedData.getId());
                                intent.putExtra("title", selectedData.getTitle());
                                intent.putExtra("description", selectedData.getDescription());
                                intent.putExtra("image", selectedData.getImage());
                                startActivity(intent);
                                break;
                            case 1:
                                db.deleteInfo(id);
                                customListView = CustomListView.this;
                                Resources res = getResources();
                                list = (ListView) findViewById(R.id.list);
                                adapter = new CustomAdapter(customListView, db.getAllInfo(), res);
                                list.setAdapter(adapter);
                                break;
                        }
                    }
                }
        );

        dialog.show();

    }

}

