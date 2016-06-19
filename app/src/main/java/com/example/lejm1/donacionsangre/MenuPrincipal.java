package com.example.lejm1.donacionsangre;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void irCrearCampana(View v){
        Intent i;
        i = new Intent(this, CrearCampana.class);
        startActivity(i);
    }

    public void listaCampanias(View v){
        Intent i;
        i = new Intent(this, VerListacampanias.class);
        startActivity(i);
    }

    public void abrirPerfil(View v){
        Intent i;
        i = new Intent(this, PerfilUsuario.class);
        startActivity(i);
    }

    public void abrirDonaciones(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://www.paypal.com"));
        startActivity(intent);
    }


    }
