package api.entities;

public class JefeLeaf extends JefeComponent {

    private Jefe jefe;

    public JefeLeaf(String name, Jefe jefe) {
        super(name);
        this.jefe = jefe;
    }

    public Jefe getJefe() {
        return this.jefe;
    }

    @Override public void add(JefeComponent jefeComponent) {
        throw new UnsupportedOperationException("Operación no soportada");
    }

    @Override public void remove(JefeComponent jefeComponent) {
        throw new UnsupportedOperationException("Operación no soportada");
    }

    @Override public boolean isComposite() {
        return false;
    }

    @Override public String view() {
        return this.jefe.getId();
    }
}
