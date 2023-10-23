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

import com.example.iglesiaapp.controladores.invitadosAddController;
import com.example.iglesiaapp.modelos.InvitadosDato;
import com.example.iglesiaapp.modelos.InvitadosNegocio;

import java.util.ArrayList;
import java.util.List;

public class invitadosTabla extends AppCompatActivity {

    private TableLayout tableLayout;
    private String[]header={"ID","Nombre","Edad","Fecha"};
    private ArrayList<String[]> filas=new ArrayList<>();

    EditText et_id;
    Context context;
    InvitadosNegocio invitadosNegocio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitados_tabla);

        et_id = findViewById(R.id.et_id);

        tableLayout = (TableLayout) findViewById(R.id.tabla);

        TableDynamic tableDynamic=new TableDynamic(tableLayout,getApplicationContext());
        tableDynamic.addHeader(header);

        tableDynamic.addData(obtenerInvitados());
        tableDynamic.createDataTable();

        tableDynamic.backgroundHeader(Color.DKGRAY);
        tableDynamic.backgroundData(Color.LTGRAY,Color.WHITE);
        tableDynamic.textColorData(Color.BLACK);
        tableDynamic.textColorHeader(Color.WHITE);
        tableDynamic.lineColor(Color.LTGRAY);
    }

    private ArrayList<String[]> obtenerInvitados(){
        invitadosNegocio=new InvitadosNegocio(invitadosTabla.this);
        List<InvitadosDato> invitadosDatoList=invitadosNegocio.listar();
        for (InvitadosDato invitados:invitadosDatoList){
            filas.add(new String[]{String.valueOf(invitados.getId()),invitados.getNombre(),String.valueOf(invitados.getEdad()),invitados.getFecha()});
        }

        return filas;
    }

    public void onClick(View v) {
        et_id = findViewById(R.id.et_id);
        String idStr = et_id.getText().toString();
        if (!idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            //aqui no tocar este this
            invitadosNegocio = new InvitadosNegocio(this);
            try {
                // Intenta eliminar el invitado
                invitadosNegocio.eliminar(id);

                // Elimina el invitado de la lista 'filas'
                boolean found = false;
                for (int i = 0; i < filas.size(); i++) {
                    String[] invitado = filas.get(i);
                    if (Integer.parseInt(invitado[0]) == id) {
                        filas.remove(i);
                        found = true;
                        break;
                    }
                }

                if (found) {
                    // Actualiza la tabla después de eliminar el invitado
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

                    Toast.makeText(this, "Invitado eliminado con éxito.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No se encontró un invitado con el ID especificado", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error al eliminar el invitado: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
            // Abre la actividad de edición en modo de edición y pasa el ID del invitado a editar
            Intent editarIntent = new Intent(this, invitadosAddController.class);
            editarIntent.putExtra("invitadoIdToEdit", id);
            startActivity(editarIntent);
        } else {
            Toast.makeText(this, "Por favor, ingrese un ID", Toast.LENGTH_SHORT).show();
        }
    }
}