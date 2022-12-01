package codebeers.modelo;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Cliente {
    protected String nombre;
    protected String domicilio;
    protected String nif;
    protected String email;

    public Cliente(){} //Inicializa todos los valores a null.
    public Cliente(String nombre, String domicilio, String nif, String email) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.nif = nif;
        this.email = email;
    }
    public Cliente(Cliente cliente) {
        this.nombre = cliente.nombre;
        this.domicilio = cliente.domicilio;
        this.nif = cliente.nif;
        this.email = cliente.email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract String tipoCliente();
    public abstract float calcAnual();
    public abstract float descuentoEnv();

    @Override
    public String toString() {
        return "Cliente{" +
                "Nombre='" + nombre + '\'' +
                ", Domicilio='" + domicilio + '\'' +
                ", NIF='" + nif + '\'' +
                ", Email='" + email + '\'' + '}';
    }

    public Map<String, String> getDatosCliente() {

        Map<String, String> datosCliente = new LinkedHashMap<>();

        datosCliente.put("Nombre", getNombre());
        datosCliente.put("Domicilio", getDomicilio());
        datosCliente.put("Nif", getNif());
        datosCliente.put("Email", getEmail());
        datosCliente.put("Tipo", tipoCliente());
        datosCliente.put("Cuota", Float.toString(calcAnual()));

        return datosCliente;
    }
}
