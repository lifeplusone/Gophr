package com.example.khalilvanalphen.gophr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ItemViewActivity extends AppCompatActivity {

    GphTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
    }

    @Override
    protected void onStart(){
        super.onStart();

        task = getIntent().getParcelableExtra("task");

        ((TextView) findViewById(R.id.text_title)).setText(task.getTitle());
        ((TextView) findViewById(R.id.text_desc)).setText(task.getDescription());

        String time = task.getMonth() + "/" + task.getDay() + "  " + task.getHour() + ":" + task.getMinute();

        ((TextView) findViewById(R.id.text_time)).setText(String.valueOf(time));
        if (task.getLocation() != null) ((TextView) findViewById(R.id.text_location)).setText(task.getLocation().toString());
    }
}
