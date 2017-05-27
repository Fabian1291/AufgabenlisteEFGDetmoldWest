package com.fabian.android.aufgabenliste;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentOne extends Fragment
{
    public FragmentOne ()
    {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate (R.layout.fragment_one, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listview);

        return view;
    }
}
