package com.pintu.neostore.drawer.store_locator;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pintu.neostore.R;
import com.pintu.neostore.adapter.StoreLocatorAdapter;
import com.pintu.neostore.model.map.MapsAPI;

import java.lang.reflect.Type;
import java.util.List;

public class StoreLocator extends AppCompatActivity implements OnMapReadyCallback {

    StoreLocatorAdapter storeLocatorAdapter;
    RecyclerView recyclerView;

    private GoogleMap mMap;
    LatLng mumbai;

    ImageButton back_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_locator);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        back_Btn = (ImageButton) findViewById(R.id.back_btn);

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "location.json");
        Log.i("data", jsonFileString);

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<MapsAPI>>() {
        }.getType();

        List<MapsAPI> users = gson.fromJson(jsonFileString, listUserType);
        for (int i = 0; i < users.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + users.get(i));

        }

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Log.d("saurabh ", "name " + users.get(0).getStoreName());
        storeLocatorAdapter = new StoreLocatorAdapter(StoreLocator.this, users);
        recyclerView.setAdapter(storeLocatorAdapter);

        back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreLocator.super.onBackPressed();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mumbai = new LatLng(19.2297281, 72.845639);
        mMap.addMarker(new MarkerOptions().position(mumbai).title("Royal Touche"));

        mumbai = new LatLng(19.2337028, 72.8621114);
        mMap.addMarker(new MarkerOptions().position(mumbai).title("A to Z Furnishing"));

        mumbai = new LatLng(19.22786, 72.8551824);
        mMap.addMarker(new MarkerOptions().position(mumbai).title("Godrej Interio-Furniture Store"));

        mumbai = new LatLng(19.232180, 72.869150);
        mMap.addMarker(new MarkerOptions().position(mumbai).title("Shree Mahalaxmi Furniture"));

        mumbai = new LatLng(19.229500, 72.860320);
        mMap.addMarker(new MarkerOptions().position(mumbai).title("Radha Krushna Furniture"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mumbai, 13.0f));
//       mMap.moveCamera(CameraUpdateFactory.newLatLng(mumbai));
//        mMap.animateCamera( CameraUpdateFactory.zoomTo( 13.0f ) );
    }
}
