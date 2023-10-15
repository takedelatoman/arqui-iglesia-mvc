package com.example.iglesiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.iglesiaapp.modelos.CargosDato;
import com.example.iglesiaapp.modelos.CargosNegocio;
import com.example.iglesiaapp.modelos.UsuarioDato;
import com.example.iglesiaapp.modelos.UsuarioNegocio;
//import com.example.iglesiaapp.modelos.UsuarioNegocio;

import java.util.ArrayList;
import java.util.List;

public class cargosTabla extends AppCompatActivity {

    private TableLayout tableLayout;
    private String[]header={"ID","Nombre","Descripcion"};
    private ArrayList<String[]> filas=new ArrayList<>();

    EditText et_id;
    Context context;
    CargosNegocio cargosNegocio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargos_tabla);

        et_id = findViewById(R.id.et_id);

        tableLayout = (TableLayout) findViewById(R.id.tabla);

        TableDynamic tableDynamic=new TableDynamic(tableLayout,getApplicationContext());
        tableDynamic.addHeader(header);

        tableDynamic.addData(obtenerCargos());
        tableDynamic.createDataTable();

        tableDynamic.backgroundHeader(Color.DKGRAY);
        tableDynamic.backgroundData(Color.LTGRAY,Color.WHITE);
        tableDynamic.textColorData(Color.BLACK);
        tableDynamic.textColorHeader(Color.WHITE);
        tableDynamic.lineColor(Color.LTGRAY);
    }

    private ArrayList<String[]> obtenerCargos(){
        cargosNegocio=new CargosNegocio(cargosTabla.this);
        List<CargosDato> cargosDatoList=cargosNegocio.listar();
        for (CargosDato cargos:cargosDatoList){
            filas.add(new String[]{String.valueOf(cargos.getId()),cargos.getNombre(),cargos.getDescripcion()});
        }

        return filas;
    }

    public void onClick(View v) {
        et_id = findViewById(R.id.et_id);
        String idStr = et_id.getText().toString();
        if (!idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            cargosNegocio = new CargosNegocio(context);
            try {
                // Intenta eliminar el cargo
                cargosNegocio.eliminar(id);

                // Elimina el cargo de la lista 'filas'
                boolean found = false;
                for (int i = 0; i < filas.size(); i++) {
                    String[] cargo = filas.get(i);
                    if (Integer.parseInt(cargo[0]) == id) {
                        filas.remove(i);
                        found = true;
                        break;
                    }
                }

                if (found) {
                    // Actualiza la tabla después de eliminar el cargo
                    TableLayout tableLayout = findViewById(R.id.tabla); // Encuentra la tabla por ID
                    TableDynamic tableDynamic = new TableDynamic(tableLayout, getApplicationContext());
                    tableDynamic.addHeader(header);
                    tableDynamic.addData(filas);
                    tableDynamic.createDataTable();
                    tableDynamic.backgroundHeader(Color.DKGRAY);
                    tableDynamic.backgroundData(Color.LTGRAY, Color.WHITE);
                    tableDynamic.textColorData(Color.BLACK);
                    tableDynamic.textColorHeader(Color.WHITE);
                    tableDynamic.lineColor(Color.LTGRAY);

                    Toast.makeText(this, "Cargo eliminado con éxito.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No se encontró un cargo con el ID especificado", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error al eliminar el cargo: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor, ingrese un ID", Toast.LENGTH_SHORT).show();
        }
    }
}
