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

package com.fabian.android.aufgabenliste.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fabian.android.aufgabenliste.model.Aufgabe;

import java.util.ArrayList;
import java.util.List;

public class AufgabenlisteDatabase extends SQLiteOpenHelper
{
    public static AufgabenlisteDatabase INSTANCE = null;

    private static final String DB_NAME = "AUFGABENLISTE";
    private static final int VERSION = 8;
    private static final String TABLE_NAME = "Aufgabenliste";

    public static final String ID_COLUMN = "_id";
    public static final String AUFGABE_COLUMN = "Aufgabe";
    public static final String DATUM_COLUMN = "Datum";
    public static final String UHRZEIT_COLUMN = "Uhrzeit";
    public static final String ORT_COLUMN = "Ort";
    public static final String ERSTELLER_COLUMN = "Ersteller";
    public static final String PRIORITAET_COLUMN = "Prioritaet";
    public static final String BESCHREIBUNG_COLUMN = "Beschreibung";
    public static final String ERLEDIGT_COLUMN = "Erledigt";
    public static final String ERLEDIGER_COLUMN = "Erlediger";
    public static final String DATUM_ERLEDIGT_COLUMN = "DatumErledigt";
    public static final String UHRZEIT_ERLEDIGT_COLUMN = "UhrzeitErledigt";
    public static final String DATUM_BEARBEITET_COLUMN = "DatumBearbeitet";
    public static final String UHRZEIT_BEARBEITET_COLUMN = "UhrzeitBearbeitet";
    public static final String BEARBEITER_COLUMN = "Bearbeiter";

    private AufgabenlisteDatabase (final Context context) {super(context, DB_NAME, null, VERSION);}

    public static AufgabenlisteDatabase getInstance (final Context context)
    {
        if (INSTANCE == null)
        {
            INSTANCE = new AufgabenlisteDatabase (context);
        }

        return INSTANCE;
    }

    @Override
    public void onCreate (SQLiteDatabase sqLiteDatabase)
    {
        String createQuery = "CREATE TABLE " + TABLE_NAME +
                " (" + ID_COLUMN + " INTEGER PRIMARY KEY, " +
                AUFGABE_COLUMN + " TEXT, " +
                DATUM_COLUMN + " TEXT, " +
                UHRZEIT_COLUMN + " TEXT, " +
                ORT_COLUMN + " TEXT, " +
                ERSTELLER_COLUMN + " TEXT, " +
                PRIORITAET_COLUMN + " TEXT, " + //TODO: in integer ändern
                BESCHREIBUNG_COLUMN + " TEXT, " +
                ERLEDIGT_COLUMN + " INTEGER DEFAULT NULL, " +
                ERLEDIGER_COLUMN + " TEXT, " +
                DATUM_ERLEDIGT_COLUMN + " TEXT, " +
                UHRZEIT_ERLEDIGT_COLUMN + " TEXT, " +
                DATUM_BEARBEITET_COLUMN + " TEXT, " +
                UHRZEIT_BEARBEITET_COLUMN + " TEXT, " +
                BEARBEITER_COLUMN + " TEXT )";

        sqLiteDatabase.execSQL (createQuery);
    }

    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        String dropTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL (dropTable);

