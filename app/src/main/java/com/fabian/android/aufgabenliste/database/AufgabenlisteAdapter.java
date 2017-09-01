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

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fabian.android.aufgabenliste.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AufgabenlisteAdapter extends CursorAdapter
{
    public AufgabenlisteAdapter (final Context context, final Cursor cursor)
    {
        super (context, cursor, 0);
    }

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date today = Calendar.getInstance().getTime();
    String reportDate = df.format(today);

    @Override
    public View newView (final Context context, final Cursor cursor, final ViewGroup viewGroup)
    {
        return LayoutInflater.from (context).inflate (R.layout.activity_overview_listitem, viewGroup, false);
    }

    @Override
    public void bindView (final View view, final Context context, final Cursor cursor)
    {
        ((TextView) view.findViewById (R.id.listitemAufgabe)).setText (cursor.getString (cursor.getColumnIndex (AufgabenlisteDatabase.AUFGABE_COLUMN)));

        if (cursor.getString (cursor.getColumnIndex (AufgabenlisteDatabase.DATUM_COLUMN)).equals(reportDate))
        {
            ((TextView) view.findViewById (R.id.listitemDatum)).setText (cursor.getString (cursor.getColumnIndex (AufgabenlisteDatabase.UHRZEIT_COLUMN)));
        }
        else
        {
            ((TextView) view.findViewById (R.id.listitemDatum)).setText (cursor.getString (cursor.getColumnIndex (AufgabenlisteDatabase.DATUM_COLUMN)));
        }

        ((TextView) view.findViewById (R.id.listitemOrt)).setText (cursor.getString (cursor.getColumnIndex (AufgabenlisteDatabase.ORT_COLUMN)));
    }
}
