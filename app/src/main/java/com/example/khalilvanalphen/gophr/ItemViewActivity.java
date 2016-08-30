package com.example.khalilvanalphen.gophr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ItemViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
    }

    @Override
    protected void onStart(){
        super.onStart();
        ((TextView) findViewById(R.id.text_title)).setText(getIntent().getStringExtra("title"));
    }
}
