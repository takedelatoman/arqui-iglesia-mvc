package com.example.iglesiaapp.controladores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iglesiaapp.R;
import com.example.iglesiaapp.modelos.InvitadosDato;
import com.example.iglesiaapp.modelos.InvitadosNegocio;


public class invitadosAddController extends AppCompatActivity {

        Button btn_guardar2;
        EditText etnombre2,etedad2,etfecha2;

        InvitadosNegocio invitadosNegocio;
        int invitadoIdToEdit = -1; // Agrega un atributo para almacenar el ID del cargo a editar

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_invitados_add_controller);

            etnombre2 = findViewById(R.id.etnombre2);
            etedad2 = findViewById(R.id.etedad2);
            etfecha2 = findViewById(R.id.etfecha2);



            btn_guardar2 = findViewById(R.id.btn_guardar2);

            invitadosNegocio = new InvitadosNegocio(invitadosAddController.this);

            // Verifica si se está en modo de edición (cargoIdToEdit diferente de -1)
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                invitadoIdToEdit = extras.getInt("eventoIdToEdit");
            }

            if (invitadoIdToEdit != -1) {
                // Modo de edición: Busca y muestra los datos del cargo a editar
                InvitadosDato eventoToEdit = invitadosNegocio.obtenerPorId(invitadoIdToEdit);
                if (eventoToEdit != null) {
                    etnombre2.setText(eventoToEdit.getNombre());
                    etedad2.setText(String.valueOf(eventoToEdit.getEdad()));
                    etfecha2.setText(eventoToEdit.getFecha());


                }
            }

            btn_guardar2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String nombre = etnombre2.getText().toString();
                    int edad= Integer.parseInt(etedad2.getText().toString());
                    String fecha = etfecha2.getText().toString();


                    InvitadosDato invitadosDato = new InvitadosDato(invitadoIdToEdit, nombre,edad, fecha);

                    if (invitadoIdToEdit == -1) {
                        // Modo de agregar: Agrega un nuevo cargo
                        invitadosNegocio.agregar(invitadosDato);
                        Toast.makeText(invitadosAddController.this, "Invitado Añadido", Toast.LENGTH_SHORT).show();
                    } else {
                        // Modo de edición: Edita el cargo existente
                        invitadosNegocio.editar(invitadosDato);
                        Toast.makeText( invitadosAddController.this, " Invitado Editado", Toast.LENGTH_SHORT).show();
                    }

                    limpiar();
                }
            });
        }


        public void limpiar() {
            etnombre2.setText("");
            etedad2.setText("");
            etfecha2.setText("");

        }
    }
