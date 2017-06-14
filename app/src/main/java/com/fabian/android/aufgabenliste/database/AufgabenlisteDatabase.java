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
    private static final int VERSION = 5;
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
                PRIORITAET_COLUMN + " TEXT, " +
                BESCHREIBUNG_COLUMN + " TEXT, " +
                ERLEDIGT_COLUMN + " INTEGER DEFAULT NULL, " +
                ERLEDIGER_COLUMN + " TEXT, " +
                DATUM_ERLEDIGT_COLUMN + " TEXT, " +
                UHRZEIT_ERLEDIGT_COLUMN + " TEXT )";

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

        long newId = database.insert (TABLE_NAME, null, values);

        database.close();

        return readAufgabe (newId);
    }

    public Aufgabe readAufgabe (final long id)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, new String[]{ID_COLUMN, AUFGABE_COLUMN, DATUM_COLUMN, UHRZEIT_COLUMN, ORT_COLUMN, ERSTELLER_COLUMN, PRIORITAET_COLUMN, BESCHREIBUNG_COLUMN, ERLEDIGT_COLUMN, ERLEDIGER_COLUMN, DATUM_ERLEDIGT_COLUMN, UHRZEIT_ERLEDIGT_COLUMN},ID_COLUMN + " = ?", new String[]{String.valueOf(id)}, null, null, null);

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
                    cursor.getString (cursor.getColumnIndex (UHRZEIT_ERLEDIGT_COLUMN)));
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
        return this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ERLEDIGT_COLUMN + " = 0 " + "ORDER BY " + PRIORITAET_COLUMN + " DESC, " , null);
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
}
