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

public class InvitadosNegocio extends conexionDB {

    Context context;
    public InvitadosNegocio(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public void agregar(InvitadosDato invitadosDato){
        SQLiteDatabase db=this.getWritableDatabase();
        if(db!=null){
            ContentValues cv=new ContentValues();
            cv.put("nombre",invitadosDato.getNombre());
            cv.put("edad",invitadosDato.getEdad());
            cv.put("fecha",invitadosDato.getFecha());
            db.insert("invitados",null,cv);
            db.close();
        }
    }

    public List<InvitadosDato> listar(){
        List<InvitadosDato> list = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM invitados",null);
        if (cursor.moveToFirst()){
            do {
                int id=cursor.getInt(0);
                String nombre=cursor.getString(1);
                int edad=cursor.getInt(2);
                String fecha=cursor.getString(3);
                InvitadosDato newInvitado=new InvitadosDato(id,
                        nombre,
                        edad,
                        fecha
                );

                list.add(newInvitado);
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
            Cursor cursor = bd.query("invitados", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor.getCount() > 0) {
                bd.delete("invitados", whereClause, whereArgs);
                Toast.makeText(context, "Invitado Eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "No se encontró un cargo con el ID especificado", Toast.LENGTH_SHORT).show();
            }

            cursor.close();
            bd.close();
        }
    }
    public InvitadosDato obtenerPorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        InvitadosDato invitado = null;

        Cursor cursor = db.query("invitados", new String[]{"id", "nombre", "edad","fecha"},
                "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            invitado = new InvitadosDato(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3)
            );
        }

        cursor.close();
        db.close();
        return invitado;
    }

    public void editar(InvitadosDato invitadosDato) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", invitadosDato.getNombre());
            cv.put("edad", invitadosDato.getEdad());
            cv.put("fecha", invitadosDato.getFecha());

            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(invitadosDato.getId())};

            db.update("invitados", cv, whereClause, whereArgs);
            db.close();
        }
    }
}
