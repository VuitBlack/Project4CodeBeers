package codebeers.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="pedidos")
public class pedidos {

    @Id
    @Column(name = "num")
    private int num;

    @Column(name = "cliente")
    private clientes cliente;

    @Column(name = "articulo")
    private articulos articulo;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "fechaHora")
    private LocalDateTime fechaHora;

    public pedidos() {
    }

    public pedidos(int num, clientes cliente, articulos articulo, int cantidad, LocalDateTime fechaHora) {
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

    public clientes getCliente() {
        return cliente;
    }

    public void setCliente(clientes cliente) {
        this.cliente = cliente;
    }

    public articulos getArticulo() {
        return articulo;
    }

    public void setArticulo(articulos articulo) {
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


