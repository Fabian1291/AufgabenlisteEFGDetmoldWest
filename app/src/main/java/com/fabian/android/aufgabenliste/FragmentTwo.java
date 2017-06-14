package com.fabian.android.aufgabenliste;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fabian.android.aufgabenliste.database.AufgabenlisteAdapter;
import com.fabian.android.aufgabenliste.database.AufgabenlisteDatabase;

public class FragmentTwo extends Fragment
{
    private ListView listView;
    private AufgabenlisteAdapter adapter;

    public FragmentTwo ()
    {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate (R.layout.fragment_two, container, false);

        this.adapter = new AufgabenlisteAdapter(getActivity(), AufgabenlisteDatabase.getInstance (getActivity()).getAllAufgabenErledigtAsCursor ());
        this.listView.setAdapter (adapter);
        this.listView.setOnItemClickListener (new AdapterView.OnItemClickListener ()
        {
            @Override
            public void onItemClick (final AdapterView<?> adapterView, final View view, final int i, final long l)
            {
                Intent intent = new Intent(getActivity(), Aufgaben_Erledigt.class);

                intent.putExtra ("id", l);

                //zum Debuggen um die id anzuzeigen
                //Toast.makeText(getActivity(), ""+l, Toast.LENGTH_LONG).show();

                startActivity(intent);
            }
        });

        return view;
    }
}
