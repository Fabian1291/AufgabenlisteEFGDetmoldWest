package com.fabian.android.aufgabenliste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

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
