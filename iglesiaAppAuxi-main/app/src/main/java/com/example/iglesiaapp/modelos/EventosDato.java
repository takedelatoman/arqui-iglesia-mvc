package com.example.iglesiaapp.modelos;

import java.util.Date;

public class EventosDato {

    private int id;
    private String nombre;

    private String fecha;

    private String Descripcion;

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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public EventosDato(int id, String nombre, String fecha, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        Descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "EventosDato{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", fecha=" + fecha +
                ", Descripcion='" + Descripcion + '\'' +
                '}';
    }
}
