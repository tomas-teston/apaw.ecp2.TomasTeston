package api.entities;

public class Jefe{

    private String id;
    private String nombre;
    private int telefono;

    public Jefe(int id, String nombre, String telefono) {
        this.id = "0";
        this.nombre = nombre;
        this.telefono = 0;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTelefono() {
        return telefono;
    }
}
