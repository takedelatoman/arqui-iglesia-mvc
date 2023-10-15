package com.example.iglesiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TableLayout;

import com.example.iglesiaapp.modelos.UsuarioDato;
import com.example.iglesiaapp.modelos.UsuarioNegocio;

import java.util.ArrayList;
import java.util.List;

public class usuarioTabla extends AppCompatActivity {

    private TableLayout tableLayout;
    private String[]header={"ID","Nombre","Apellido","Correo","Edad"};
    private ArrayList<String[]> filas=new ArrayList<>();
    UsuarioNegocio usuarioNegocio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_tabla);
        tableLayout = (TableLayout) findViewById(R.id.tabla);

        TableDynamic tableDynamic=new TableDynamic(tableLayout,getApplicationContext());
        tableDynamic.addHeader(header);
        tableDynamic.addData(obtenerUsuarios());

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
}