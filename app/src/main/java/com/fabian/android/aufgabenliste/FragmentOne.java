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

                intent.putExtra ("id", i);

                //zum Debuggen um die id anzuzeigen
                Toast.makeText(getActivity(), ID_EXTRA.toString(), Toast.LENGTH_LONG).show();

                startActivity(intent);
            }
        });

        return view;
    }
}
