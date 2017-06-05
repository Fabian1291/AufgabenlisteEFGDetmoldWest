package com.fabian.android.aufgabenliste.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AufgabenlisteDatabase extends SQLiteOpenHelper
{
    public static AufgabenlisteDatabase INSTANCE = null;

    private static final String DB_NAME = "AUFGABENLISTE";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "Aufgebenliste";

    public final String ID_COLUMN = "_id";

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
        String createQuery = "CREATE TABLE " + TABLE_NAME + " (" + ID_COLUMN + " INTEGER PRIMARY KEY, )";

        sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        String dropTable = "DROP TABLE IF EXISTS" + TABLE_NAME;
        sqLiteDatabase.execSQL (dropTable);

        onCreate (sqLiteDatabase);
    }
}
