package api.entities;

import java.util.HashMap;
import java.util.Map;

public final class EmpleadoReferencesFactory extends Observable {

    private static EmpleadoReferencesFactory empleadoReferencesFactory = new EmpleadoReferencesFactory();

    private Map<String, Empleado> references;

    private EmpleadoReferencesFactory() {
        this.references = new HashMap<>();
    }

    public static EmpleadoReferencesFactory getFactory() {
        return empleadoReferencesFactory;
    }

    public Empleado getReference(String id) {
        return references.get(id);
    }

    public Empleado setReference(Empleado empleado) {
        this.notifyObservers();
        return references.put(empleado.getId(), empleado);
    }

    public void removeReference(Integer key) {
        this.references.remove(key);
    }

}