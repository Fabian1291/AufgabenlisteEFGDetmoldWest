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

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fabian.android.aufgabenliste.database.AufgabenlisteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

        toolbar = (Toolbar) findViewById(R.id.toolbarAufgaben);
        setSupportActionBar (toolbar);
        getSupportActionBar().setTitle ("Aufgaben");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        String Bearbeiter = AufgabenlisteDatabase.getInstance(this).getBearbeiter(i);

        TextView bearbeiter = (TextView) findViewById(R.id.textViewBearbeiter1);
        bearbeiter.setText(Bearbeiter);

        String DatumBearbeitet = AufgabenlisteDatabase.getInstance(this).getDatumBearbeitet(i);

        TextView datumBearbeitet = (TextView) findViewById(R.id.textViewDatumBearbeitet1);
        datumBearbeitet.setText(DatumBearbeitet);

        String UhrzeitBearbeitet = AufgabenlisteDatabase.getInstance(this).getUhrzeitBearbeitet(i);

        TextView uhrzeitBearbeitet = (TextView) findViewById(R.id.textViewUhrzeitBearbeitet1);
        uhrzeitBearbeitet.setText(UhrzeitBearbeitet);
    }

    public void update (View view)
    {
        Intent intent = getIntent();
        long i = intent.getExtras().getLong("id");

        Intent intentErledigt = new Intent(this, MainActivity.class);

        EditText editTextErlediger = (EditText) findViewById(R.id.editTextErlediger);
        String Erlediger = editTextErlediger.getText().toString();

        if (Erlediger.length() == 0)
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

        AufgabenlisteDatabase database = AufgabenlisteDatabase.getInstance(Aufgaben.this);

        database.updateAufgabe(i, Erlediger, reportDate, reportTime);

        startActivity(intentErledigt);
    }

    public void delete (View view)
    {
        Intent intent = getIntent ();
        long i = intent.getExtras ().getLong ("id");

        Intent intentLoeschen = new Intent (this, MainActivity.class);

        AufgabenlisteDatabase database = AufgabenlisteDatabase.getInstance(Aufgaben.this);

        database.deleteAufgabe (i);

        startActivity (intentLoeschen);
    }

    public void change1 (View view)
    {
        Intent intent = getIntent ();
        long i = intent.getExtras ().getLong ("id");

        Intent intentBearbeiten = new Intent (this, Aufgaben_Bearbeiten.class);

        intentBearbeiten.putExtra ("id", i);

        startActivity (intentBearbeiten);
    }
}
