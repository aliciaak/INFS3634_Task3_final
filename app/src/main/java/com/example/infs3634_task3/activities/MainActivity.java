package com.example.infs3634_task3.activities;

//test github commit

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.example.infs3634_task3.R;
import com.example.infs3634_task3.adapters.PageAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem tabSearch;
    TabItem tabFavorite;

    ViewPager viewPager;
    PageAdapter pageAdapter;

    Toolbar toolbar_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        tabSearch = findViewById(R.id.tabSearch);
        tabFavorite = findViewById(R.id.tabFavorite);
        viewPager = findViewById(R.id.viewPager);

        toolbar_main = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar_main);

        getSupportActionBar().setTitle("Meowdex");

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pageAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 1) {
                    //tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
                    tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                } else if (tab.getPosition() == 0) {
                    //tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorPrimary));
                    tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }
}