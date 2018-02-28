package com.example.cristobal.persistenciasqlite.SQLiteDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cristobal on 13/02/2018.
 */

public class CiudadSQLhelper extends SQLiteOpenHelper{

    static final String DATABASE_NAME = "CiudadesDB";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_TABLE_CONTACTOS =
            "CREATE TABLE "+ CiudadBDContract.CiudadEntry.TABLE_NAME+ "( "+
                    CiudadBDContract.CiudadEntry.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                    CiudadBDContract.CiudadEntry.COLUMN_NAME+" TEXT NOT NULL," +
                    CiudadBDContract.CiudadEntry.COLUMN_PROVINCIA+" TEXT NOT NULL,"+
                    CiudadBDContract.CiudadEntry.COLUMN_HABITANTES+" TEXT NOT NULL);";

    public CiudadSQLhelper(Context contexto) {
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CiudadBDContract.CiudadEntry.TABLE_NAME);
        onCreate(db);
    }
}
