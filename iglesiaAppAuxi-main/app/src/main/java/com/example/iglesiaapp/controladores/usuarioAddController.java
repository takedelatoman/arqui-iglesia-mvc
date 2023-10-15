package com.example.iglesiaapp.controladores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iglesiaapp.R;
import com.example.iglesiaapp.modelos.UsuarioDato;
import com.example.iglesiaapp.modelos.UsuarioNegocio;

public class usuarioAddController extends AppCompatActivity {

    Button btn_guardar;
    EditText etnombre, etapellido ,etemail,etedad;
    UsuarioNegocio usuarioNegocio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_add_controller);
        etnombre = findViewById(R.id.etnombre);
        etapellido = findViewById(R.id.etapellido);
        etemail = findViewById(R.id.etemail);
        etedad = findViewById(R.id.etedad);

        btn_guardar=findViewById(R.id.btn_guardar);

        usuarioNegocio=new UsuarioNegocio(usuarioAddController.this);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etnombre.getText().toString();
                String apellido = etapellido.getText().toString();
                String correo=etemail.getText().toString();
                int edad= Integer.parseInt(etedad.getText().toString());
                UsuarioDato usuarioDato=new UsuarioDato(-1,nombre,apellido,correo,edad);
                usuarioNegocio.agregar(usuarioDato);

                limpiar();
                Toast.makeText(usuarioAddController.this, "Usuario Guardado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void limpiar(){
        etemail.setText("");
        etnombre.setText("");
        etapellido.setText("");
        etedad.setText("");
    }
}