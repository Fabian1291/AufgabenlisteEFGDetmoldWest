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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Version extends AppCompatActivity
{
    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstancesState)
    {
        super.onCreate (savedInstancesState);
        setContentView (R.layout.activity_version);

        toolbar = (Toolbar) findViewById(R.id.toolbarVersion);
        setSupportActionBar (toolbar);
        getSupportActionBar().setTitle ("Version");

        getSupportActionBar().setDisplayHomeAsUpEnabled (true);
    }
}