        onCreate (sqLiteDatabase);
    }

    public Aufgabe createAufgabe (final Aufgabe aufgabe)
    {
        SQLiteDatabase database = this.getWritableDatabase ();

        ContentValues values = new ContentValues ();
        values.put (AUFGABE_COLUMN, aufgabe.getAufgabe ());
        values.put (DATUM_COLUMN, aufgabe.getDatum ());
        values.put (UHRZEIT_COLUMN, aufgabe.getUhrzeit());
        values.put (ORT_COLUMN, aufgabe.getOrt ());
        values.put (ERSTELLER_COLUMN, aufgabe.getErsteller ());
        values.put (PRIORITAET_COLUMN, aufgabe.getPrioritaet ());
        values.put (BESCHREIBUNG_COLUMN, aufgabe.getBeschreibung());
        values.put (ERLEDIGT_COLUMN, aufgabe.getErledigt ());
        values.put (ERLEDIGER_COLUMN, aufgabe.getErlediger ());
        values.put (DATUM_ERLEDIGT_COLUMN, aufgabe.getDatumErledigt ());
        values.put (UHRZEIT_ERLEDIGT_COLUMN, aufgabe.getUhrzeitErldigt());
        values.put (DATUM_BEARBEITET_COLUMN, aufgabe.getDatumBearbeitet ());
        values.put (UHRZEIT_BEARBEITET_COLUMN, aufgabe.getUhrzeitBearbeitet ());
        values.put (BEARBEITER_COLUMN, aufgabe.getBearbeiter ());

        long newId = database.insert (TABLE_NAME, null, values);

        database.close();

        return readAufgabe (newId);
    }

    public Aufgabe readAufgabe (final long id)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, new String[]{ID_COLUMN, AUFGABE_COLUMN, DATUM_COLUMN, UHRZEIT_COLUMN, ORT_COLUMN, ERSTELLER_COLUMN, PRIORITAET_COLUMN, BESCHREIBUNG_COLUMN, ERLEDIGT_COLUMN, ERLEDIGER_COLUMN, DATUM_ERLEDIGT_COLUMN, UHRZEIT_ERLEDIGT_COLUMN, DATUM_BEARBEITET_COLUMN, UHRZEIT_BEARBEITET_COLUMN, BEARBEITER_COLUMN},ID_COLUMN + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        Aufgabe aufgabe = null;

        if (cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            aufgabe = new Aufgabe (cursor.getString (cursor.getColumnIndex (AUFGABE_COLUMN)),
                    cursor.getString (cursor.getColumnIndex (DATUM_COLUMN)),
                    cursor.getString (cursor.getColumnIndex (UHRZEIT_COLUMN)),
                    cursor.getString (cursor.getColumnIndex (ORT_COLUMN)),
                    cursor.getString (cursor.getColumnIndex (ERSTELLER_COLUMN)),
                    cursor.getString (cursor.getColumnIndex (PRIORITAET_COLUMN)),
                    cursor.getString (cursor.getColumnIndex (BESCHREIBUNG_COLUMN)),
                    cursor.getInt (cursor.getColumnIndex (ERLEDIGT_COLUMN)),
                    cursor.getString (cursor.getColumnIndex (ERLEDIGER_COLUMN)),
                    cursor.getString (cursor.getColumnIndex (DATUM_ERLEDIGT_COLUMN)),
                    cursor.getString (cursor.getColumnIndex (UHRZEIT_ERLEDIGT_COLUMN)),
                    cursor.getString (cursor.getColumnIndex (DATUM_BEARBEITET_COLUMN)),
                    cursor.getString (cursor.getColumnIndex (UHRZEIT_BEARBEITET_COLUMN)),
                    cursor.getString (cursor.getColumnIndex (BEARBEITER_COLUMN)));
            aufgabe.setId(cursor.getLong(cursor.getColumnIndex (ID_COLUMN)));
        }

        database.close();

        return aufgabe;
    }

    public void updateAufgabe (long Id, String Erlediger, String DatumErledigt, String UhrzeitErledigt)
    {
        int id = (int) Id;

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put (ERLEDIGT_COLUMN, 1);
        values.put (ERLEDIGER_COLUMN, Erlediger);
        values.put (DATUM_ERLEDIGT_COLUMN, DatumErledigt);
        values.put (UHRZEIT_ERLEDIGT_COLUMN, UhrzeitErledigt);

        database.update(TABLE_NAME, values, "_id = " + id, null);

        database.close();
    }

    public void updateAufgabeBearbeiten (long Id, String Aufgabe, String Ort, String Ersteller, String Prioritaet, String Beschreibung, String Erlediger, String DatumBearbeitet, String UhrzeitBearbeitet, String Bearbeiter)
    {
        int id = (int) Id;

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put (AUFGABE_COLUMN, Aufgabe);
        values.put (ORT_COLUMN, Ort);
        values.put (ERSTELLER_COLUMN, Ersteller);
        values.put (PRIORITAET_COLUMN, Prioritaet);
        values.put (BESCHREIBUNG_COLUMN, Beschreibung);
        values.put (ERLEDIGER_COLUMN, Erlediger);
        values.put (DATUM_BEARBEITET_COLUMN, DatumBearbeitet);
        values.put (UHRZEIT_BEARBEITET_COLUMN, UhrzeitBearbeitet);
        values.put (BEARBEITER_COLUMN, Bearbeiter);

        database.update(TABLE_NAME, values, "_id = " + id, null);

        database.close();
    }

    public void updateAufgabeNichtErledigt (long Id)
    {
        int id = (int) Id;

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put (ERLEDIGT_COLUMN, 0);

        database.update(TABLE_NAME, values, "_id = " + id, null);

        database.close();
    }

    public void deleteAufgabe (long Id)
    {
        int id = (int) Id;

        SQLiteDatabase database = getWritableDatabase ();

        database.delete (TABLE_NAME, "_id = " + id, null);
    }

    public List<Aufgabe> readAllAufgaben ()
    {
        List<Aufgabe> todos = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase ();

        Cursor cursor = database.rawQuery ("SELECT * FROM " + TABLE_NAME, null);

        if(cursor.moveToFirst())
        {
            do{
                Aufgabe aufgabe = readAufgabe (cursor.getLong(cursor.getColumnIndex (ID_COLUMN)));
                if (aufgabe != null)
                {
                    todos.add (aufgabe);
                }
            }while (cursor.moveToNext());
        }

        database.close ();

        return todos;
    }

    public Cursor getAllAufgabenAsCursor ()
    {
        return this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ERLEDIGT_COLUMN + " = 0 " + "ORDER BY " + PRIORITAET_COLUMN + " DESC" , null);
    }

    public Cursor getAllAufgabenErledigtAsCursor ()
    {
        return this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ERLEDIGT_COLUMN + " = 1 " + "ORDER BY " + ID_COLUMN + " ASC", null);
    }

    public String getAufgabe (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{AUFGABE_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }

    public String getDatum (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{DATUM_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }

    public String getUhrzeit (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{UHRZEIT_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }

    public String getOrt (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{ORT_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }

    public String getErsteller (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{ERSTELLER_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }

    public String getPrioritaet (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{PRIORITAET_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }

    public String getBeschreibung (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{BESCHREIBUNG_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }

    public String getErledigt (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{ERLEDIGT_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }

    public String getErlediger (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{ERLEDIGER_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }

    public String getDatumErledigt (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{DATUM_ERLEDIGT_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }

    public String getUhrzeitErledigt (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{UHRZEIT_ERLEDIGT_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }

    public String getBearbeiter (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{BEARBEITER_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }

    public String getDatumBearbeitet (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{DATUM_BEARBEITET_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }

    public String getUhrzeitBearbeitet (long Id) {

        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query (TABLE_NAME, new String[]{UHRZEIT_BEARBEITET_COLUMN}, null, null, null, null, null);
        int id = (int) Id;
        cursor.move(id);

        String Aufgabe = cursor.getString(0);

        database.close();

        return Aufgabe;
    }
}
