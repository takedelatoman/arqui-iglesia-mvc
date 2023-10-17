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

import com.example.iglesiaapp.controladores.usuarioAddController;
import com.example.iglesiaapp.modelos.UsuarioDato;
import com.example.iglesiaapp.modelos.UsuarioNegocio;

import java.util.ArrayList;
import java.util.List;

public class usuarioTabla extends AppCompatActivity {

    private TableLayout tableLayout;
    private String[]header={"ID","Nombre","Apellido","Correo","Edad"};
    private ArrayList<String[]> filas=new ArrayList<>();
    EditText et_id;
    Context context;
    UsuarioNegocio usuarioNegocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_tabla);

        et_id = findViewById(R.id.et_id);

        tableLayout = (TableLayout) findViewById(R.id.tabla);

        TableDynamic tableDynamic=new TableDynamic(tableLayout,getApplicationContext());
        tableDynamic.addHeader(header);
        tableDynamic.addData(obtenerUsuarios());

        tableDynamic.createDataTable();

        tableDynamic.backgroundHeader(Color.DKGRAY);
        tableDynamic.backgroundData(Color.LTGRAY,Color.WHITE);
        tableDynamic.textColorData(Color.BLACK);
        tableDynamic.textColorHeader(Color.WHITE);
        tableDynamic.lineColor(Color.LTGRAY);
    }

    private ArrayList<String[]> obtenerUsuarios(){
        usuarioNegocio=new UsuarioNegocio(usuarioTabla.this);
        List<UsuarioDato> usuarioDatoList=usuarioNegocio.listar();
        for (UsuarioDato usuario:usuarioDatoList){
            filas.add(new String[]{String.valueOf(usuario.getId()),usuario.getNombre(),usuario.getApellido(),usuario.getEmail(),String.valueOf(usuario.getEdad())});
        }

        return filas;
    }
    public void onClick(View v) {
        et_id = findViewById(R.id.et_id);
        String idStr = et_id.getText().toString();
        if (!idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            //aqui no tocar este this
            usuarioNegocio = new UsuarioNegocio(this);
            try {
                // Intenta eliminar el cargo
                usuarioNegocio.eliminar(id);

                // Elimina el cargo de la lista 'filas'
                boolean found = false;
                for (int i = 0; i < filas.size(); i++) {
                    String[] usuario = filas.get(i);
                    if (Integer.parseInt(usuario[0]) == id) {
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

                    Toast.makeText(this, "Usuario eliminado con éxito.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No se encontró un usuario con el ID especificado", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error al eliminar el usuario: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
            Intent editarIntent = new Intent(this, usuarioAddController.class);
            editarIntent.putExtra("usuarioIdToEdit", id);
            startActivity(editarIntent);
        } else {
            Toast.makeText(this, "Por favor, ingrese un ID", Toast.LENGTH_SHORT).show();
        }
    }

}
