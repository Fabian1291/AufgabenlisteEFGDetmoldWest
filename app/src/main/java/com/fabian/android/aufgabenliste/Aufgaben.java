package com.fabian.android.aufgabenliste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.fabian.android.aufgabenliste.database.AufgabenlisteDatabase;

public class Aufgaben extends AppCompatActivity
{
    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstancesState)
    {
        super.onCreate (savedInstancesState);
        setContentView (R.layout.activity_aufgaben);

        Intent intent = getIntent();
        int i = Integer.valueOf (intent.getStringExtra(FragmentOne.ID_EXTRA));

        toolbar = (Toolbar) findViewById(R.id.toolbarVersion);
        setSupportActionBar (toolbar);
        getSupportActionBar().setTitle ("Aufgaben");

        getSupportActionBar().setDisplayHomeAsUpEnabled (true);

        String Aufgabe = AufgabenlisteDatabase.getAufgabe (i);
    }
}
