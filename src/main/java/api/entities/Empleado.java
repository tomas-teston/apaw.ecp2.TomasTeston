package api.entities;

import java.util.ArrayList;
import java.util.List;

public class Empleado {
    private String id;
    private String nombre;
    private int edad;

    private List<Nomina> nominas;
    private Departamento departamento;
    private Jefe jefe;

    public Empleado() {
        this.id = null;
        this.nombre = "";
        this.edad = 0;
        this.nominas = new ArrayList<>();
        this.departamento = null;
        this.jefe = null;

    }

    public Empleado(String id) {
        this();
        this.id = id;
    }

    public Empleado(String nombre, int edad, Departamento departamento, Jefe jefe) {
        this();
        this.nombre = nombre;
        this.edad = edad;
        this.departamento = departamento;
        this.jefe = jefe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Jefe getJefe() { return this.jefe; }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public List<Nomina> getNominas() {
        return nominas;
    }

}