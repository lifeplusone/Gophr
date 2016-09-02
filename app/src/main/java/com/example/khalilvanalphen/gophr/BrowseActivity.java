package com.example.khalilvanalphen.gophr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class BrowseActivity extends AppCompatActivity {

    Button btnDebug;
    ListView listView;



    Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        mRef = new Firebase("https://gophr-c8962.firebaseio.com/Items");
    }

    @Override
    protected void onStart(){
        super.onStart();
        btnDebug = (Button) findViewById(R.id.btn_debug);

        btnDebug.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TaskCreationActivity.class);
                startActivityForResult(i, 1);
                mRef.push().setValue(null);
            }
        });

        listView = (ListView) findViewById(R.id.list_view);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ItemViewActivity.class);
                i.putExtra("title", (String) listView.getItemAtPosition(position));
                startActivity(i);
            }
        });


        mRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        adapter.add(dataSnapshot.getValue(GphTask.class).name);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        adapter.add(dataSnapshot.getValue(GphTask.class).name);
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {
                    GphTask task = data.getParcelableExtra("task");
                    Toast.makeText(getApplicationContext(), task.name, Toast.LENGTH_SHORT).show();
                    mRef.push().setValue(task.name);
                }
                break;
            }
        }
    }
}
