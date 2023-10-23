package com.example.iglesiaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.iglesiaapp.controladores.cargosAddController;
import com.example.iglesiaapp.controladores.eventosAddController;
import com.example.iglesiaapp.controladores.usuarioAddController;
import com.example.iglesiaapp.controladores.invitadosAddController;


public class MainActivity extends AppCompatActivity {

    Button btn_gusuario,btn_tabla_usuario,btn_gcargos,btn_tabla_cargos,btn_geventos,btn_tabla_eventos,btn_ginvitados,btn_tabla_invitados;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_gusuario=findViewById(R.id.btn_gusuario);
        btn_tabla_usuario=findViewById(R.id.btn_tabla_usuario);
        btn_gcargos = findViewById(R.id.btn_gcargos); // Obtén una referencia al botón
        btn_tabla_cargos=findViewById(R.id.btn_tabla_cargos);

        btn_geventos = findViewById(R.id.btn_geventos); // Obtén una referencia al botón
        btn_tabla_eventos=findViewById(R.id.btn_tabla_eventos);

        btn_ginvitados = findViewById(R.id.btn_ginvitados); // Obtén una referencia al botón
        btn_tabla_invitados=findViewById(R.id.btn_tabla_invitados);

        btn_gusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, usuarioAddController.class);
                startActivity(intent);
            }
        });

        btn_tabla_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, usuarioTabla.class);
                startActivity(intent);
            }
        });
        btn_gcargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, cargosAddController.class);//Dirige hacia "para agregar un cargo"
                startActivity(intent);
            }
        });
        btn_tabla_cargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, cargosTabla.class);
                startActivity(intent);
            }
        });
        btn_geventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, eventosAddController.class);//Dirige hacia "para agregar un evento"
                startActivity(intent);
            }
        });
        btn_tabla_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, eventosTabla.class);
                startActivity(intent);
            }
        });
        btn_ginvitados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, invitadosAddController.class);//Dirige hacia "para agregar un evento"
                startActivity(intent);
            }
        });
        btn_tabla_invitados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, invitadosTabla.class);
                startActivity(intent);
            }
        });

    }


}