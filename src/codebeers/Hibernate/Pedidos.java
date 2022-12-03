package codebeers.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="pedidos")
public class Pedidos {

    @Id
    @Column(name = "num")
    private int num;

    @Column(name = "cliente")
    private Clientes cliente;

    @Column(name = "articulo")
    private Articulos articulo;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "fechaHora")
    private LocalDateTime fechaHora;

    public Pedidos() {
    }

    public Pedidos(int num, Clientes cliente, Articulos articulo, int cantidad, LocalDateTime fechaHora) {
        this.num = num;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "pedidos{" +
                "num=" + num +
                ", cliente=" + cliente +
                ", articulo=" + articulo +
                ", cantidad=" + cantidad +
                ", fechaHora=" + fechaHora +
                '}';
    }
}


