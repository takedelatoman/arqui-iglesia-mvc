package com.example.iglesiaapp.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.iglesiaapp.conexionDB.conexionDB;

import java.util.ArrayList;
import java.util.List;

public class UsuarioNegocio extends conexionDB {

    Context context;
    public UsuarioNegocio(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public void agregar(UsuarioDato usuarioDato){
        SQLiteDatabase db=this.getWritableDatabase();
        if(db!=null){
            ContentValues cv=new ContentValues();
            cv.put("nombre",usuarioDato.getNombre());
            cv.put("apellido",usuarioDato.getApellido());
            cv.put("email",usuarioDato.getEmail());
            cv.put("edad",usuarioDato.getEdad());
            db.insert("usuario",null,cv);
            db.close();
        }
    }

    public List<UsuarioDato> listar(){
        List<UsuarioDato> list = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM usuario",null);
        if (cursor.moveToFirst()){
            do {
                int id=cursor.getInt(0);
                String nombre=cursor.getString(1);
                String apellido=cursor.getString(2);
                String email=cursor.getString(3);
                int edad=cursor.getInt(4);
                //int edad = cursor.getInt(cursor.getColumnIndex("edad"));
                UsuarioDato newUsuario=new UsuarioDato(id,
                        nombre,
                        apellido,
                        email,
                        edad
                        );
                list.add(newUsuario);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }


}
