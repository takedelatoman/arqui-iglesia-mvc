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

public class CargosNegocio extends conexionDB {
    Context context;
    public CargosNegocio(@Nullable Context context) {
        super(context);
        this.context=context;
    }

    public void agregar(CargosDato cargosDato){
        SQLiteDatabase db=this.getWritableDatabase();
        if(db!=null){
            ContentValues cv=new ContentValues();
            cv.put("nombre",cargosDato.getNombre());
            cv.put("descripcion",cargosDato.getDescripcion());
            db.insert("cargos",null,cv);
            db.close();
        }
    }

    public List<CargosDato> listar(){
        List<CargosDato> list = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM cargos",null);
        if (cursor.moveToFirst()){
            do {
                int id=cursor.getInt(0);
                String nombre=cursor.getString(1);
                String descripcion=cursor.getString(2);
                CargosDato newCargo=new CargosDato(id,
                        nombre,
                        descripcion
                         );

                list.add(newCargo);
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
            Cursor cursor = bd.query("cargos", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor.getCount() > 0) {
                bd.delete("cargos", whereClause, whereArgs);
                Toast.makeText(context, "Cargo Eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "No se encontró un cargo con el ID especificado", Toast.LENGTH_SHORT).show();
            }

            cursor.close();
            bd.close();
        }
    }
    public CargosDato obtenerCargoPorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        CargosDato cargo = null;

        Cursor cursor = db.query("cargos", new String[]{"id", "nombre", "descripcion"},
                "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {
            cargo = new CargosDato(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
            );
        }

        cursor.close();
        db.close();
        return cargo;
    }
    public void editar(CargosDato cargosDato) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", cargosDato.getNombre());
            cv.put("descripcion", cargosDato.getDescripcion());

            String whereClause = "id = ?";
            String[] whereArgs = {String.valueOf(cargosDato.getId())};

            db.update("cargos", cv, whereClause, whereArgs);
            db.close();
        }
    }

}
