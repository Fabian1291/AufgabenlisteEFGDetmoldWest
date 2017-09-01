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

package com.fabian.android.aufgabenliste;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.fabian.android.aufgabenliste.database.AufgabenlisteAdapter;
import com.fabian.android.aufgabenliste.database.AufgabenlisteDatabase;

public class FragmentOne extends Fragment
{
    private ListView listView;
    private AufgabenlisteAdapter adapter;

    public FragmentOne ()
    {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate (R.layout.fragment_one, container, false);

        this.listView = (ListView) view.findViewById(R.id.listview);

        this.adapter = new AufgabenlisteAdapter (getActivity(), AufgabenlisteDatabase.getInstance (getActivity()).getAllAufgabenAsCursor ());
        this.listView.setAdapter (adapter);
        this.listView.setOnItemClickListener (new AdapterView.OnItemClickListener ()
        {
            @Override
            public void onItemClick (final AdapterView<?> adapterView, final View view, final int i, final long l)
            {
                Intent intent = new Intent(getActivity(), Aufgaben.class);

                intent.putExtra ("id", l);

                //zum Debuggen um die id anzuzeigen
                //Toast.makeText(getActivity(), ""+l, Toast.LENGTH_LONG).show();

                startActivity(intent);
            }
        });

        return view;
    }
}
