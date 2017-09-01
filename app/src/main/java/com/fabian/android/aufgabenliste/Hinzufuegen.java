package com.fabian.android.aufgabenliste;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fabian.android.aufgabenliste.database.AufgabenlisteDatabase;
import com.fabian.android.aufgabenliste.model.Aufgabe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
        getSupportActionBar().setTitle("Aufgabe Hinzufügen");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextAufgabe);
        EditText editText4 = (EditText) findViewById(R.id.editTextErsteller);
        EditText editText2 = (EditText) findViewById(R.id.editTextOrt);
        EditText editText3 = (EditText) findViewById(R.id.editTextBeschreibung);
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner);
        String Aufgabe = editText.getText().toString();
        String Ort = editText2.getText().toString();
        String Ersteller = editText4.getText().toString();
        String Beschreibung= editText3.getText().toString();
        String Prioritaet = String.valueOf(spinner1.getSelectedItem());

        if (Aufgabe.length() == 0)
        {
            Toast.makeText(this,"Bitte das Feld 'Aufgabe' ausfüllen", Toast.LENGTH_LONG).show();
            return;
        }

        if (Ersteller.length() == 0)
        {
            Toast.makeText(this,"Bitte das Feld 'Name' ausfüllen", Toast.LENGTH_LONG).show();
            return;
        }

        if (Ort.length() == 0)
        {
            Toast.makeText(this,"Bitte das Feld 'Ort' ausfüllen", Toast.LENGTH_LONG).show();
            return;
        }

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);

        DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
        Date today1 = Calendar.getInstance().getTime();
        String reportTime = df1.format(today1);

        AufgabenlisteDatabase database = AufgabenlisteDatabase.getInstance(Hinzufuegen.this);

        database.createAufgabe(new Aufgabe(Aufgabe, reportDate, reportTime, Ort, Ersteller, Prioritaet, Beschreibung, 0, null, null, null, null, null, null));

        startActivity(intent);
    }
}
