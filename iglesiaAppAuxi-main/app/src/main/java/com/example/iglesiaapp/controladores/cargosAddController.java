package com.example.iglesiaapp.controladores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iglesiaapp.R;
import com.example.iglesiaapp.modelos.CargosDato;
import com.example.iglesiaapp.modelos.CargosNegocio;

public class cargosAddController extends AppCompatActivity {
    Button btn_guardar1;
    EditText etnombre1, etdescripcion;
    CargosNegocio cargosNegocio;
    int cargoIdToEdit = -1; // Agrega un atributo para almacenar el ID del cargo a editar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargos_add_controller);
        etnombre1 = findViewById(R.id.etnombre1);
        etdescripcion = findViewById(R.id.etdescripcion);

        btn_guardar1 = findViewById(R.id.btn_guardar1);

        cargosNegocio = new CargosNegocio(cargosAddController.this);

        // Verifica si se está en modo de edición (cargoIdToEdit diferente de -1)
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cargoIdToEdit = extras.getInt("cargoIdToEdit");
        }

        if (cargoIdToEdit != -1) {
            // Modo de edición: Busca y muestra los datos del cargo a editar
            CargosDato cargoToEdit = cargosNegocio.obtenerPorId(cargoIdToEdit);
            if (cargoToEdit != null) {
                etnombre1.setText(cargoToEdit.getNombre());
                etdescripcion.setText(cargoToEdit.getDescripcion());
            }
        }

        btn_guardar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etnombre1.getText().toString();
                String descripcion = etdescripcion.getText().toString();
                CargosDato cargosDato = new CargosDato(cargoIdToEdit, nombre, descripcion);

                if (cargoIdToEdit == -1) {
                    // Modo de agregar: Agrega un nuevo cargo
                    cargosNegocio.agregar(cargosDato);
                    Toast.makeText(cargosAddController.this, "Cargo Añadido", Toast.LENGTH_SHORT).show();
                } else {
                    // Modo de edición: Edita el cargo existente
                    cargosNegocio.editar(cargosDato);
                    Toast.makeText(cargosAddController.this, "Cargo Editado", Toast.LENGTH_SHORT).show();
                }

                limpiar();
            }
        });
    }


    public void limpiar() {
        etnombre1.setText("");
        etdescripcion.setText("");
    }
}
