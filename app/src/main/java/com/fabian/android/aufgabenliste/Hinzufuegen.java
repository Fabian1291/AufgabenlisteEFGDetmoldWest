package com.fabian.android.aufgabenliste;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Hinzufuegen extends AppCompatActivity
{
    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_hinzufuegen);

        toolbar = (Toolbar) findViewById (R.id.toolbarHinzufuegen);
        setSupportActionBar (toolbar);
        toolbar.setSubtitle (R.string.Untertitel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
