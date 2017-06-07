package com.fabian.android.aufgabenliste.database;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fabian.android.aufgabenliste.R;

import java.util.Calendar;

public class AufgabenlisteAdapter extends CursorAdapter
{
    public AufgabenlisteAdapter (final Context context, final Cursor cursor)
    {
        super (context, cursor, 0);
    }

    @Override
    public View newView (final Context context, final Cursor cursor, final ViewGroup viewGroup)
    {
        return LayoutInflater.from (context).inflate (R.layout.activity_overview_listitem, viewGroup, false);
    }

    @Override
    public void bindView (final View view, final Context context, final Cursor cursor)
    {
        ((TextView) view.findViewById (R.id.listitemAufgabe)).setText (cursor.getString (cursor.getColumnIndex (AufgabenlisteDatabase.AUFGABE_COLUMN)));
        ((TextView) view.findViewById (R.id.listitemDatum)).setText (cursor.getString (cursor.getColumnIndex (AufgabenlisteDatabase.DATUM_COLUMN)));
        ((TextView) view.findViewById (R.id.listitemOrt)).setText (cursor.getString (cursor.getColumnIndex (AufgabenlisteDatabase.ORT_COLUMN)));
    }
}
