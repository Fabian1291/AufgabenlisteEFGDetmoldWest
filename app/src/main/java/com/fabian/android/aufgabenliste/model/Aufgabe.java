package com.fabian.android.aufgabenliste.model;

import java.util.Calendar;

public class Aufgabe
{
    private long id;
    private String Aufgabe;
    private Calendar Datum;
    private String Ort;
    private String Ersteller;
    private int Prioritaet;
    private String Beschreibung;
    private int Erledigt;
    private String Erlediger;
    private Calendar DatumErledigt;

    public Aufgabe (final String Aufgabe) {this (Aufgabe, null); }

    public Aufgabe (String Aufgabe, Calendar Datum)
    {
        this.Aufgabe = Aufgabe;
        this.Datum = Datum;
    }
}
