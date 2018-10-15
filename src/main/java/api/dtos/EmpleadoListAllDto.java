package api.dtos;

import api.entities.Departamento;

public class EmpleadoListAllDto {

    private String id;
    private String nombre;
    private int edad;

    public EmpleadoListAllDto(String id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ThemeIdReferenceDto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad='" + edad + '\'' +
                '}';
    }
}
