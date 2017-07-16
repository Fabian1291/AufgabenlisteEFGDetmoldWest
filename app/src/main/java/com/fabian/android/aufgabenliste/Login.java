package com.fabian.android.aufgabenliste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

        Intent intent = new Intent (this, MainActivity.class);

        EditText editText = (EditText) findViewById (R.id.editTextPasswort);

        String passwort = editText.getText ().toString ();

        if (passwort.equals ("test"))
        {
            startActivity(intent);
        }
        else
        {
            Toast.makeText (this, "Falsches Passwort", Toast.LENGTH_LONG).show();
        }
    }
}
