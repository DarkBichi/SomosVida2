package com.example.lejm1.donacionsangre;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Bienvenida extends AppCompatActivity {

    Button iniciarSecion, registrarse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        iniciarSecion = (Button) findViewById(R.id.btnOpcIniciarSesion);
        registrarse = (Button) findViewById(R.id.btnOpcRegistrarse);

        iniciarSecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i=new Intent(Bienvenida.this,Inicio_Sesion.class);
                startActivity(i);
            }
        });

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bienvenida.this,Registro_usuarios.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        System.exit(0);
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
