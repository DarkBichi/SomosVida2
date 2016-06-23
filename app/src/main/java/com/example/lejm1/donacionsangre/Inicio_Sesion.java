package com.example.lejm1.donacionsangre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.lejm1.donacionsangre.Datos.dtsUsuario;

public class Inicio_Sesion extends AppCompatActivity {
    EditText txtusuario;
    EditText txtpassword;
    FirebaseDatabase db;
    DatabaseReference myRef;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio__sesion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db=FirebaseDatabase.getInstance();
        myRef=db.getReference("Usuarios");
        txtusuario = (EditText)findViewById(R.id.txtLoginUsuario);
        txtpassword = (EditText)findViewById(R.id.txtLoginPassword);
        contexto=this;


    }

    @Override
    public void onBackPressed() {
        Intent i;
        i = new Intent(this, Bienvenida.class);
        startActivity(i);
        finish();
    }

    public void irMenuPpalIniciaSesion(View v){

        String usuario = txtusuario.getText().toString();
        final String password = txtpassword.getText().toString();

        DatabaseReference user = myRef.child(usuario);
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dtsUsuario dts = new dtsUsuario();
                dts = dataSnapshot.getValue(dtsUsuario.class);
                try{
                    if(dts.getPassword().equals(password)){
                        Intent i = new Intent(contexto, MenuPrincipal.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(Inicio_Sesion.this, "Error Usuario o Contraseña Erroneos", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(Inicio_Sesion.this, "Error Usuario o Contraseña Erroneos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}
