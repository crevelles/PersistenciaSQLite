package com.example.cristobal.persistenciasqlite.SQLiteDB;

import android.provider.BaseColumns;

/**
 * Created by cristobal on 13/02/2018.
 */

public class CiudadBDContract {

    public static abstract class CiudadEntry implements BaseColumns {

        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "NOMBRE";
        public static final String COLUMN_PROVINCIA = "PROVINCIA";
        public static final String COLUMN_HABITANTES = "HABITANTES";
        public static final String TABLE_NAME = "CIUDAD";
    }

}
