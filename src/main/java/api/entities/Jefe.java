package api.entities;

public class Jefe{

    private String id;
    private String nombre;
    private String telefono;

    public Jefe(String id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Jefe(String nombre, String telefono) {
        this(null, nombre, telefono);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
