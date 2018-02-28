package com.example.cristobal.persistenciasqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cristobal.persistenciasqlite.SQLiteDB.CiudadDataSource;
import com.example.cristobal.persistenciasqlite.model.Ciudad;

import java.util.ArrayList;

public class NuevaCiudad extends AppCompatActivity {

    private EditText nombreCiudad, provincia, numHabitantes;
    private Button btnRegistroCiudad;
    private ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();

    CiudadDataSource cds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_ciudad);
        btnRegistroCiudad = findViewById(R.id.btnRegistrar);
        nombreCiudad = findViewById(R.id.etNombreCiudad);
        provincia = findViewById(R.id.etProvincia);
        numHabitantes = findViewById(R.id.etHabitantes);
        cds = new CiudadDataSource(this);

    }

    public void insertarCiudad(View view){
        Ciudad ciudad = new Ciudad(nombreCiudad.getText().toString(), provincia.getText().toString(),
                                        numHabitantes.getText().toString());
        int idCiudad = (int)cds.insertarCiudad(ciudad);


        if(idCiudad != -1){
            Toast.makeText(this, "Realizada la inserción correctamente",
                    Toast.LENGTH_LONG).show();
            nombreCiudad.setText(null);
            provincia.setText(null);
            numHabitantes.setText(null);
            Intent intent = new Intent(NuevaCiudad.this, VerListadoActivity.class);
            startActivity(intent);


        } else {
            Toast.makeText(this, "Error en la inserción",
                    Toast.LENGTH_LONG).show();
        }
    }




}
