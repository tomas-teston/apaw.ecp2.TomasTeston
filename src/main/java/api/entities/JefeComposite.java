package api.entities;

public class JefeComposite extends JefeComponent {

    private java.util.List<JefeComponent> listJefes;

    public JefeComposite(String name) {
        super(name);
        this.listJefes = new java.util.ArrayList<>();
    }

    @Override
    public String view() {
        return this.name;
    }

    @Override public void add(JefeComponent jefeComponent) {
        this.listJefes.add(jefeComponent);
    }

    @Override public void remove(JefeComponent jefeComponent) {
        this.listJefes.remove(jefeComponent);
    }

    @Override
    public boolean isComposite() {
        return true;
    }

    @Override
    public String toString() {
        return this.name.toLowerCase();
    }


}
