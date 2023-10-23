package com.example.iglesiaapp.modelos;

public class InvitadosDato {

    private int id;
    private String nombre;

    private int edad;

    private String fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public InvitadosDato(int id, String nombre, int edad, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "InvitadosDato{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
