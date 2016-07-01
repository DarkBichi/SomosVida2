package com.example.lejm1.donacionsangre;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CrearCampana extends AppCompatActivity {


    FirebaseDatabase db;
    DatabaseReference myRef;
    FirebaseAuth auth;

    ProgressDialog pd;
    Context contexto;

    EditText txtnombreCampaña;
    EditText txtnombrePaciente;
    EditText txtfechaCampaña;
    EditText txtnoDonantes;
    EditText txtdescripcionCampaña;
    EditText txtnombreHospital;
    EditText txtdireccionHospital;
    Spinner spinner;


    String nombreCampaña;
    String nombrePaciente;
    String fechaCampaña;
    String noDonantes;
    String descripcionCampaña;
    String tipoSangre;
    String nombreHospital;
    String direccionHospital;
    String idusuario;


    void obtenerDatos(){
        nombreCampaña = txtnombreCampaña.getText().toString();
        nombrePaciente = txtnombrePaciente.getText().toString();
        noDonantes=txtnoDonantes.getText().toString();
        descripcionCampaña=txtdescripcionCampaña.getText().toString();
        tipoSangre = spinner.getSelectedItem().toString();
        nombreHospital=txtnombreHospital.getText().toString();
        direccionHospital=txtdireccionHospital.getText().toString();
        idusuario=auth.getCurrentUser().getUid();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_campana);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contexto=this;
        db=FirebaseDatabase.getInstance();
        myRef=db.getReference("Campañas");
        auth=FirebaseAuth.getInstance();

        final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        txtnombreCampaña = (EditText) findViewById(R.id.txtCrearCNombreCamp);
        txtnombrePaciente = (EditText) findViewById(R.id.txtCrearCNombrePaciente);
        txtfechaCampaña = (EditText) findViewById(R.id.txtFechaCampCreacion);
        txtnoDonantes = (EditText) findViewById(R.id.txtCrearCNumeroDonantes);
        txtdescripcionCampaña = (EditText) findViewById(R.id.txtCrearCampDescripcion);
        txtnombreHospital = (EditText) findViewById(R.id.txtCrearCNombreHospital);
        txtdireccionHospital = (EditText) findViewById(R.id.txtCrearCampDatosHospital);
        txtfechaCampaña.setInputType(InputType.TYPE_NULL);
        txtfechaCampaña.requestFocus();
        txtfechaCampaña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar= Calendar.getInstance();
                DatePickerDialog escocherFecha = new DatePickerDialog(contexto, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year,monthOfYear,dayOfMonth);
                        String[] meses = getResources().getStringArray(R.array.meses);
                        String fecha = String.valueOf(dayOfMonth).concat(" de ").concat(meses[monthOfYear]).concat(" de ").concat(String.valueOf(year));
                        fechaCampaña =dateFormatter.format(newDate.getTime());
                        txtfechaCampaña.setText(fecha);
                    }
                },newCalendar.get(Calendar.YEAR),newCalendar.get(Calendar.MONTH),newCalendar.get(Calendar.DAY_OF_MONTH));
                escocherFecha.show();
            }
        });


        //Spinner para los estados
        spinner = (Spinner) findViewById(R.id.spinnerCrearCampTipoSangre);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipos_Sangre, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        Button crearCampaña = (Button) findViewById(R.id.btnCrearCamp);
        crearCampaña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd=ProgressDialog.show(CrearCampana.this,"Creando Campaña","Espere unos segundos");
                if(txtnombreCampaña.getText().toString().equals("")){
                    Toast.makeText(CrearCampana.this, "Debes ingresar un nombre para la campaña", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                    return;
                }
                if(txtnombrePaciente.getText().toString().equals("")){
                    Toast.makeText(CrearCampana.this, "Debes ingresar el nombre del paciente que necesita sangre", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                    return;
                }
                if(fechaCampaña.equals("")){
                    Toast.makeText(CrearCampana.this, "Debes ingresar una fecha limite para la campaña", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                    return;
                }
                if(txtnoDonantes.getText().toString().equals("")){
                    Toast.makeText(CrearCampana.this, "Debes ingresar el numero de donantes que requiere la campaña", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                    return;
                }
                if(txtdescripcionCampaña.getText().toString().equals("")){
                    Toast.makeText(CrearCampana.this, "Agrega una descripcion a tu campaña para motivar a los donantes", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                    return;
                }
                if(txtnombreHospital.getText().toString().equals("")){
                    Toast.makeText(CrearCampana.this, "Debes ingresar el nombre del hospital en el que se encuentra el paciente", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                    return;
                }
                if(txtdireccionHospital.getText().toString().equals("")){
                    Toast.makeText(CrearCampana.this, "Debes ingresar la direccion del hospital", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                    return;
                }

                obtenerDatos();
                registraCampaña();
            }
        });
    }



    void registraCampaña(){
        DatabaseReference campaña = myRef.child(nombreCampaña);
        campaña.child("nombrePaciente").setValue(nombrePaciente);
        campaña.child("fechaCampaña").setValue(fechaCampaña);
        campaña.child("noDonantes").setValue(noDonantes);
        campaña.child("descripcionCampaña").setValue(descripcionCampaña);
        campaña.child("tipoSangre").setValue(tipoSangre);
        campaña.child("nombreHospita").setValue(nombreHospital);
        campaña.child("direccionHospital").setValue(direccionHospital);
        campaña.child("IDCreador").setValue(idusuario);
        pd.dismiss();
        Toast.makeText(CrearCampana.this, "La Camapaña ha sido creada", Toast.LENGTH_LONG).show();
        Intent i = new Intent(CrearCampana.this,MenuPrincipal.class);
        startActivity(i);
    }

}
