package com.fabian.android.aufgabenliste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Aufgaben_Bearbeiten extends AppCompatActivity
{
    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_aufgaben_bearbeiten);

        toolbar = (Toolbar) findViewById(R.id.toolbarAufgaben);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Aufgabe Bearbeiten");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
