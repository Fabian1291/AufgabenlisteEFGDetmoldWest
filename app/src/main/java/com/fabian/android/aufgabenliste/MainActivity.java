package com.fabian.android.aufgabenliste;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        toolbar.setSubtitle("EFG Detmold West");

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager (viewPager);
    }

    private void setupViewPager (ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter (getSupportFragmentManager());
        adapter.addFragment (new OneFragment(), "Aufgaben");
        viewPager.setAdapter(adapter);
    }
}
