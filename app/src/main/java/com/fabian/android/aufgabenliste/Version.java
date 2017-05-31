package com.fabian.android.aufgabenliste;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Version extends AppCompatActivity
{
    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstancesState)
    {
        super.onCreate (savedInstancesState);
        setContentView (R.layout.activity_version);

        toolbar = (Toolbar) findViewById(R.id.toolbarVersion);
        setSupportActionBar (toolbar);
        toolbar.setTitle ("Version");

        getSupportActionBar().setDisplayHomeAsUpEnabled (true);
    }
}
