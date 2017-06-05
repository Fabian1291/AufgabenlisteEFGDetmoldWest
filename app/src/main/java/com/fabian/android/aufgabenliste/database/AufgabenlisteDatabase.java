package com.fabian.android.aufgabenliste.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fabian.android.aufgabenliste.model.Aufgabe;

public class AufgabenlisteDatabase extends SQLiteOpenHelper
{
    public static AufgabenlisteDatabase INSTANCE = null;

    private static final String DB_NAME = "AUFGABENLISTE";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "Aufgebenliste";

    public final String ID_COLUMN = "_id";
    public final String AUFGABE_COLUMN = "Aufgabe";
    public final String DATUM_COLUMN = "Datum";
    public final String ORT_COLUMN = "Ort";
    public final String ERSTELLER_COLUMN = "Ersteller";
    public final int PRIORITAET_COLUMN = 0;
    public final String BESCHREIBUNG_COLUMN = "Beschreibung";
    public final int ERLEDIGT_COLUMN = 0;
    public final String ERLEDIGER_COLUMN = "Erlediger";
    public final String DATUM_ERLEDIGT_COLUMN = "DatumErledigt";

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
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery = "CREATE TABLE " + TABLE_NAME +
                " (" + ID_COLUMN + " INTEGER PRIMARY KEY, " +
                AUFGABE_COLUMN + "TEXT NOT NULL" +
                DATUM_COLUMN + "TEXT NOT NULL" +
                ORT_COLUMN + "TEXT NOT NULL" +
                ERSTELLER_COLUMN + "TEXT NOT NULL" +
                PRIORITAET_COLUMN + "INTEGER DEFAULT NULL" +
                BESCHREIBUNG_COLUMN + "TEXT NOT NULL" +
                ERLEDIGT_COLUMN + "INTEGER DEFAULT NULL" +
                ERLEDIGER_COLUMN + "TEXT NOT NULL" +
                DATUM_ERLEDIGT_COLUMN + " TEXT NOT NULL )";

        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        String dropTable = "DROP TABLE IF EXISTS" + TABLE_NAME;
        sqLiteDatabase.execSQL (dropTable);

        onCreate (sqLiteDatabase);
    }

    public Aufgabe createAufgabe (final Aufgabe aufgabe)
    {
        SQLiteDatabase database = this.getWritableDatabase ();

        ContentValues values = new ContentValues ();
        values.put (AUFGABE_COLUMN, aufgabe.getAufgabe ());
        values.put (DATUM_COLUMN, aufgabe.getDatum () == null ? null : aufgabe.getDatum ().getTimeInMillis () / 1000);
        values.put (ORT_COLUMN, aufgabe.getOrt ());
        values.put (ERSTELLER_COLUMN, aufgabe.getErsteller ());
        values.put (String.valueOf(PRIORITAET_COLUMN), aufgabe.getPrioritaet ());
        values.put (BESCHREIBUNG_COLUMN, aufgabe.getBeschreibung());

        long newId = database.insert (TABLE_NAME, null, values);

        database.close();

        return readToDo (newId);
    }
}
