package com.fabian.android.aufgabenliste;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentTwo extends Fragment
{
    public FragmentTwo ()
    {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate (R.layout.fragment_two, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listviewtwo);

        return view;
    }
}
