package com.example.paneladmin;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BBDD extends SQLiteOpenHelper {

    String crear = "CREATE TABLE Usuario (id_usuario INTEGER PRIMARY KEY, usuario TEXT NOT NULL, contraseña TEXT NOT NULL, permisos INTEGER NOT NULL)";
    String crear2 = "CREATE TABLE Evento (id_evento INTEGER PRIMARY KEY, nombre TEXT NOT NULL, descripcion TEXT NOT NULL, foto blob NOT NULL)";
    String crear3 = "CREATE TABLE UsuarioxEvento (id_usuario_evento INTEGER PRIMARY KEY,id_usuario INTEGER NOT NULL, id_evento INTEGER NOT NULL, reserva INTEGER NOT NULL, " +
                    "FOREIGN KEY (id_usuario) REFERENCES Usuario (id_usuario), FOREIGN KEY(id_evento) REFERENCES Evento (id_evento))";

    public BBDD( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }




    @Override
    public void onCreate(SQLiteDatabase bbdd) {
        bbdd.execSQL(crear);
        bbdd.execSQL(crear2);
        bbdd.execSQL(crear3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bbdd, int versionantigua, int versionnueva) {

        bbdd.execSQL("DROP TABLE IF EXISTS Usuario");
        bbdd.execSQL(crear);

        bbdd.execSQL("DROP TABLE IF EXISTS Evento");
        bbdd.execSQL(crear2);

        bbdd.execSQL("DROP TABLE IF EXISTS UsuarioxEvento");
        bbdd.execSQL(crear3);
    }


    public static Boolean revisarPermiso(String usuario){
        SQLiteDatabase mibbdd = MainActivity.getMibbdd();
        Cursor c = mibbdd.rawQuery("SELECT permisos FROM Usuario WHERE usuario='" + usuario + "'" + "AND permisos ='" + "1'" , null);
        if(c.getCount() > 0)
            return true;
        else
            return false;
    }

    public static Boolean revisarUser(String usuario, String contraseña) {
        SQLiteDatabase mibbdd = MainActivity.getMibbdd();
        Cursor c = mibbdd.rawQuery("SELECT * FROM Usuario WHERE usuario='" + usuario + "'" + "AND contraseña='" + contraseña +"'", null);
        if(c.getCount()>0)
            return true;
        else
            return false;
    }

    public static Boolean revisarSiExiste(String usuario) {
        SQLiteDatabase mibbdd = MainActivity.getMibbdd();
        Cursor c = mibbdd.rawQuery("SELECT * FROM Usuario WHERE usuario='" + usuario + "'", null);
        if(c.getCount()>0)
            return true;
        else
            return false;
    }


}

