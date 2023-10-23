package com.example.iglesiaapp.controladores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iglesiaapp.R;
import com.example.iglesiaapp.modelos.CargosDato;
import com.example.iglesiaapp.modelos.CargosNegocio;
import com.example.iglesiaapp.modelos.UsuarioDato;
import com.example.iglesiaapp.modelos.UsuarioNegocio;

import java.util.ArrayList;
import java.util.List;

public class usuarioAddController extends AppCompatActivity {

    Button btn_guardar;
    EditText etnombre, etapellido ,etemail,etedad;

    Spinner spinnerCargo;

    List<CargosDato> listaCargo;
    UsuarioNegocio usuarioNegocio;

    CargosNegocio cargosNegocio=new CargosNegocio(this);

    int usuarioIdToEdit = -1; // Agrega un atributo para almacenar el ID del cargo a editar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_add_controller);
        etnombre = findViewById(R.id.etnombre);
        etapellido = findViewById(R.id.etapellido);
        etemail = findViewById(R.id.etemail);
        etedad = findViewById(R.id.etedad);
        spinnerCargo = findViewById(R.id.spinnerCargoUsuario);

        btn_guardar=findViewById(R.id.btn_guardar);

        usuarioNegocio=new UsuarioNegocio(usuarioAddController.this);

        //añádi esto
        // Verifica si se está en modo de edición (cargoIdToEdit diferente de -1)
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usuarioIdToEdit = extras.getInt("usuarioIdToEdit");
        }

        if (usuarioIdToEdit != -1) {
            // Modo de edición: Busca y muestra los datos del cargo a editar
            UsuarioDato usuarioToEdit = usuarioNegocio.obtenerPorId(usuarioIdToEdit);
            if (usuarioToEdit != null) {
                etnombre.setText(usuarioToEdit.getNombre());
                etapellido.setText(usuarioToEdit.getApellido());
                etemail.setText(usuarioToEdit.getEmail());


                etedad.setText(String.valueOf(usuarioToEdit.getEdad()));
            }


        }
        //hasta aqui
        List<CargosDato> listaCargos = cargosNegocio.listar();
        List<String> nombreCargos = new ArrayList<>();
        for (CargosDato cargo : listaCargos){
            nombreCargos.add(cargo.getNombre());
        }

        ArrayAdapter<String> adapterCategoria = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nombreCargos);
        spinnerCargo.setAdapter(adapterCategoria);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CargosDato cargoSeleccionado = listaCargos.get(spinnerCargo.getSelectedItemPosition());

                String nombre = etnombre.getText().toString();
                String apellido = etapellido.getText().toString();
                String correo=etemail.getText().toString();
                int edad= Integer.parseInt(etedad.getText().toString());

                int cargo_id = cargoSeleccionado.getId(); // Obtén el ID del cargo seleccionado

                UsuarioDato usuarioDato=new UsuarioDato(usuarioIdToEdit,nombre,apellido,correo,edad,cargo_id);
                if (usuarioIdToEdit == -1) {
                    // Modo de agregar: Agrega un nuevo cargo
                    usuarioNegocio.agregar(usuarioDato);
                    Toast.makeText(usuarioAddController.this, "Usuario Añadido", Toast.LENGTH_SHORT).show();
                } else {
                    // Modo de edición: Edita el cargo existente
                    usuarioNegocio.editar(usuarioDato);
                    Toast.makeText(usuarioAddController.this, "Usuario Editado", Toast.LENGTH_SHORT).show();
                }

                limpiar();


                //Toast.makeText(usuarioAddController.this, "Usuario Guardado", Toast.LENGTH_SHORT).show();
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