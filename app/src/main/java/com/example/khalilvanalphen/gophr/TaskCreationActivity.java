package com.example.khalilvanalphen.gophr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TaskCreationActivity extends AppCompatActivity {

    Button btnCreate;
    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskcreation);

        btnCreate = (Button) findViewById(R.id.tc_create);
        nameField = (EditText) findViewById(R.id.tc_name);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("task", new GphTask(nameField.getText().toString()));
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

}
