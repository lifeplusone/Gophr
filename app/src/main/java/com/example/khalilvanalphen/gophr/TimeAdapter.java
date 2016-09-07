package com.example.khalilvanalphen.gophr;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TimeAdapter extends ArrayAdapter<Time> {

    Context context;
    int layoutResourceId;
    ArrayList<Time> data;

    public TimeAdapter(Context context, int layoutResourceId, ArrayList<Time> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new Holder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);

            row.setTag(holder);
        }
        else
        {
            holder = (Holder)row.getTag();
        }

        Time time = data.get(position);
        if(time != null) {
            holder.txtTitle.setText(time.toString());
        }

        return row;
    }
    static class Holder {
        ImageView imgIcon;
        TextView txtTitle;
    }
}
