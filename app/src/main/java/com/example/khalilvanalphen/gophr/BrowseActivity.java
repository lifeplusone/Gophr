package com.example.khalilvanalphen.gophr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class BrowseActivity extends BaseActivity{
    Button btnCreate;
    ListView listView, listViewExtra;

    GphTaskAdapter adapter, adapterExtra;

    Firebase mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        Firebase.setAndroidContext(this);
        mRef = new Firebase("https://gophr-c8962.firebaseio.com/");

    }

    @Override
    public void onStart(){
        super.onStart();
        btnCreate = (Button) findViewById(R.id.btn_create);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TaskCreationActivity.class);
                startActivityForResult(i, 1);
            }
        });

        ArrayList<GphTask> data = new ArrayList<>();
        ArrayList<GphTask> dataExtra = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_view);
        adapter = new GphTaskAdapter(this, R.layout.task_list_row_item, data);
        listView.setAdapter(adapter);

        listViewExtra = (ListView) findViewById(R.id.list_view2);
        adapterExtra = new GphTaskAdapter(this, R.layout.task_list_row_item, dataExtra);
        listViewExtra.setAdapter(adapterExtra);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ItemViewActivity.class);
                i.putExtra("task", (GphTask) listView.getItemAtPosition(position));
                startActivity(i);
            }
        });


        mRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        updateList(dataSnapshot);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        updateList(dataSnapshot);
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
                    Toast.makeText(getApplicationContext(), task.getTitle(), Toast.LENGTH_SHORT).show();
                    mRef.child("tasks").push().setValue(task);
                }
                break;
            }
        }
    }
    public void updateList(DataSnapshot data){
        for (DataSnapshot dataSnapshot : data.getChildren()) {
            GphTask task = dataSnapshot.getValue(GphTask.class);
            if (!task.getOwner().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                adapter.add(task);
            } else {
                adapterExtra.add(task);
            }
        }
    }
}
