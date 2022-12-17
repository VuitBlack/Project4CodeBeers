package codebeers.modelo;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;


@Entity
@Table
public class Articulo implements Serializable {

    // atributos
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String descripcion;
    @Column(nullable = false)
    private Float pvp;
    @Column(nullable = false)
    private Float gastosEnvio;
    @Column(nullable = false)
    private int preparacion;

    // constructores

    public Articulo(String id, String descripcion, float pvp, float gastosEnvio, int tiempoPreparacion) {
    }

    public Articulo(String id, String descripcion, Float pvp, Float gastosEnvio, int preparacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.pvp = pvp;
        this.gastosEnvio = gastosEnvio;
        this.preparacion = preparacion;
    }

    // getter y settergigit statsu


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


    // toString


    @Override
    public String toString() {
        return "Articulo{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", pvp=" + pvp +
                ", gastosEnvio=" + gastosEnvio +
                ", preparacion=" + preparacion +
                '}';
    }

    public Map<String, String> getDatosArticulo() {
        return null;
    }
}
