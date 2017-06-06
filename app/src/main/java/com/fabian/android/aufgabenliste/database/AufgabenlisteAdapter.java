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

        TextView dueDate = (TextView) view.findViewById (R.id.listitemDatum);

        if (cursor.isNull(cursor.getColumnIndex (AufgabenlisteDatabase.DATUM_COLUMN)))
        {
            dueDate.setVisibility(View.GONE);
        }
        else {
            dueDate.setVisibility (View.VISIBLE);
            Calendar calendar = Calendar.getInstance ();
            calendar.setTimeInMillis (cursor.getInt (cursor.getColumnIndex (AufgabenlisteDatabase.DATUM_COLUMN)) * 1000);
            dueDate.setText (String.valueOf (calendar.get (Calendar.YEAR)));
        }
    }
}
