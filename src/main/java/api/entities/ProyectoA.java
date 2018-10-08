package api.entities;

import java.time.LocalDateTime;

public class ProyectoA implements ProyectoStrategy{
    private String id;
    private LocalDateTime fecha;
    private String descripcion;

    public ProyectoA(String descripcion) {
        this.id = "0";
        this.fecha = null;
        this.descripcion = descripcion;
    }

    public String getId() {
        return this.id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override public String descripcion() {
        return this.getDescripcion();
    }
}
