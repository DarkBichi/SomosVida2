package com.example.lejm1.donacionsangre;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Registro_usuarios extends AppCompatActivity {

    private RadioGroup radioGroup;
    ConnectionClass connectionClass;

    EditText ape_paterno, ape_materno, nombre, fecha, mail, usuario, passwordd, ciudadOrigen;
    String tipoSexo = "";

    private RadioButton sound, vibration, silent;
    private Button btnRegistro;
    Spinner spinner, spinner2, spinner3;

    Connection connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            connectionClass = new ConnectionClass();
            connect = connectionClass.CONN();
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }
        ape_paterno = (EditText) findViewById(R.id.txtRegistroApellidoPaterno);
        ape_materno = (EditText) findViewById(R.id.txtRegistroApellidoMaterno);
        nombre = (EditText) findViewById(R.id.txtRegistroNombres);
        fecha = (EditText) findViewById(R.id.txtFechaNacimiento);
        mail = (EditText) findViewById(R.id.txtRegistroCorreo);
        usuario = (EditText) findViewById(R.id.txtRegistroUsuario);
        passwordd =  (EditText) findViewById(R.id.txtRegistroPassword);
        ciudadOrigen = (EditText) findViewById(R.id.txtRegistroCiudad);

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
                if(checkedId == R.id.rdioBtnMasculino) {
                    tipoSexo = "M";
                } else if(checkedId == R.id.rdioBtnFemenino) {
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

    public void registrarUsuario(View v){
        Registro registro = new Registro();
        registro.execute("");
        //Intent i = new Intent(this, Bienvenida.class);
        //startActivity(i);
    }

    public class Registro extends AsyncTask<String,String,String>
    {
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
            Toast.makeText(Registro_usuarios.this,r,Toast.LENGTH_SHORT).show();
            if(isSuccess) {
                Intent i = new Intent(Registro_usuarios.this, Bienvenida.class);
                startActivity(i);
                finish();
            }
            else {
                Toast.makeText(Registro_usuarios.this, "Error",
                        Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected String doInBackground(String... params) {
            if(userid.trim().equals("")|| password.trim().equals(""))
                z = "Por favor ingrese los datos";
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Error in connection with SQL server";
                    } else {
                        String query = "INSERT INTO USUARIOS_DATOS_GENERALES(APE_PAT,APE_MAT,NOMBRE,FECHA_NACIMIENTO,EMAIL,USUARIO,PASS,CIUDAD,ESTADO,PAIS,SEXO,TIPO_SANGRE) VALUES( " + ap_paterno + ", " + ap_materno +", " + nombres + ", " + fecha_nac + ", " + email + ", " + usuario + ", " + password + ", " + ciudad + ", " + estado + ", " + pais + ", " + sexo + ", " + tipoSangre + ")";
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
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions";
                }
            }
            return z;
        }
    }

}
