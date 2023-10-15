package com.example.iglesiaapp.conexionDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class conexionDB extends SQLiteOpenHelper{

    private String tablaUsuario="CREATE TABLE usuario(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "nombre TEXT, "+
            "apellido TEXT, "+
            "email TEXT, "+
            "edad INTEGER "+
            ")";
    private String tablaCargos="CREATE TABLE cargos(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT ,"+
           "nombre TEXT, "+
           "descripcion TEXT"+
           ")";


    public conexionDB(@Nullable Context context) {
        super(context, "iglesia.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(tablaUsuario);
        sqLiteDatabase.execSQL(tablaCargos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ tablaUsuario);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ tablaCargos);
        onCreate(sqLiteDatabase);
    }

}
