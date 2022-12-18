package codebeers.Hibernate;

import codebeers.modelo.Pedido;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="Pedidos")
public class Pedidos_ORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num")
    private int num;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente")
    private Clientes_ORM cliente;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "articulo")
    private Articulos_ORM articulo;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "fechaHora")
    private LocalDateTime fechaHora;

    public Pedidos_ORM() { }

    public Pedidos_ORM(
            Clientes_ORM cliente,
            Articulos_ORM articulo,
            int cantidad,
            LocalDateTime fechaHora
    ) {
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }

    public Pedidos_ORM(Pedido pedido) {
        this.num = pedido.getNum();
        this.cliente = new Clientes_ORM(pedido.getCliente());
        this.articulo = new Articulos_ORM(pedido.getArticulo());
        this.cantidad = pedido.getCantidad();
        this.fechaHora = pedido.getFechaHora();
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Clientes_ORM getCliente() {
        return cliente;
    }

    public void setCliente(Clientes_ORM cliente) {
        this.cliente = cliente;
    }

    public Articulos_ORM getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulos_ORM articulo) {
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
        return "Pedidos_ORM{" +
                "num=" + num +
                ", cliente=" + cliente +
                ", articulo=" + articulo +
                ", cantidad=" + cantidad +
                ", fechaHora=" + fechaHora +
                '}';
    }
}


