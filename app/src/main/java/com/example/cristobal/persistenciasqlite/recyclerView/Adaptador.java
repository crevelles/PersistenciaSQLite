package com.example.cristobal.persistenciasqlite.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cristobal.persistenciasqlite.R;
import com.example.cristobal.persistenciasqlite.model.Ciudad;

import java.util.ArrayList;

import static com.example.cristobal.persistenciasqlite.R.id.tvHaesbitant;

/**
 * Created by cristobal on 13/02/2018.
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.VHCiudad>implements View.OnClickListener{

    private ArrayList<Ciudad> ciudades;
    private View.OnClickListener listener;

    public Adaptador(ArrayList<Ciudad> ciudades) {
        super();
        this.ciudades = ciudades;
    }

    @Override
    public VHCiudad onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ciudad, parent, false);
        VHCiudad vhc = new VHCiudad(v);
        v.setOnClickListener(this);
        return vhc;
    }

    @Override
    public void onBindViewHolder(VHCiudad holder, int position) {
        holder.tvNombreCiudad.setText("Ciudad: "+ciudades.get(position).getNombre());
        holder.tvProvinvia.setText("Provincia: "+ciudades.get(position).getProvincia());
        holder.tvNumHabitantes.setText("Numero habitantes: "+ciudades.get(position).getNumHabitantes());
        holder.tvIdentificador.setText("Identificador: " + ciudades.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return ciudades.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }


    //creamos la clase interna
    public static class VHCiudad extends RecyclerView.ViewHolder {
        private TextView tvNombreCiudad;
        private TextView tvProvinvia;
        private  TextView tvNumHabitantes;
        private  TextView tvIdentificador;

        public VHCiudad(View itemView) {
            super(itemView);
            tvNombreCiudad = itemView.findViewById(R.id.tvNombreCiudad);
            tvProvinvia  = itemView.findViewById(R.id.tvProvincia);
            tvNumHabitantes = itemView.findViewById(R.id.tvHaesbitant);
            tvIdentificador = itemView.findViewById(R.id.idITEMidentificador);
        }

        public TextView getTvNombre() {
            return tvNombreCiudad;
        }

        public TextView getTvEmail() {
            return tvProvinvia;
        }

        public TextView getTvNumHabitantes() {
            return tvNumHabitantes;
        }

        public TextView getTvIdentificador() {
            return tvIdentificador;
        }
    }
}


