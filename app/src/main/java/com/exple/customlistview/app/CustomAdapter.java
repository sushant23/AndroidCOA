package com.exple.customlistview.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sushant on 5/16/14.
 */
public class CustomAdapter extends BaseAdapter {
    private Activity customListViewActivity;
    private ArrayList allData;
    private static LayoutInflater inflater = null;
    public Resources res;
    ListModel tempValues = null;
    int i = 0;

    public CustomAdapter(Activity a, ArrayList d, Resources resLocal) {

        customListViewActivity = a;
        allData = d;
        res = resLocal;

        inflater = (LayoutInflater) customListViewActivity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public static class ViewHolder {

        public TextView title;
        public TextView description;
        public TextView columnId;
        public ImageView image;


    }


    @Override
    public int getCount() {
        if (allData.size() <= 0)
            return 1;
        return allData.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {

            vi = inflater.inflate(R.layout.listitem, null);

            holder = new ViewHolder();
            holder.title = (TextView) vi.findViewById(R.id.title);
            holder.description = (TextView) vi.findViewById(R.id.description);
            holder.image = (ImageView) vi.findViewById(R.id.image);


            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();

        }


        if (allData.size() <= 0) {
            holder.description.setText("No Data");

        } else {
            tempValues = null;

            tempValues = (ListModel) allData.get(position);
            holder.title.setText(tempValues.getTitle());
            holder.description.setText(tempValues.getDescription());
            holder.image.setImageResource(res.getIdentifier(
                    "com.exple.customlistview.app:drawable/" + tempValues.getImage()
                    , null, null));
            Log.i("ids", String.valueOf(tempValues.getId()));
            vi.setOnClickListener(new OnItemClickListener(tempValues.getId()));
            vi.setOnLongClickListener(new OnItemLongClickListener(tempValues.getId()));
        }

        return vi;
    }

    class OnItemClickListener implements View.OnClickListener {
        int id;

        OnItemClickListener(int id) {
            this.id = id;
        }
        @Override
        public void onClick(View view) {
            CustomListView cusActivity = (CustomListView) customListViewActivity;
            cusActivity.onItemClick(id);
        }
    }

    class OnItemLongClickListener implements View.OnLongClickListener{
        int id;

        OnItemLongClickListener(int id) {
            this.id = id;
        }

        @Override
        public boolean onLongClick(View view) {
            CustomListView cusActivity = (CustomListView) customListViewActivity;
            cusActivity.onItemLongClick(id);
            return false;
        }
    }
}
