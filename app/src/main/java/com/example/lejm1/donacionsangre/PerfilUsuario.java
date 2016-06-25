package com.example.lejm1.donacionsangre;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lejm1.donacionsangre.Datos.dtsUsuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilUsuario extends AppCompatActivity {

    FirebaseDatabase db;
    FirebaseAuth auth;
    DatabaseReference myRef;

    TextView nombre;
    TextView fecha_Nac;
    TextView tipo_Sangre;
    TextView numero_Donaciones;
    TextView sexo;
    TextView ciudad;
    TextView estado;
    TextView pais;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = FirebaseDatabase.getInstance();
        myRef=db.getReference("Usuarios");
        auth = FirebaseAuth.getInstance();

        nombre = (TextView) findViewById(R.id.etdatosNombre);
        fecha_Nac = (TextView) findViewById(R.id.etdatosFecha_Nac);
        tipo_Sangre = (TextView) findViewById(R.id.etdatosTipo_Sangre);
        numero_Donaciones = (TextView) findViewById(R.id.etdatosNumero_Donaciones);
        sexo = (TextView) findViewById(R.id.etdatosSexo);
        ciudad = (TextView) findViewById(R.id.etdatosCiudad);
        estado = (TextView) findViewById(R.id.etdatosEstado);
        pais = (TextView) findViewById(R.id.etdatosPais);
        email = (TextView) findViewById(R.id.etdatosEmail);
        Button cerrarSesion = (Button) findViewById(R.id.btncerrarSesion);



        FirebaseUser user = auth.getCurrentUser();
        DatabaseReference usuario = myRef.child(user.getUid());
        usuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dtsUsuario u = dataSnapshot.getValue(dtsUsuario.class);
                System.out.println();
                nombre.setText(u.getNombres().concat(" ").concat(u.getAp_paterno().concat(" ").concat(u.getAp_materno())));
                fecha_Nac.setText(u.getFecha_nac());
                tipo_Sangre.setText(u.getTipoSangre());
                sexo.setText(u.getSexo());
                ciudad.setText(u.getCiudad());
                estado.setText(u.getEstado());
                pais.setText(u.getPais());
                email.setText(u.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent i = new Intent(PerfilUsuario.this,MainActivity.class);
                startActivity(i);
            }
        });


    }

}
