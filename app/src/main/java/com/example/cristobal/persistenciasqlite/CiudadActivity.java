package com.example.cristobal.persistenciasqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristobal.persistenciasqlite.SQLiteDB.CiudadDataSource;
import com.example.cristobal.persistenciasqlite.model.Ciudad;

public class CiudadActivity extends AppCompatActivity {

    EditText ETciudad, ETprovincia, ETnumHabitantes;
    Button btnGuardar, btnCancelar, btnVolver;
    String idCiudad;
    Ciudad ciudad = null;
    TextView newName, newProv, newNumHab, ideCiudad, ideCiu;
    CiudadDataSource cds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciudad);

        ETciudad = findViewById(R.id.idETciudad);
        ETprovincia = findViewById(R.id.idETProvincia);
        ETnumHabitantes = findViewById(R.id.idETHabitantes);
        btnCancelar = findViewById(R.id.idBTNcancelar);
        btnGuardar = findViewById(R.id.idBTNmodificar);
        btnVolver = findViewById(R.id.idVolverListado);
        ideCiu = findViewById(R.id.idTTXTidCiudad);


        ciudad = getIntent().getParcelableExtra("ciudad");

        //Log.d("id: ", ideCiuda+", nombreCiudad: "+ideCiuda);

        ETciudad.setText(ciudad.getNombre());
        ETprovincia.setText(ciudad.getProvincia());
        ETnumHabitantes.setText(ciudad.getNumHabitantes());
        ideCiu.setText("IDENTIFICADOR: "+(String.valueOf(ciudad.getId())));

    }

    public void volverListado(View view){
        Intent intent = new Intent(CiudadActivity.this, VerListadoActivity.class);
        startActivity(intent);
    }

    public void volverInicio(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void modificarCiudad(View view){
        CiudadDataSource cds = new CiudadDataSource(this);
        Ciudad ciu = new Ciudad(
                            ciudad.getId(),
                            ETciudad.getText().toString(),
                            ETprovincia.getText().toString(),
                            ETnumHabitantes.getText().toString());

        //Pasamos la ciudad al metocdo
        cds.modificarCiudad(ciu);
        if(ciudad.getId() == -1){
            Toast.makeText(this, "Error en la modificación", Toast.LENGTH_LONG).show();
            //idCiudad.setText(String.valueOf(ciu.getId()));
        } else{
            Toast.makeText(this, "La ciudad con ID: " + ciudad.getId()
                        + " se ha modificado", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(CiudadActivity.this, VerListadoActivity.class);
            startActivity(intent);
        }
    }

    public void borrar(View view){
        CiudadDataSource cds = new CiudadDataSource(this);
        int idOontacto = ciudad.getId();
        cds.borrarCiudad(idOontacto);
        ETciudad.setText(null);
        ETprovincia.setText(null);
        ETnumHabitantes.setText(null);
        Intent intent = new Intent(CiudadActivity.this, VerListadoActivity.class);
        startActivity(intent);

    }


}
