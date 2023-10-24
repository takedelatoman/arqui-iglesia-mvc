package com.example.iglesiaapp.modelos;



public class EventosDato {

    private int id;
    private String nombre;

    private String fecha;

    private String Descripcion;

    private int usuario_id;

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

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public EventosDato(int id, String nombre, String fecha, String descripcion,int usuario_id) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        Descripcion = descripcion;
        this.usuario_id = usuario_id;

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
