package com.example.cristobal.persistenciasqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristobal.persistenciasqlite.SQLiteDB.CiudadDataSource;
import com.example.cristobal.persistenciasqlite.model.Ciudad;
import com.example.cristobal.persistenciasqlite.recyclerView.Adaptador;

import java.util.ArrayList;

public class VerListadoActivity extends AppCompatActivity{

    private RecyclerView rv;
    private CiudadDataSource cds;
    private ArrayList<Ciudad> ciudaades;
    private Adaptador adaptador;
    private LinearLayoutManager llm;
    private static   String texto = null;

    private TextView idItem;

    public ArrayList<Ciudad> getCiudaades() {
        return ciudaades;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_listado);
        rv = findViewById(R.id.idRV);
        cds = new CiudadDataSource(this);
        ciudaades = new ArrayList<Ciudad>();
        ciudaades = cds.leer_Ciudades();



        idItem = findViewById(R.id.idITEMidentificador);

        cargarRV();
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = ciudaades.get(rv.getChildAdapterPosition(v)).getId();
                String name = ciudaades.get(rv.getChildAdapterPosition(v)).getNombre();
                String pro = ciudaades.get(rv.getChildAdapterPosition(v)).getProvincia();
                String nH = ciudaades.get(rv.getChildAdapterPosition(v)).getNumHabitantes();

                Ciudad ci = new Ciudad(id, name, pro,nH);

                /*
                Ciudad ciudad = ciudaades.get(rv.getChildAdapterPosition(v));
                */
                Intent intent = new Intent(VerListadoActivity.this, CiudadActivity.class);
                intent.putExtra("ciudad",ci);
                startActivity(intent);
                finish();
            }
        });
    }

    public void cargarRV() {
        rv.setHasFixedSize(true);
        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        adaptador = new Adaptador(ciudaades);
        rv.setAdapter(adaptador);

    }


}
