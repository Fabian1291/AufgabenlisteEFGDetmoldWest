package com.fabian.android.aufgabenliste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.fabian.android.aufgabenliste.database.AufgabenlisteDatabase;

public class Aufgaben extends AppCompatActivity
{
    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstancesState)
    {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_aufgaben);

        Intent intent = getIntent();
        long i = intent.getExtras().getLong("id");

        toolbar = (Toolbar) findViewById(R.id.toolbarVersion);
        setSupportActionBar (toolbar);
        //getSupportActionBar().setTitle ("Aufgaben");

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String Aufgabe = AufgabenlisteDatabase.getInstance(this).getAufgabe(i);

        TextView aufgabe = (TextView) findViewById(R.id.textViewAufgabe1);
        aufgabe.setText(Aufgabe);

        String Datum = AufgabenlisteDatabase.getInstance(this).getDatum(i);

        TextView datum = (TextView) findViewById(R.id.textViewDatum1);
        datum.setText(Datum);

        String Uhrzeit = AufgabenlisteDatabase.getInstance(this).getUhrzeit(i);

        TextView uhrzeit = (TextView) findViewById(R.id.textViewUhrzeit1);
        uhrzeit.setText(Uhrzeit);

        String Prioritaet = AufgabenlisteDatabase.getInstance(this).getPrioritaet(i);

        TextView prioritaet = (TextView) findViewById(R.id.textViewPrioritaet1);
        prioritaet.setText(Prioritaet);

        String Ort = AufgabenlisteDatabase.getInstance(this).getOrt(i);

        TextView ort = (TextView) findViewById(R.id.textViewOrt1);
        ort.setText(Ort);

        String Beschreibung = AufgabenlisteDatabase.getInstance(this).getBeschreibung(i);

        TextView beschreibung = (TextView) findViewById(R.id.textViewBeschreibung1);
        beschreibung.setText(Beschreibung);

        String Ersteller = AufgabenlisteDatabase.getInstance(this).getErsteller(i);

        TextView ersteller = (TextView) findViewById(R.id.textViewErsteller1);
        ersteller.setText(Ersteller);
    }
}
