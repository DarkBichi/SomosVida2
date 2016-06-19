package com.example.lejm1.donacionsangre;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Inicio_Sesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio__sesion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onBackPressed() {
        Intent i;
        i = new Intent(this, Bienvenida.class);
        startActivity(i);
        finish();
    }

    public void irMenuPpalIniciaSesion(View v){
        Intent i = new Intent(this, MenuPrincipal.class);
        startActivity(i);
    }
}
