//
// This file is part of Aufgabenliste.
//
// Copyright (C) 2017 Fabian Dollas <fabiandollas@web.de>
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//

package com.fabian.android.aufgabenliste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fabian.android.aufgabenliste.database.AufgabenlisteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Aufgaben_Bearbeiten extends AppCompatActivity
{
    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_aufgaben_bearbeiten);

        Intent intent = getIntent();
        long i = intent.getExtras().getLong("id");
        int spinneritem = 0;

        toolbar = (Toolbar) findViewById(R.id.toolbarAufgabenBearbeiten);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Aufgabe Bearbeiten");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String Aufgabe = AufgabenlisteDatabase.getInstance(this).getAufgabe(i);

        EditText aufgabe = (EditText) findViewById(R.id.editTextAufgabeBearbeiten);
        aufgabe.setText(Aufgabe);

        String Prioritaet = AufgabenlisteDatabase.getInstance(this).getPrioritaet(i);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerBearbeiten);
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

        EditText ort = (EditText) findViewById(R.id.editTextOrtBearbeiten);
        ort.setText(Ort);

        String Beschreibung = AufgabenlisteDatabase.getInstance(this).getBeschreibung(i);

        EditText beschreibung = (EditText) findViewById(R.id.editTextBeschreibungBearbeiten);
        beschreibung.setText(Beschreibung);

        String Ersteller = AufgabenlisteDatabase.getInstance(this).getErsteller(i);

        EditText ersteller = (EditText) findViewById(R.id.editTextNameBearbeiten);
        ersteller.setText(Ersteller);
    }

    public void changesubmit (View view)
    {
        Intent intent = getIntent();
        long i = intent.getExtras().getLong("id");

        Intent intentchange = new Intent(this, MainActivity.class);

        EditText editTextAufgabe = (EditText) findViewById (R.id.editTextAufgabeBearbeiten);
        String Aufgabe = editTextAufgabe.getText().toString();

        EditText editTextOrt = (EditText) findViewById (R.id.editTextOrtBearbeiten);
        String Ort = editTextOrt.getText().toString();

        EditText editTextErsteller = (EditText) findViewById (R.id.editTextNameBearbeiten);
        String Ersteller = editTextErsteller.getText().toString();

        String Prioritaet = AufgabenlisteDatabase.getInstance(this).getPrioritaet(i);

        EditText editTextBeschreibung = (EditText) findViewById (R.id.editTextBeschreibungBearbeiten);
        String Beschreibung = editTextBeschreibung.getText().toString();

        EditText editTextBearbeiter = (EditText) findViewById (R.id.editTextBearbeiterBearbeiten);
        String Bearbeiter = editTextBearbeiter.getText().toString();

        if (Aufgabe.length() == 0)
        {
            Toast.makeText(this,"Das Feld 'Aufgabe' darf nicht leer sein", Toast.LENGTH_LONG).show();
            return;
        }

        if (Ort.length() == 0)
        {
            Toast.makeText(this,"Das Feld 'Ort' darf nicht leer sein", Toast.LENGTH_LONG).show();
            return;
        }

        if (Ersteller.length() == 0)
        {
            Toast.makeText(this,"Das Feld 'Erstellt von' darf nicht leer sein", Toast.LENGTH_LONG).show();
            return;
        }

        if (Bearbeiter.length() == 0)
        {
            Toast.makeText(this,"Bitte das Feld 'Name' ausfüllen", Toast.LENGTH_LONG).show();
            return;
        }

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);

        DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
        Date today1 = Calendar.getInstance().getTime();
        String reportTime = df1.format(today1);

        AufgabenlisteDatabase database = AufgabenlisteDatabase.getInstance(Aufgaben_Bearbeiten.this);

        database.updateAufgabeBearbeiten(i, Aufgabe, Ort, Ersteller, Prioritaet, Beschreibung, null, reportDate, reportTime, Bearbeiter);

        startActivity(intentchange);
    }
}
