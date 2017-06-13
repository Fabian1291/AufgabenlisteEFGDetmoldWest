package com.fabian.android.aufgabenliste.model;

import java.util.Calendar;

public class Aufgabe
{
    private long id;
    private String Aufgabe;
    private String Datum;
    private String Ort;
    private String Ersteller;
    private String Prioritaet;
    private String Beschreibung;
    private int Erledigt;
    private String Erlediger;
    private String DatumErledigt;

    public Aufgabe (String Aufgabe, String Datum, String Ort, String Prioritaet, String Beschreibung)
    {
        this.Aufgabe = Aufgabe;
        this.Datum = Datum;
        this.Ort = Ort;
        this.Prioritaet = Prioritaet;
        this.Beschreibung = Beschreibung;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAufgabe() {
        return Aufgabe;
    }

    public void setAufgabe(String aufgabe) {
        Aufgabe = aufgabe;
    }

    public String getDatum() {
        return Datum;
    }

    public void setDatum(String datum) {
        Datum = datum;
    }

    public String getOrt() {
        return Ort;
    }

    public void setOrt(String ort) {
        Ort = ort;
    }

    public String getErsteller() {
        return Ersteller;
    }

    public void setErsteller(String ersteller) {
        Ersteller = ersteller;
    }

    public String getPrioritaet() {
        return Prioritaet;
    }

    public void setPrioritaet(String prioritaet) {
        Prioritaet = prioritaet;
    }

    public String getBeschreibung() {
        return Beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        Beschreibung = beschreibung;
    }

    public int getErledigt() {
        return Erledigt;
    }

    public void setErledigt(int erledigt) {
        Erledigt = erledigt;
    }

    public String getErlediger() {
        return Erlediger;
    }

    public void setErlediger(String erlediger) {
        Erlediger = erlediger;
    }

    public String getDatumErledigt() {
        return DatumErledigt;
    }

    public void setDatumErledigt(String datumErledigt) {
        DatumErledigt = datumErledigt;
    }
}
