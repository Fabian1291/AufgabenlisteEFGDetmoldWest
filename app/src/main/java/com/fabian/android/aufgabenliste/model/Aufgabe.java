package com.fabian.android.aufgabenliste.model;

public class Aufgabe
{
    private long id;
    private String Aufgabe;
    private String Datum;
    private String Uhrzeit;
    private String Ort;
    private String Ersteller;
    private String Prioritaet;
    private String Beschreibung;
    private int Erledigt;
    private String Erlediger;
    private String DatumErledigt;
    private String UhrzeitErldigt;
    private String DatumBearbeitet;
    private String UhrzeitBearbeitet;
    private String Bearbeiter;

    public Aufgabe (String Aufgabe, String Datum, String Uhrzeit, String Ort, String Ersteller, String Prioritaet, String Beschreibung, int Erledigt, String Erlediger, String DatumErledigt, String UhrzeitErledigt, String DatumBearbeitet, String UhrzeitBearbeitet, String Bearbeiter)
    {
        this.Aufgabe = Aufgabe;
        this.Datum = Datum;
        this.Uhrzeit = Uhrzeit;
        this.Ort = Ort;
        this.Ersteller = Ersteller;
        this.Prioritaet = Prioritaet;
        this.Beschreibung = Beschreibung;
        this.Erledigt = Erledigt;
        this.Erlediger = Erlediger;
        this.DatumErledigt = DatumErledigt;
        this.UhrzeitErldigt = UhrzeitErledigt;
        this.DatumBearbeitet = DatumBearbeitet;
        this.UhrzeitBearbeitet = UhrzeitBearbeitet;
        this.Bearbeiter = Bearbeiter;
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

    public String getUhrzeit() {
        return Uhrzeit;
    }

    public void setUhrzeit(String uhrzeit) {
        Uhrzeit = uhrzeit;
    }

    public String getUhrzeitErldigt() {
        return UhrzeitErldigt;
    }

    public void setUhrzeitErldigt(String uhrzeitErldigt) {
        UhrzeitErldigt = uhrzeitErldigt;
    }

    public String getDatumBearbeitet() {
        return DatumBearbeitet;
    }

    public void setDatumBearbeitet(String datumBearbeitet) {
        DatumBearbeitet = datumBearbeitet;
    }

    public String getUhrzeitBearbeitet() {
        return UhrzeitBearbeitet;
    }

    public void setUhrzeitBearbeitet(String uhrzeitBearbeitet) {
        UhrzeitBearbeitet = uhrzeitBearbeitet;
    }

    public String getBearbeiter() {
        return Bearbeiter;
    }

    public void setBearbeiter(String bearbeiter) {
        Bearbeiter = bearbeiter;
    }
}
