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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.toolbarLogin);
        setSupportActionBar (toolbar);
        getSupportActionBar().setTitle ("Log in");

        getSupportActionBar().setDisplayHomeAsUpEnabled (false);
    }

    public void login (View view)
    {
        Intent intent = new Intent (this, MainActivity.class);

        EditText editText = (EditText) findViewById (R.id.editTextPasswort);

        String passwort = editText.getText ().toString ();

        if (passwort.equals ("Test"))
        {
            startActivity(intent);
        }
        else
        {
            Toast.makeText (this, "Falsches Passwort", Toast.LENGTH_LONG).show();
        }
    }
}
