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
            "edad INTEGER, "+
            "cargo_id INTEGER"+
            ")";
    private String tablaCargos="CREATE TABLE cargos(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT ,"+
           "nombre TEXT, "+
           "descripcion TEXT"+
           ")";


    private String tablaEventos="CREATE TABLE eventos(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            "nombre TEXT, "+
            "fecha TEXT, "+
            "descripcion TEXT, "+
            "usuario_id INTEGER"+
            ")";

    private String tablaInvitados="CREATE TABLE invitados(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT ,"+
            "nombre TEXT, "+
            "edad Integer, "+
            "fecha TEXT"+

            ")";



    public conexionDB(@Nullable Context context) {
        super(context, "iglesia.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(tablaUsuario);
        sqLiteDatabase.execSQL(tablaCargos);
        sqLiteDatabase.execSQL(tablaEventos);
        sqLiteDatabase.execSQL(tablaInvitados);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ tablaUsuario);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ tablaCargos);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ tablaEventos);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ tablaInvitados);
        onCreate(sqLiteDatabase);
    }

}
