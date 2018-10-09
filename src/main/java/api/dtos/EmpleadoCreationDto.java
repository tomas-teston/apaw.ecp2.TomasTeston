package api.dtos;

import api.entities.Departamento;

public class EmpleadoCreationDto {

    private String nombre;
    private int edad;

    private String jefeId;

    private Departamento departamento;

    public EmpleadoCreationDto(String nombre, int edad, Departamento departamento, String jefeId) {
        this.nombre = nombre;
        this.edad = edad;
        this.departamento = departamento;
        this.jefeId = jefeId;
    }

    public String getJefeId() {
        return jefeId;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }
}
