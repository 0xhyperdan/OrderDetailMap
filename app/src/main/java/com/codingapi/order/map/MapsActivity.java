package com.codingapi.order.map;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.amap.api.maps.AMap;
import com.amap.api.maps.TextureSupportMapFragment;

public class MapsActivity extends AppCompatActivity {
    private AMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // get screen height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        // init view
        View bottomSheet = findViewById(R.id.bottom_sheet);
        BottomSheetBehavior mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(height / 2);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    Log.i(toString(), "onStateChanged: BottomSheetBehavior.STATE_COLLAPSED");
                }

                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    Log.i(toString(), "onStateChanged: BottomSheetBehavior.STATE_EXPANDED");
                }

                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    Log.i(toString(), "onStateChanged: BottomSheetBehavior.STATE_DRAGGING");
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i(toString(), "onSlide: " + slideOffset);
            }
        });
        // set top bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setTitle("同城跑腿");
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            FragmentManager manager = getSupportFragmentManager();
            if (manager == null) return;
            TextureSupportMapFragment mapFragment = (TextureSupportMapFragment) manager.findFragmentById(R.id.map);
            if (mapFragment == null) return;
            mMap = mapFragment.getMap();
        }
    }

}