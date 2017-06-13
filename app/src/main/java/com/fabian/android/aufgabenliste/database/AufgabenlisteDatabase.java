package com.fabian.android.aufgabenliste.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fabian.android.aufgabenliste.model.Aufgabe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AufgabenlisteDatabase extends SQLiteOpenHelper
{
    public static AufgabenlisteDatabase INSTANCE = null;

    private static final String DB_NAME = "AUFGABENLISTE";
    private static final int VERSION = 3;
    private static final String TABLE_NAME = "Aufgabenliste";

    public static final String ID_COLUMN = "_id";
    public static final String AUFGABE_COLUMN = "Aufgabe";
    public static final String DATUM_COLUMN = "Datum";
    public static final String ORT_COLUMN = "Ort";
    public static final String ERSTELLER_COLUMN = "Ersteller";
    public static final String PRIORITAET_COLUMN = "Prioritaet";
    public static final String BESCHREIBUNG_COLUMN = "Beschreibung";
    public static final String ERLEDIGT_COLUMN = "Erledigt";
    public static final String ERLEDIGER_COLUMN = "Erlediger";
    public static final String DATUM_ERLEDIGT_COLUMN = "DatumErledigt";

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
                ORT_COLUMN + " TEXT, " +
                ERSTELLER_COLUMN + " TEXT, " +
                PRIORITAET_COLUMN + " TEXT, " +
                BESCHREIBUNG_COLUMN + " TEXT, " +
                ERLEDIGT_COLUMN + " INTEGER DEFAULT NULL, " +
                ERLEDIGER_COLUMN + " TEXT, " +
                DATUM_ERLEDIGT_COLUMN + " TEXT )";

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
        values.put (ORT_COLUMN, aufgabe.getOrt ());
        values.put (ERSTELLER_COLUMN, aufgabe.getErsteller ());
        values.put (PRIORITAET_COLUMN, aufgabe.getPrioritaet ());
        values.put (BESCHREIBUNG_COLUMN, aufgabe.getBeschreibung());
        values.put (ERLEDIGT_COLUMN, aufgabe.getErledigt ());
        values.put (ERLEDIGER_COLUMN, aufgabe.getErlediger ());
        values.put (DATUM_ERLEDIGT_COLUMN, aufgabe.getDatumErledigt ());

        long newId = database.insert (TABLE_NAME, null, values);

        database.close();

        return readAufgabe (newId);
    }

    public Aufgabe readAufgabe (final long id)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, new String[]{ID_COLUMN, AUFGABE_COLUMN, DATUM_COLUMN, ORT_COLUMN, ERSTELLER_COLUMN, PRIORITAET_COLUMN, BESCHREIBUNG_COLUMN},ID_COLUMN + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        Aufgabe aufgabe = null;

        if (cursor != null && cursor.getCount() > 0)
        {
            cursor.moveToFirst();
            aufgabe = new Aufgabe(cursor.getString(cursor.getColumnIndex (AUFGABE_COLUMN)), cursor.getString(cursor.getColumnIndex (DATUM_COLUMN)), cursor.getString(cursor.getColumnIndex (ORT_COLUMN)) ,cursor.getString (cursor.getColumnIndex (PRIORITAET_COLUMN)), cursor.getString (cursor.getColumnIndex(BESCHREIBUNG_COLUMN)));
            aufgabe.setId(cursor.getLong(cursor.getColumnIndex (ID_COLUMN)));
        }

        database.close();

        return aufgabe;
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
        return this.getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
