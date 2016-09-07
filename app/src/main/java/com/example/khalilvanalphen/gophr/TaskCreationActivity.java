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

import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.Calendar;


public class TaskCreationActivity extends AppCompatActivity{

    final GphTask newTask = new GphTask();

    Button btnCreate, btnPickPlace, btnPickTime;
    EditText nameField, descField;
    ListView timeList, placeList;
    ArrayAdapter timeListAdapter, placeListAdapter;

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

        timeListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, newTask.getTimes());
        timeList.setAdapter(timeListAdapter);
        placeListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, newTask.getLocations());
        placeList.setAdapter(placeListAdapter);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            public void onClick(View v) {

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
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(TaskCreationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Time newT = new Time();
                        newT.setTag("New Time");
                        newT.setDay(Calendar.DAY_OF_MONTH);
                        newT.setMonth(Calendar.MONTH);
                        newT.setHour(selectedHour);
                        newT.setMinute(selectedMinute);
                        newTask.addTime(newT);

                        timeListAdapter.notifyDataSetChanged();
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                Location newl = new Location();
                newl.setLat(PlacePicker.getPlace(TaskCreationActivity.this, data).getLatLng().latitude);
                newl.setLng(PlacePicker.getPlace(TaskCreationActivity.this, data).getLatLng().longitude);
                newl.setTag("New Loc");
                newTask.addLocation(newl);

                placeListAdapter.notifyDataSetChanged();
            }
        }
    }

}
