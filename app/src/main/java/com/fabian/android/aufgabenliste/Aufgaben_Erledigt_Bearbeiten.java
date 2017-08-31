package com.fabian.android.aufgabenliste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Spinner;

import com.fabian.android.aufgabenliste.database.AufgabenlisteDatabase;

public class Aufgaben_Erledigt_Bearbeiten extends AppCompatActivity
{
    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_aufgaben_erledigt_bearbeiten);

        Intent intent = getIntent();
        long i = intent.getExtras().getLong("id");
        int spinneritem = 0;

        toolbar = (Toolbar) findViewById(R.id.toolbarAufgabenErledigtBearbeiten);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Aufgabe Bearbeiten");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String Aufgabe = AufgabenlisteDatabase.getInstance(this).getAufgabe(i);

        EditText aufgabe = (EditText) findViewById(R.id.editTextAufgabeErledigtBearbeiten);
        aufgabe.setText(Aufgabe);

        String Prioritaet = AufgabenlisteDatabase.getInstance(this).getPrioritaet(i);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerErledigtBearbeiten);
        if (Prioritaet.equals ("0-sehr niedrig"))
            spinneritem = 0;
        else if (Prioritaet.equals ("1-niedrig"))
            spinneritem = 1;
        else if (Prioritaet.equals ("2-normal"))
            spinneritem = 2;
        else if (Prioritaet.equals ("3-hoch"))
            spinneritem = 3;
        else if (Prioritaet.equals ("4-sehr hoch"))
            spinneritem = 4;
        else if (Prioritaet.equals ("5-dringend"))
            spinneritem = 5;

        /*zum debuggen
        String spinneritem2 = String.valueOf(spinneritem);
        Toast.makeText(this, spinneritem2, Toast.LENGTH_LONG).show();*/

        spinner.setSelection(spinneritem);

        String Ort = AufgabenlisteDatabase.getInstance(this).getOrt(i);

        EditText ort = (EditText) findViewById(R.id.editTextOrtErledigtBearbeiten);
        ort.setText(Ort);

        String Beschreibung = AufgabenlisteDatabase.getInstance(this).getBeschreibung(i);

        EditText beschreibung = (EditText) findViewById(R.id.editTextBeschreigungErledigtBearbeiten);
        beschreibung.setText(Beschreibung);

        String Ersteller = AufgabenlisteDatabase.getInstance(this).getErsteller(i);

        EditText ersteller = (EditText) findViewById(R.id.editTextNameErledigtBearbeiten);
        ersteller.setText(Ersteller);

        String Erlediger = AufgabenlisteDatabase.getInstance(this).getErlediger(i);

        EditText erlediger = (EditText) findViewById(R.id.editTextNameErledigerErledigtBearbeiten);
        erlediger.setText(Erlediger);
    }

    public void changesubmit ()
    {

    }
}
