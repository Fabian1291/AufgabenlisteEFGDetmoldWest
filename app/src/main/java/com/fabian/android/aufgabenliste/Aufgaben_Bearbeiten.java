package com.fabian.android.aufgabenliste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.fabian.android.aufgabenliste.database.AufgabenlisteDatabase;

public class Aufgaben_Bearbeiten extends AppCompatActivity
{
    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_aufgaben_bearbeiten);

        Intent intent = getIntent();
        long i = intent.getExtras().getLong("id");

        toolbar = (Toolbar) findViewById(R.id.toolbarAufgaben);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Aufgabe Bearbeiten");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String Aufgabe = AufgabenlisteDatabase.getInstance(this).getAufgabe(i);

        EditText aufgabe = (EditText) findViewById(R.id.editTextAufgabeBearbeiten);
        aufgabe.setText(Aufgabe);

        String Prioritaet = AufgabenlisteDatabase.getInstance(this).getPrioritaet(i);

        TextView prioritaet = (TextView) findViewById(R.id.textViewPrioritaet1);
        prioritaet.setText(Prioritaet);

        String Ort = AufgabenlisteDatabase.getInstance(this).getOrt(i);

        EditText ort = (EditText) findViewById(R.id.editTextOrtBearbeiten);
        ort.setText(Ort);

        String Beschreibung = AufgabenlisteDatabase.getInstance(this).getBeschreibung(i);

        EditText beschreibung = (EditText) findViewById(R.id.editTextBeschreigungBearbeiten);
        beschreibung.setText(Beschreibung);

        String Ersteller = AufgabenlisteDatabase.getInstance(this).getErsteller(i);

        EditText ersteller = (EditText) findViewById(R.id.editTextNameBearbeiten);
        ersteller.setText(Ersteller);
    }
}
