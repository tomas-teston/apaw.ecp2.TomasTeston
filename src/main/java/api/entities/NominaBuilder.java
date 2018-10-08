package api.entities;

public class NominaBuilder {
    private static int reference = 1;

    private Nomina nomina;

    public NominaBuilder() {
        this.nomina = new Nomina();
    }

    public NominaBuilder salario(double salario) {
        this.nomina.setSalario(salario);
        return this;
    }

    public NominaBuilder cobrada(boolean cobrada) {
        this.nomina.setCobrada(cobrada);
        return this;
    }

    public Nomina build() {
        reference++;
        return this.nomina;
    }
}
