package api.entities;

public abstract class JefeComponent {

    protected String name;

    public JefeComponent (String name)
    {
        this.name = name;
    }


    public abstract void add(JefeComponent jefeComponent);

    public abstract void remove(JefeComponent jefeComponent);

    public abstract boolean isComposite();

    public abstract String view();


}
