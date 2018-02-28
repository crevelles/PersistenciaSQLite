package com.example.cristobal.persistenciasqlite.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cristobal.persistenciasqlite.VerListadoActivity;

import java.util.ArrayList;

/**
 * Created by cristobal on 13/02/2018.
 */

    public class Ciudad implements Parcelable{

    private String nombre;
    private String provincia;
    private String numHabitantes;
    private int id;
    private ArrayList<Ciudad> ciudades;
    private VerListadoActivity verList = new VerListadoActivity();

    public int getId() {
        return id;
    }




    public Ciudad(){}

    public Ciudad(int idCiudad, String nombre, String provincia, String numHabitantes) {
        this.id = idCiudad;
        this.nombre = nombre;
        this.provincia = provincia;
        this.numHabitantes = numHabitantes;
    }

    public Ciudad(String nombre, String provincia, String numHabitantes) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.numHabitantes = numHabitantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNumHabitantes() {
        return numHabitantes;
    }

    public void setNumHabitantes(String numHabitantes) {
        this.numHabitantes = numHabitantes;
    }

    protected Ciudad(Parcel in) {
        nombre = in.readString();
        provincia = in.readString();
        numHabitantes = in.readString();
        id = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(provincia);
        dest.writeString(numHabitantes);
        dest.writeInt(id);
    }


    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Ciudad> CREATOR = new Parcelable.Creator<Ciudad>() {
        @Override
        public Ciudad createFromParcel(Parcel in) {
            return new Ciudad(in);
        }

        @Override
        public Ciudad[] newArray(int size) {
            return new Ciudad[size];
        }
    };
}