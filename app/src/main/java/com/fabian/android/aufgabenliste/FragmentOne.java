package com.fabian.android.aufgabenliste;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

        this.adapter = new AufgabenlisteAdapter (this, AufgabenlisteDatabase.getInstance (this).getAllAufgabenAsCursor ());
        this.listView.setAdapter (adapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, final int i, final long l)
            {
                Object element = adapterView.getAdapter().getItem(i);

                Log.e("ClickOnList", element.toString());

            }
        });

        private void refreshListView ()
        {
            adapter.changeCursor (AufgabenlisteDatabase.getInstance (this).getAllTodosAsCursor ());
        }

        return view;
    }
}
