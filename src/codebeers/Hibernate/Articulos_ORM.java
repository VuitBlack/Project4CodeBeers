package codebeers.Hibernate;

import codebeers.modelo.Articulo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Articulos")
public class Articulos_ORM {

    @Id
    @Column(name="id")
    private String id;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="pvp")
    private Float pvp;
    @Column(name="gastosEnvio")
    private Float gastosEnvio;
    @Column(name="preparacion")
    private int preparacion;

    public Articulos_ORM(String id, String descripcion, Float pvp, Float gastosEnvio, int preparacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.pvp = pvp;
        this.gastosEnvio = gastosEnvio;
        this.preparacion = preparacion;
    }

    public Articulos_ORM(Articulo articulo){
        id = articulo.getId();
        descripcion = articulo.getDescripcion();
        pvp = articulo.getPvp();
        gastosEnvio = articulo.getGastosEnvio();
        preparacion = articulo.getPreparacion();
    }

    public Articulos_ORM() {
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
        return "articulo{" +
                "id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", pvp=" + pvp +
                ", gastosEnvio=" + gastosEnvio +
                ", preparacion=" + preparacion +
                '}';
    }
}

