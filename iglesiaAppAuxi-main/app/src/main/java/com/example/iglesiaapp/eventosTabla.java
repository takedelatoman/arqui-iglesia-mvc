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

import com.example.iglesiaapp.controladores.eventosAddController;
import com.example.iglesiaapp.modelos.EventosDato;
import com.example.iglesiaapp.modelos.EventosNegocio;

import java.util.ArrayList;
import java.util.List;

public class eventosTabla extends AppCompatActivity {

    private TableLayout tableLayout;
    private String[]header={"ID","Nombre","fecha","Descripcion"};
    private ArrayList<String[]> filas=new ArrayList<>();

    EditText et_id;
    Context context;
    EventosNegocio eventosNegocio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_tabla);

        et_id = findViewById(R.id.et_id);

        tableLayout = (TableLayout) findViewById(R.id.tabla);

        TableDynamic tableDynamic=new TableDynamic(tableLayout,getApplicationContext());
        tableDynamic.addHeader(header);

        tableDynamic.addData(obtenerEventos());
        tableDynamic.createDataTable();

        tableDynamic.backgroundHeader(Color.DKGRAY);
        tableDynamic.backgroundData(Color.LTGRAY,Color.WHITE);
        tableDynamic.textColorData(Color.BLACK);
        tableDynamic.textColorHeader(Color.WHITE);
        tableDynamic.lineColor(Color.LTGRAY);
    }

    private ArrayList<String[]> obtenerEventos(){
        eventosNegocio=new EventosNegocio(eventosTabla.this);
        List<EventosDato> eventosDatoList=eventosNegocio.listar();
        for (EventosDato eventos:eventosDatoList){
            filas.add(new String[]{String.valueOf(eventos.getId()),eventos.getNombre(),eventos.getFecha(),eventos.getDescripcion()});
        }

        return filas;
    }

    public void onClick(View v) {
        et_id = findViewById(R.id.et_id);
        String idStr = et_id.getText().toString();
        if (!idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            //aqui no tocar este this
            eventosNegocio = new EventosNegocio(this);
            try {
                // Intenta eliminar el cargo
                eventosNegocio.eliminar(id);

                // Elimina el cargo de la lista 'filas'
                boolean found = false;
                for (int i = 0; i < filas.size(); i++) {
                    String[] evento = filas.get(i);
                    if (Integer.parseInt(evento[0]) == id) {
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

                    Toast.makeText(this, "Evento eliminado con éxito.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No se encontró un Evento con el ID especificado", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error al eliminar el Evento: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Por favor, ingrese un ID", Toast.LENGTH_SHORT).show();
        }
    }
    public void onClickEditar(View v) {
        et_id = findViewById(R.id.et_id);
        String idStr = et_id.getText().toString();
        if (!idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            // Abre la actividad de edición en modo de edición y pasa el ID del cargo a editar
            Intent editarIntent = new Intent(this, eventosAddController.class);
            editarIntent.putExtra("eventoIdToEdit", id);
            startActivity(editarIntent);
        } else {
            Toast.makeText(this, "Por favor, ingrese un ID", Toast.LENGTH_SHORT).show();
        }
    }
}