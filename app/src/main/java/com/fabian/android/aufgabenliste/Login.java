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

        if (passwort.equals ("West210"))
        {
            startActivity(intent);
        }
        else
        {
            Toast.makeText (this, "Falsches Passwort", Toast.LENGTH_LONG).show();
        }
    }
}
