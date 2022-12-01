package codebeers.modelo;

import java.util.LinkedHashMap;
import java.util.Map;

public class Articulo {
    private String id;
    private String descripcion;
    private Float pvp;
    private Float gastosEnvio;
    private int preparacion;

    public Articulo(){} //Inicializa todos los valores a NULL

    public Articulo(String id, String descripcion, Float pvp, Float gastosEnvio, int preparacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.pvp = pvp;
        this.gastosEnvio = gastosEnvio;
        this.preparacion = preparacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Float getPvp() {
        return pvp;
    }

    public void setPvp(Float pvp) {
        this.pvp = pvp;
    }

    public Float getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(Float gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    public int getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(int preparacion) {
        this.preparacion = preparacion;
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "Codigo='" + id + '\'' +
                ", Descripcion='" + descripcion + '\'' +
                ", PVP='" + pvp + '\'' +
                ", Gastos de Envio='" + gastosEnvio + '\'' +
                ", Tiempo Preparacion='" + preparacion + '\'' + '}';
    }

    public Map<String, String> getDatosArticulo() {

        Map<String, String> datosArticulo = new LinkedHashMap<>();

        datosArticulo.put("Id", getId());
        datosArticulo.put("Descripcion", getDescripcion());
        datosArticulo.put("PVP", Float.toString(getPvp()));
        datosArticulo.put("Gastos de envío", Float.toString(getGastosEnvio()));
        datosArticulo.put("Tiempo de preparación", Integer.toString(getPreparacion()));

        return  datosArticulo;
    }
}