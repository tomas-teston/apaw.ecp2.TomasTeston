package api.entities;

public class Nomina {
    private double salario;
    private boolean cobrada;

    public Nomina() {
        this.salario = 0.0;
        this.cobrada = false;
    }

    public Nomina(Double salario, boolean cobrada) {
        this.salario = salario;
        this.cobrada = cobrada;
    }

    public Double getSalario() {
        return salario;
    }

    public boolean isCobrada() {
        return this.cobrada;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setCobrada(boolean cobrada) {
        this.cobrada = cobrada;
    }



}
