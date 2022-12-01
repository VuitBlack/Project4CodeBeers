package codebeers.modelo;

public class Estandar extends Cliente {

    public Estandar() {
        super();
    }

    public Estandar(String nombre, String domicilio, String nif, String email) {
        super(nombre, domicilio, nif, email);
    }

    public Estandar(Estandar estandar) {
        super(estandar);
    }

    public String tipoCliente() {
        return "Estándar";
    }

    public float calcAnual() {
        return 0;
    }

    public float descuentoEnv() {
        return 0;
    }

    public String toString() {
        return "Estandar [tipoCliente()=" + tipoCliente() + ", calAnual()=" + calcAnual() + ", descuentoEnv()="
                + descuentoEnv() + ", getNombre()=" + getNombre() + ", getDomicilio()=" + getDomicilio() + ", getNif()="
                + getNif() + ", getEmail()=" + getEmail() + "]";
    }
}