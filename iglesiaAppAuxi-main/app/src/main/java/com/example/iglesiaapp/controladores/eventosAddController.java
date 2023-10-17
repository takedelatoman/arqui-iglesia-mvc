package com.example.iglesiaapp.controladores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Toast;

import com.example.iglesiaapp.R;
import com.example.iglesiaapp.modelos.EventosDato;
import com.example.iglesiaapp.modelos.EventosNegocio;




public class eventosAddController extends AppCompatActivity {

    Button btn_guardar2;
    EditText etnombre2,etfecha2, etdescripcion2;

    EventosNegocio eventosNegocio;
    int eventoIdToEdit = -1; // Agrega un atributo para almacenar el ID del cargo a editar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_add_controller);

        etnombre2 = findViewById(R.id.etnombre2);
        etfecha2 = findViewById(R.id.etfecha2);
        etdescripcion2 = findViewById(R.id.etdescripcion2);


        btn_guardar2 = findViewById(R.id.btn_guardar2);

        eventosNegocio = new EventosNegocio(eventosAddController.this);

        // Verifica si se está en modo de edición (cargoIdToEdit diferente de -1)
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            eventoIdToEdit = extras.getInt("eventoIdToEdit");
        }

        if (eventoIdToEdit != -1) {
            // Modo de edición: Busca y muestra los datos del cargo a editar
            EventosDato eventoToEdit = eventosNegocio.obtenerPorId(eventoIdToEdit);
            if (eventoToEdit != null) {
                etnombre2.setText(eventoToEdit.getNombre());
                etfecha2.setText(eventoToEdit.getFecha());
                etdescripcion2.setText(eventoToEdit.getDescripcion());

            }
        }

        btn_guardar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etnombre2.getText().toString();
                String fecha = etfecha2.getText().toString();
                String descripcion = etdescripcion2.getText().toString();

                EventosDato eventosDato = new EventosDato(eventoIdToEdit, nombre, fecha,descripcion);

                if (eventoIdToEdit == -1) {
                    // Modo de agregar: Agrega un nuevo cargo
                    eventosNegocio.agregar(eventosDato);
                    Toast.makeText(eventosAddController.this, "Evento Añadido", Toast.LENGTH_SHORT).show();
                } else {
                    // Modo de edición: Edita el cargo existente
                    eventosNegocio.editar( eventosDato);
                    Toast.makeText( eventosAddController.this, " Evento Editado", Toast.LENGTH_SHORT).show();
                }

                limpiar();
            }
        });
    }


    public void limpiar() {
        etnombre2.setText("");
        etfecha2.setText("");
        etdescripcion2.setText("");
    }
}