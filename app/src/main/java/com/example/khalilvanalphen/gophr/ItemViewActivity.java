package com.example.khalilvanalphen.gophr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class ItemViewActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GphTask task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

        task = getIntent().getParcelableExtra("task");

        ((TextView) findViewById(R.id.text_title)).setText(task.getTitle());
        ((TextView) findViewById(R.id.text_desc)).setText(task.getDescription());

    }

    @Override
    public void onMapReady(GoogleMap mMap) {

        LatLngBounds.Builder bounds = LatLngBounds.builder();


        for (Location loc : task.getLocations()){
            LatLng location = new LatLng(loc.getLat(), loc.getLng());
            mMap.addMarker(new MarkerOptions().position(location).title(loc.getTag()).snippet(loc.getName())).showInfoWindow();
            bounds.include(location);

        }
        if(task.getLocations() != null){
            int width = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (width * 0.12);
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), width, height, padding));
        }


    }
}
