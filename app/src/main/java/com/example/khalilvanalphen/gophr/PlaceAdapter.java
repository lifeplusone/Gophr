package com.example.khalilvanalphen.gophr;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaceAdapter extends ArrayAdapter<Location> {

    Context context;
    int layoutResourceId;
    ArrayList<Location> data;

    public PlaceAdapter(Context context, int layoutResourceId, ArrayList<Location> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder = null;





        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new Holder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.tagLine = (EditText) row.findViewById(R.id.tagline);

            row.setTag(holder);
        }
        else
        {
            holder = (Holder)row.getTag();
        }

        Location location = data.get(position);
        if(location != null) {
            holder.txtTitle.setText(location.getName());
        }

        holder.tagLine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                data.get(position).setTag(editable.toString());
            }
        });

        return row;
    }
    static class Holder {
        ImageView imgIcon;
        TextView txtTitle;
        EditText tagLine;
    }
}
