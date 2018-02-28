package com.example.cristobal.persistenciasqlite.SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cristobal.persistenciasqlite.VerListadoActivity;
import com.example.cristobal.persistenciasqlite.model.Ciudad;

import java.util.ArrayList;

/**
 * Created by cristobal on 13/02/2018.
 */

public class CiudadDataSource {

    private Context miContext;
    private CiudadSQLhelper miSqlHelper;
    SQLiteDatabase database;

    public CiudadDataSource(Context miContext) {
        this.miContext = miContext;
        this.miSqlHelper = new CiudadSQLhelper(miContext);

    }


    public SQLiteDatabase openReadable() {
        return miSqlHelper.getReadableDatabase();
    }
    public SQLiteDatabase openWriteable() {
        return miSqlHelper.getWritableDatabase();
    }

    public void close(SQLiteDatabase database) {
        database.close();
    }


    public long insertarCiudad(Ciudad ciudad) {
        //Abrimos la BD(database)
        SQLiteDatabase database = openWriteable();
        //Iniciamos la transacción
        database.beginTransaction();
        //Instanciamos clase esquema de la BD
        ContentValues ciudadValues = new ContentValues();
        //Añadimos lo que se debe insertar en la tabla (clave - valor)
        ciudadValues.put(CiudadBDContract.CiudadEntry.COLUMN_NAME,ciudad.getNombre());
        ciudadValues.put(CiudadBDContract.CiudadEntry.COLUMN_PROVINCIA,ciudad.getProvincia());
        ciudadValues.put(CiudadBDContract.CiudadEntry.COLUMN_HABITANTES,ciudad.getNumHabitantes());
        int idCiudad = (int) database.insert(CiudadBDContract.CiudadEntry.TABLE_NAME, null,ciudadValues);
        if(idCiudad != -1){
            database.setTransactionSuccessful();

        }
        database.endTransaction();
        //cerramos la base de datos
        close(database);
        //retornamos el IDContacto para un futuro uso
        return idCiudad;
    }

    public void modificarCiudad(Ciudad ciudad){
        //Abrimos la BD(database)
        SQLiteDatabase database = openWriteable();
        //Iniciamos la transacción
        database.beginTransaction();
        //Instanciamos clase esquema de la BD
        ContentValues ciudadValues = new ContentValues();
        //añadimos las modificaciones
        ciudadValues.put(CiudadBDContract.CiudadEntry.COLUMN_NAME,ciudad.getNombre());
        ciudadValues.put(CiudadBDContract.CiudadEntry.COLUMN_PROVINCIA,ciudad.getProvincia());
        ciudadValues.put(CiudadBDContract.CiudadEntry.COLUMN_HABITANTES,ciudad.getNumHabitantes());
        //modificamos
        String where1 = CiudadBDContract.CiudadEntry.COLUMN_ID + " = " + ciudad.getId();
        database.update(CiudadBDContract.CiudadEntry.TABLE_NAME, ciudadValues, where1, null);
        /*Otras formas
         database.update(ContactosDBContract.ContactoEntry.TABLE_NAME, contactoValues, String.format("%s=%d",
                ContactosDBContract.ContactoEntry.COLUMN_ID, contacto.getId()), null);

        String where2 = ContactosDBContract.ContactoEntry.COLUMN_ID + " = ?";
        String [] argd = {String.valueOf(contacto.getId())};
        database.update(ContactosDBContract.ContactoEntry.TABLE_NAME, contactoValues, where2, null);
        */
        database.setTransactionSuccessful();
        database.endTransaction();
        //cerramos la base de datos
        //close(database);
    }

    public void borrarCiudad(int idContacto){

        //Abrimos la BD(database)

        SQLiteDatabase database = openWriteable();
        //Iniciamos la transacción
        database.beginTransaction();


        String [] args = {String.valueOf(idContacto)};
        database.delete(CiudadBDContract.CiudadEntry.TABLE_NAME,
                CiudadBDContract.CiudadEntry.COLUMN_ID + "=?", args);

    /* Otra forma
        database.delete(CiudadBDContract.CiudadEntry.TABLE_NAME,
                CiudadBDContract.CiudadEntry.COLUMN_ID + " = "+ idContacto, null);
    */
        database.setTransactionSuccessful();
        database.endTransaction();
        //cerramos la base de datos
        database.close();


    }


    public Ciudad leerCiudades(int idCiud)
    {
        //Abrimos la Base de datos en modo lectura
        SQLiteDatabase database = openReadable();
        Ciudad ciudad = new Ciudad();

        //Ejecutamos consulta
        String sentencia = "SELECT "
                + CiudadBDContract.CiudadEntry.COLUMN_ID + ", "
                + CiudadBDContract.CiudadEntry.COLUMN_NAME + ", "
                + CiudadBDContract.CiudadEntry.COLUMN_PROVINCIA + ", "
                + CiudadBDContract.CiudadEntry.COLUMN_HABITANTES
                +" FROM ciudades WHERE"
                + CiudadBDContract.CiudadEntry.COLUMN_ID + " = " +idCiud ;
        Cursor miCursor = database.rawQuery(sentencia, null);
        //Recorremos el cursor
        String nombreCiudad, provincia, numHabitantes;


        if(miCursor.moveToFirst()){
            idCiud = miCursor.getInt(miCursor.getColumnIndex(CiudadBDContract.CiudadEntry.COLUMN_ID));
            nombreCiudad = miCursor.getString(miCursor.getColumnIndex(CiudadBDContract.CiudadEntry.COLUMN_NAME));
            provincia = miCursor.getString(miCursor.getColumnIndex(CiudadBDContract.CiudadEntry.COLUMN_PROVINCIA));
            numHabitantes = miCursor.getString(miCursor.getColumnIndex(CiudadBDContract.CiudadEntry.COLUMN_HABITANTES));
            ciudad = new Ciudad(idCiud,nombreCiudad,provincia,numHabitantes);
        }
        database.endTransaction();
        //cerramos la base de datos
        close(database);
        return ciudad;
    }

    public ArrayList<Ciudad> leer_Ciudades()
    {
        ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
        SQLiteDatabase database = openWriteable();

        String [] columnas = {
                CiudadBDContract.CiudadEntry.COLUMN_ID,
                CiudadBDContract.CiudadEntry.COLUMN_NAME,
                CiudadBDContract.CiudadEntry.COLUMN_PROVINCIA,
                CiudadBDContract.CiudadEntry.COLUMN_HABITANTES};
        Cursor miCursor = database.query(CiudadBDContract.CiudadEntry.TABLE_NAME, columnas,
                null, null,null,null,null);
        int idCiudad;
        String nombreCiudad, provincia, numHabitantes;
        Ciudad ciu = null;
        if (miCursor.moveToFirst()) {
            do {
                idCiudad = miCursor.getInt(miCursor.getColumnIndex(CiudadBDContract.CiudadEntry.COLUMN_ID));
                nombreCiudad = miCursor.getString(miCursor.getColumnIndex(CiudadBDContract.CiudadEntry.COLUMN_NAME));
                provincia = miCursor.getString(miCursor.getColumnIndex(CiudadBDContract.CiudadEntry.COLUMN_PROVINCIA));
                numHabitantes = miCursor.getString(miCursor.getColumnIndex(CiudadBDContract.CiudadEntry.COLUMN_HABITANTES));
                ciu = new Ciudad(idCiudad,nombreCiudad,provincia, numHabitantes);
                Log.e("id___________________: ",ciu.getId()+"");
                ciudades.add(ciu);
            } while (miCursor.moveToNext());
        }
        database.close();

        miCursor.close();

        return ciudades;
    }



}
