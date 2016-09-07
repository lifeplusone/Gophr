package com.example.khalilvanalphen.gophr;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.ArrayList;
import java.util.Calendar;


public class TaskCreationActivity extends AppCompatActivity{

    final GphTask newTask = new GphTask();

    Button btnCreate, btnPickPlace, btnPickTime;
    EditText nameField, descField;
    ListView timeList, placeList;
    ArrayAdapter timeListAdapter, placeListAdapter;

    ArrayList<Location> locations = new ArrayList<>();
    ArrayList<Time> times = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskcreation);



        btnCreate = (Button) findViewById(R.id.tc_create);
        btnPickPlace = (Button) findViewById(R.id.tc_pick_place);
        btnPickTime = (Button) findViewById(R.id.tc_pick_time);
        nameField = (EditText) findViewById(R.id.tc_name);
        descField = (EditText) findViewById(R.id.tc_desc);
        timeList = (ListView) findViewById(R.id.tc_time_list);
        placeList = (ListView) findViewById(R.id.tc_place_list);

        timeListAdapter = new TimeAdapter(this, R.layout.time_list_row, times);
        timeList.setAdapter(timeListAdapter);
        placeListAdapter = new PlaceAdapter(this, R.layout.location_list_row, locations);
        placeList.setAdapter(placeListAdapter);

        nameField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    ViewUtilities.hideKeyboard(TaskCreationActivity.this, v);
                }
            }
        });


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            public void onClick(View v) {

                boolean bOk = true;
                bOk = bOk && nameField.getText().length() > 0;

                if (!bOk){
                    Toast.makeText(TaskCreationActivity.this, "Please enter a title", Toast.LENGTH_LONG).show();
                    return;
                }

                for (Location e : locations)newTask.addLocation(e);
                for (Time e : times)newTask.addTime(e);

                newTask.setTitle(nameField.getText().toString());
                newTask.setDescription(descField.getText().toString());

                Intent resultIntent = new Intent();
                resultIntent.putExtra("task", newTask);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        btnPickPlace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    startActivityForResult(builder.build(TaskCreationActivity.this), 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnPickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(TaskCreationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Time newT = new Time();
                        newT.setDay(Calendar.DAY_OF_MONTH);
                        newT.setMonth(Calendar.MONTH);
                        newT.setHour(selectedHour);
                        newT.setMinute(selectedMinute);
                        times.add(newT);

                        timeListAdapter.notifyDataSetChanged();
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                Place place = PlacePicker.getPlace(TaskCreationActivity.this, data);

                Location newl = new Location();
                newl.setLat(place.getLatLng().latitude);
                newl.setLng(place.getLatLng().longitude);
                newl.setName(place.getName().toString());
                newl.setId(place.getId());
                locations.add(newl);

                placeListAdapter.notifyDataSetChanged();
            }
        }
    }

}
