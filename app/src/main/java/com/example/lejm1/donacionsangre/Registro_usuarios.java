package com.example.lejm1.donacionsangre;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lejm1.donacionsangre.Datos.dtsUsuario;
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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class Registro_usuarios extends AppCompatActivity {

    private RadioGroup radioGroup;
    ConnectionClass connectionClass;

    EditText ape_paterno, ape_materno, nombre, fecha, mail, usuario, passwordd, ciudadOrigen;
    String tipoSexo = "";

    private RadioButton sound, vibration, silent;
    private Button btnRegistro;
    Spinner spinner, spinner2, spinner3;
    FirebaseDatabase db;
    DatabaseReference myRef;
    FirebaseAuth auth;
    Context contexto;

    Connection connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference("Usuarios");
        contexto=this;
        auth = FirebaseAuth.getInstance(db.getApp());

        //hola
     /*   try {
            connectionClass = new ConnectionClass();jk
            connect = connectionClass.CONN();hghgjhgcjcgj

        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }*/
        ape_paterno = (EditText) findViewById(R.id.txtRegistroApellidoPaterno);
        ape_materno = (EditText) findViewById(R.id.txtRegistroApellidoMaterno);
        nombre = (EditText) findViewById(R.id.txtRegistroNombres);
        fecha = (EditText) findViewById(R.id.txtFechaNacimiento);
        mail = (EditText) findViewById(R.id.txtRegistroCorreo);
        usuario = (EditText) findViewById(R.id.txtRegistroUsuario);
        passwordd = (EditText) findViewById(R.id.txtRegistroPassword);
        ciudadOrigen = (EditText) findViewById(R.id.txtRegistroCiudad);
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        fecha.setInputType(InputType.TYPE_NULL);
        fecha.requestFocus();

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog escojerFecha = new DatePickerDialog(contexto, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year,monthOfYear,dayOfMonth);
                        fecha.setText(dateFormatter.format(newDate.getTime()));
                    }
                },newCalendar.get(Calendar.YEAR),newCalendar.get(Calendar.MONTH),newCalendar.get(Calendar.DAY_OF_MONTH));
                escojerFecha.show();
            }
        });

        btnRegistro = (Button) findViewById(R.id.btnRegistrarUsuario);

        //Spinner para los estados
        spinner = (Spinner) findViewById(R.id.spinnerRegistroTipoSangre);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipos_Sangre, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Spinner para los estados
        spinner2 = (Spinner) findViewById(R.id.spinnerRegistroEstado);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.estados_republica, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

        //Spinner para los estados
        spinner3 = (Spinner) findViewById(R.id.spinnerRegistroPais);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.paises, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner3.setAdapter(adapter3);

        radioGroup = (RadioGroup) findViewById(R.id.rdioGroupRegistro);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.rdioBtnMasculino) {
                    tipoSexo = "M";
                } else if (checkedId == R.id.rdioBtnFemenino) {
                    tipoSexo = "F";
                }
            }
        });
        sound = (RadioButton) findViewById(R.id.rdioBtnMasculino);
        vibration = (RadioButton) findViewById(R.id.rdioBtnFemenino);


        /*btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registro registro = new Registro();
                registro.execute("");
            }
        });*/


    }

    String ap_paterno;
    String ap_materno;
    String nombres;
    String fecha_nac;
    String email;
    String userid;
    String password;
    String sexo;
    String tipoSangre;
    String ciudad;
    String estado;
    String pais;
    String id;


    void obtenerDatos() {
        ap_paterno = ape_paterno.getText().toString();
        ap_materno = ape_materno.getText().toString();
        nombres = nombre.getText().toString();
        fecha_nac = fecha.getText().toString();
        email = mail.getText().toString();
        userid = usuario.getText().toString();
        password = passwordd.getText().toString();
        sexo = tipoSexo;
        tipoSangre = spinner.getSelectedItem().toString();
        ciudad = ciudadOrigen.getText().toString();
        estado = spinner2.getSelectedItem().toString();
        pais = spinner3.getSelectedItem().toString();
    }

    public void registrarUsuario(View v) {
        //Registro registro = new Registro();
        //registro.execute("");
        //Intent i = new Intent(this, Bienvenida.class);
        //startActivity(i);
        obtenerDatos();


        if (ap_paterno.equals("")) {
            Toast.makeText(this, "Debe ingresar su Apellido Paterno", Toast.LENGTH_LONG).show();
            return;
        }
        if (ap_materno.equals("")) {
            Toast.makeText(this, "Debe ingresar su Apellido Materno", Toast.LENGTH_LONG).show();
            return;
        }
        if (nombres.equals("")) {
            Toast.makeText(this, "Debe ingresar su Nombre", Toast.LENGTH_LONG).show();
            return;
        }
        if (fecha_nac.equals("")) {
            Toast.makeText(this, "Debe ingresar su Fecha de nacimiento", Toast.LENGTH_LONG).show();
            return;
        }
        if (email.equals("")) {
            Toast.makeText(this, "Debe ingresar su Email", Toast.LENGTH_LONG).show();
            return;
        }
        if (userid.equals("")) {
            Toast.makeText(this, "Debe ingresar un Usuario", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.equals("")) {
            Toast.makeText(this, "Debe ingresar una contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        if (ciudad.equals("")) {
            Toast.makeText(this, "Debe ingresar una ciudad", Toast.LENGTH_LONG).show();
            return;
        }
        if(sexo.equals("")){
            Toast.makeText(this,"Debe seleccionar un sexo",Toast.LENGTH_LONG).show();
            return;
        }
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user!=null){
                        id=user.getUid();
                        DatabaseReference usuario = myRef.child(id);
                        registrar(usuario);
                    }


                }else{
                    String error = task.getException().toString();
                    Toast.makeText(contexto,error,Toast.LENGTH_LONG).show();
                }
            }
        });




    }


    public void registrar(DatabaseReference usuario) {
        usuario.child("nombres").setValue(nombres);
        usuario.child("ap_paterno").setValue(ap_paterno);
        usuario.child("ap_materno").setValue(ap_materno);
        usuario.child("email").setValue(email);
        usuario.child("user").setValue(userid);
        usuario.child("tipoSangre").setValue(tipoSangre);
        usuario.child("sexo").setValue(sexo);
        usuario.child("fecha_nac").setValue(fecha_nac);
        usuario.child("pais").setValue(pais);
        usuario.child("estado").setValue(estado);
        usuario.child("ciudad").setValue(ciudad);

        Toast.makeText(this, "Usuario Registradoo", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, Bienvenida.class);
        startActivity(i);
    }

    public class Registro extends AsyncTask<String, String, String> {
        String z = "";
        Boolean isSuccess = false;
        String ap_paterno = ape_paterno.getText().toString();
        String ap_materno = ape_materno.getText().toString();
        String nombres = nombre.getText().toString();
        String fecha_nac = fecha.getText().toString();
        String email = mail.getText().toString();
        String userid = usuario.getText().toString();
        String password = passwordd.getText().toString();
        String sexo = tipoSexo;
        String tipoSangre = spinner.getSelectedItem().toString();
        String ciudad = ciudadOrigen.getText().toString();
        String estado = spinner2.getSelectedItem().toString();
        String pais = spinner3.getSelectedItem().toString();

        @Override
        protected void onPostExecute(String r) {
            Toast.makeText(Registro_usuarios.this, r, Toast.LENGTH_SHORT).show();
            if (isSuccess) {
                Intent i = new Intent(Registro_usuarios.this, Bienvenida.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(Registro_usuarios.this, "Error",
                        Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            if (userid.trim().equals("") || password.trim().equals(""))
                z = "Por favor ingrese los datos";
            else {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        String query = "INSERT INTO USUARIOS_DATOS_GENERALES(APE_PAT,APE_MAT,NOMBRE,FECHA_NACIMIENTO,EMAIL,USUARIO,PASS,CIUDAD,ESTADO,PAIS,SEXO,TIPO_SANGRE) VALUES( " + ap_paterno + ", " + ap_materno + ", " + nombres + ", " + fecha_nac + ", " + email + ", " + usuario + ", " + password + ", " + ciudad + ", " + estado + ", " + pais + ", " + sexo + ", " + tipoSangre + ")";
                        Statement stmt = con.createStatement();
                        stmt.executeQuery(query);
//                        if(rs.next())
//                        {
//                            z = "Login successfull";
//                            isSuccess=true;
//                        }
//                        else
//                        {
//                            z = "Invalid Credentials";
//                            isSuccess = false;
//                        }
                    }
                } catch (Exception ex) {
                    isSuccess = false;
                    z = "Exceptions";
                }
            }
            return z;
        }
    }

}
