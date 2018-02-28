package com.example.cristobal.persistenciasqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnNuevaCiudad;
    Button btnVerListado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNuevaCiudad = findViewById(R.id.btnNuevaCiudad);
        btnVerListado = findViewById(R.id.btnVerCiudades);
    }

    public void nuevaCiudad(View view){
        Intent intent = new Intent(this,NuevaCiudad.class);
        startActivity(intent);
    }

    public void verListado(View view){
        Intent intent = new Intent(this,VerListadoActivity.class);
        startActivity(intent);
    }
}
