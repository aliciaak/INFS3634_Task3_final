package com.example.infs3634_task3.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.infs3634_task3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

/*
- progress bar indicator (show loading of results)
- fragments: search, favorite, bottomnav
- activity layouts: main activity, search, favorite, results rows, cat detail

- drawables: search - magnifying glass, favorite - star
 */