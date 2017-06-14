package com.fabian.android.aufgabenliste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.fabian.android.aufgabenliste.database.AufgabenlisteDatabase;

public class Aufgaben_Erledigt extends AppCompatActivity
{
    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstancesState)
    {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_aufgaben_erledigt);

        Intent intent = getIntent();
        long i = intent.getExtras().getLong("id");

        toolbar = (Toolbar) findViewById(R.id.toolbarVersion);
        setSupportActionBar (toolbar);
        //getSupportActionBar().setTitle ("Aufgaben erledigt");

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String Aufgabe = AufgabenlisteDatabase.getInstance(this).getAufgabe(i);

        TextView aufgabe = (TextView) findViewById(R.id.textViewAufgabe1_erledigt);
        aufgabe.setText(Aufgabe);

        String Datum = AufgabenlisteDatabase.getInstance(this).getDatum(i);

        TextView datum = (TextView) findViewById(R.id.textViewDatum1_erledigt);
        datum.setText(Datum);

        String Uhrzeit = AufgabenlisteDatabase.getInstance(this).getUhrzeit(i);

        TextView uhrzeit = (TextView) findViewById(R.id.textViewUhrzeit1_erledigt);
        uhrzeit.setText(Uhrzeit);

        String Prioritaet = AufgabenlisteDatabase.getInstance(this).getPrioritaet(i);

        TextView prioritaet = (TextView) findViewById(R.id.textViewPrioritaet1_erledigt);
        prioritaet.setText(Prioritaet);

        String Ort = AufgabenlisteDatabase.getInstance(this).getOrt(i);

        TextView ort = (TextView) findViewById(R.id.textViewOrt1_erledigt);
        ort.setText(Ort);

        String Beschreibung = AufgabenlisteDatabase.getInstance(this).getBeschreibung(i);

        TextView beschreibung = (TextView) findViewById(R.id.textViewBeschreibung1_erledigt);
        beschreibung.setText(Beschreibung);

        String Ersteller = AufgabenlisteDatabase.getInstance(this).getErsteller(i);

        TextView ersteller = (TextView) findViewById(R.id.textViewErsteller1_erledigt);
        ersteller.setText(Ersteller);

        String Erlediger = AufgabenlisteDatabase.getInstance(this).getErlediger(i);

        TextView erlediger = (TextView) findViewById(R.id.textViewErlediger1_erledigt);
        erlediger.setText(Erlediger);

        String Datum2 = AufgabenlisteDatabase.getInstance(this).getDatumErledigt(i);

        TextView datum2 = (TextView) findViewById(R.id.textViewDatum3_erledigt);
        datum2.setText(Datum2);

        String Uhrzeit2 = AufgabenlisteDatabase.getInstance(this).getUhrzeitErledigt(i);

        TextView uhrzeit2 = (TextView) findViewById(R.id.textViewUhrzeit3_erledigt);
        uhrzeit2.setText(Uhrzeit2);
    }
}
