package com.exple.customlistview.app;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sushant on 5/18/14.
 */
public class DBHandler extends SQLiteOpenHelper {
    Context context;
    //Database Name
    private static final String DB_NAME = "titledescription";

    //Database Version
    private static final int DB_VERSION = 1;

    //Table name
    private static final String TABLE_NAME = "INFOS";

    //Column names
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_INFO_TABLE = "CREATE TABLE " + TABLE_NAME + "("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TITLE+" TEXT, "+DESCRIPTION+" TEXT, "+IMAGE+" TEXT)";
        sqLiteDatabase.execSQL(CREATE_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    /*Add Information*/

    public void addInfo(ListModel info){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE,info.getTitle());
        values.put(DESCRIPTION, info.getDescription());
        values.put(IMAGE, info.getImage());
        db.insert(TABLE_NAME,null,values);
        /*Toast.makeText(context, "Data Added", Toast.LENGTH_SHORT).show();*/
        db.close();
    }

    /*Get all Information*/
    public ArrayList<ListModel> getAllInfo(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ListModel> lists = new ArrayList<ListModel>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do{
                final ListModel temp = new ListModel();
                temp.setId(Integer.parseInt(cursor.getString(0)));
                temp.setTitle(cursor.getString(1));
                temp.setDescription(cursor.getString(2));
                temp.setImage(cursor.getString(3));
                Log.i("doloop",cursor.getString(1));
                Log.i("doloop",cursor.getString(2));
                lists.add(temp);
            } while (cursor.moveToNext());
        }
        db.close();
        for(ListModel lm: lists){
            Log.i("dbhandler", lm.getTitle());
        }
        return lists;
    }

    /*Get single information*/
    public ListModel getSingleInfo(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        ListModel singleInfo = new ListModel();
        Cursor cursor = db.query(TABLE_NAME, new String[]{"id","title, description, image"}, "id = ?", new String[] {String.valueOf(id)},null, null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        singleInfo.setId(Integer.parseInt(cursor.getString(0)));
        singleInfo.setTitle(cursor.getString(1));
        singleInfo.setDescription(cursor.getString(2));
        singleInfo.setImage(cursor.getString(3));
        db.close();
        return singleInfo;
    }

    /*Update Information*/
    public void updateInfo(ListModel info){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, info.getTitle());
        values.put(DESCRIPTION, info.getDescription());
        values.put(IMAGE, info.getImage());

        db.update(TABLE_NAME, values, ID + "=?", new String[]{String.valueOf(info.getId())});
        /*Toast.makeText(context, "Data updated", Toast.LENGTH_SHORT).show();*/
        db.close();
    }

    /*Delete Info*/
    public void deleteInfo(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + "= ?", new String[] {String.valueOf(id)});
    }
}
