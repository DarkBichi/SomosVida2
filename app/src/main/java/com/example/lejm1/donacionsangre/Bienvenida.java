package com.example.lejm1.donacionsangre;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Bienvenida extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

    }

    public void opcIniciarSesion(View v) {
        Intent i;
        i = new Intent(this, Inicio_Sesion.class);
        startActivity(i);

    }

    public void opcRegistroUsuarios(View v) {
        Intent i;
        i = new Intent(this, Registro_usuarios.class);
        startActivity(i);

    }
}
