package com.example.iglesiaapp.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.iglesiaapp.conexionDB.conexionDB;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventosNegocio extends conexionDB {

    Context context;
    public EventosNegocio(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public void agregar(EventosDato eventosDato){
        SQLiteDatabase db=this.getWritableDatabase();
        if(db!=null){
            ContentValues cv=new ContentValues();
            cv.put("nombre",eventosDato.getNombre());
            cv.put("fecha", eventosDato.getFecha());

            cv.put("descripcion",eventosDato.getDescripcion());
            cv.put("usuario_id",eventosDato.getUsuario_id());
            db.insert("eventos",null,cv);
            db.close();
        }
    }

    public List<EventosDato> listar(){
        List<EventosDato> list = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM eventos",null);
        if (cursor.moveToFirst()){
            do {
                int id=cursor.getInt(0);
                String nombre=cursor.getString(1);
                String fecha=cursor.getString(2);
                String descripcion=cursor.getString(3);
                int usuario_id=cursor.getInt(4);
                EventosDato newEvento=new EventosDato(id,
                        nombre,
                        fecha,
                        descripcion,
                        usuario_id
                );

                list.add(newEvento);
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
            Cursor cursor = bd.query("eventos", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor.getCount() > 0) {
                bd.delete("eventos", whereClause, whereArgs);
                Toast.makeText(context, "Evento Eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "No se encontró un Evento con el ID especificado", Toast.LENGTH_SHORT).show();
            }

            cursor.close();
            bd.close();
        }
    }
    public EventosDato obtenerPorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        EventosDato evento = null;

        Cursor cursor = db.query("eventos", new String[]{"id", "nombre", "fecha","descripcion","usuario_id"},
                "id = ?", new String[]{String.valueOf(id)}, null, null, null,null);

        if (cursor.moveToFirst()) {
            evento = new EventosDato(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4)
            );
        }

        cursor.close();
        db.close();
        return evento;
    }

    public void editar(EventosDato eventosDato) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", eventosDato.getNombre());
            cv.put("fecha", eventosDato.getFecha());
            cv.put("descripcion", eventosDato.getDescripcion());
            cv.put("usuario_id", eventosDato.getUsuario_id());

            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(eventosDato.getId())};

            db.update("eventos", cv, whereClause, whereArgs);
            db.close();
        }
    }
}
