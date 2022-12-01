package codebeers.modelo;

import java.util.HashMap;
import java.util.Map;

public class Premium extends Cliente {

    private float calcAnual;
    private float descuentoEnv;

    public Premium(String email, String nombre, String domicilio, String nif, float cuota, float descuentoEnvio) {
        super(email, nombre, domicilio, nif);
        this.calcAnual = cuota;
        this.descuentoEnv = descuentoEnvio;
    }

    public Premium(String email, String nombre, String domicilio, String nif) {
        super(email, nombre, domicilio, nif);
        this.calcAnual = 30.0f;
        this.descuentoEnv = 0.2f;
    }

    public Premium() {
        super();
    }

    public float getCalcAnual() {
        return calcAnual;
    }

    public float calcAnual() {
        return calcAnual;
    }

    public void setCalcAnual(float calcAnual) {
        this.calcAnual = calcAnual;
    }

    public float getDescuentoEnv() {
        return descuentoEnv;
    }
    public float descuentoEnv() {
        return descuentoEnv;
    }

    public void setDescuentoEnv(float descuentoEnv) {
        this.descuentoEnv = descuentoEnv;
    }

    public String tipoCliente() {
        return "Premium";
    }

    @Override
    public String toString() {
        return "Premium{" +
                "calcAnual=" + calcAnual +
                ", descuentoEnv=" + descuentoEnv +
                ", nombre='" + nombre + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", nif='" + nif + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public Map<String, String> getDatosCliente() {

        Map<String, String> datosCliente = super.getDatosCliente();

        datosCliente.put("Descuento envio", Float.toString(getDescuentoEnv() * 100) + "%");

        return datosCliente;
    }
}