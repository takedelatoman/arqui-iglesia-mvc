package com.example.iglesiaapp.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

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
            cv.put("cargo_id",usuarioDato.getCargo_id());
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
                int cargo_id=cursor.getInt(5);
                //int edad = cursor.getInt(cursor.getColumnIndex("edad"));
                UsuarioDato newUsuario=new UsuarioDato(id,
                        nombre,
                        apellido,
                        email,
                        edad,
                        cargo_id
                        );
                list.add(newUsuario);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
    public void eliminar(int id) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(id)};

            // Verificar si el registro con el ID dado existe antes de eliminarlo
            Cursor cursor = bd.query("usuario", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor.getCount() > 0) {
                bd.delete("usuario", whereClause, whereArgs);
                Toast.makeText(context, "usuario Eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "No se encontró un usuario con el ID especificado", Toast.LENGTH_SHORT).show();
            }

            cursor.close();
            bd.close();
        }
    }
    public UsuarioDato obtenerPorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        UsuarioDato usuario = null;

        Cursor cursor = db.query("usuario", new String[]{"id", "nombre", "apellido","email","edad","cargo_id"},
                "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            usuario = new UsuarioDato(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5)
            );
        }

        cursor.close();
        db.close();
        return usuario;
    }

    public void editar(UsuarioDato usuarioDato) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", usuarioDato.getNombre());
            cv.put("apellido", usuarioDato.getApellido());
            cv.put("email", usuarioDato.getEmail());
            cv.put("edad", usuarioDato.getEdad());
            cv.put("cargo_id", usuarioDato.getCargo_id());

            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(usuarioDato.getId())};

            db.update("usuario", cv, whereClause, whereArgs);
            db.close();
        }
    }



}
