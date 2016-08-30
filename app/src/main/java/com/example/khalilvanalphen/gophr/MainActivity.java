package com.example.khalilvanalphen.gophr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button btnFetch;

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
        btnFetch = (Button) findViewById(R.id.btn_fetch);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                btnFetch.setText(text);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
