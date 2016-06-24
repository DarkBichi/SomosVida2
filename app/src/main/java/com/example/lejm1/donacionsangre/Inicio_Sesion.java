package com.example.lejm1.donacionsangre;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.lejm1.donacionsangre.Datos.dtsUsuario;

public class Inicio_Sesion extends AppCompatActivity {
    EditText txtemail;
    EditText txtpassword;
    FirebaseDatabase db;
    DatabaseReference myRef;
    FirebaseAuth auth;
    Context contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio__sesion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db=FirebaseDatabase.getInstance();
        myRef=db.getReference("Usuarios");
        auth = FirebaseAuth.getInstance();
        txtemail = (EditText)findViewById(R.id.txtLoginUsuario);
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

        String email = txtemail.getText().toString();
        final String password = txtpassword.getText().toString();

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                    String id = user.getUid();
                    System.out.println(id);
                    DatabaseReference userR = myRef.child(id);
                    userR.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            dtsUsuario u;
                            u=dataSnapshot.getValue(dtsUsuario.class);
                            Toast.makeText(Inicio_Sesion.this, "Bienvenido "+u.getUser(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                    Intent i = new Intent(Inicio_Sesion.this,MenuPrincipal.class);
                    startActivity(i);
                }else{
                    Toast.makeText(Inicio_Sesion.this, "Error en inicio de sesion", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}
