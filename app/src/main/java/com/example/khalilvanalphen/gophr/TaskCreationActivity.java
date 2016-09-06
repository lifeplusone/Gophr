package com.example.khalilvanalphen.gophr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;


public class TaskCreationActivity extends AppCompatActivity {

    Button btnCreate, btnPickPlace;
    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskcreation);

        btnCreate = (Button) findViewById(R.id.tc_create);
        btnPickPlace = (Button) findViewById(R.id.tc_pick_place);
        nameField = (EditText) findViewById(R.id.tc_name);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("task", new GphTask(nameField.getText().toString()));
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

        btnPickPlace.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    pickPlace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });




    }

    public void pickPlace() throws Exception{
        int PLACE_PICKER_REQUEST = 1;
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place selectedPlace = PlacePicker.getPlace(this, data);
                Toast.makeText(getApplicationContext(), selectedPlace.getAddress(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
