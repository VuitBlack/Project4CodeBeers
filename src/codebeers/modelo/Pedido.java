package codebeers.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pedido {
    private int num;
    private static int nextNum = 0;
    private Cliente cliente;
    private Articulo articulo;
    private int cantidad;
    private LocalDateTime fechaHora;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Pedido(){
        nextNum++;
        this.num = nextNum;
    } //Inicializa los valores a NULL

    public Pedido(Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHora) {
        nextNum++;
        this.num = nextNum;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }

    public Pedido(int num, Cliente cliente, Articulo articulo, int cantidad, LocalDateTime fechaHora) {
        this.num = num;
        this.cliente = cliente;
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.fechaHora = fechaHora;
    }

    public Pedido(Pedido pedido) {
        this.num = pedido.getNum();
        this.cliente = pedido.getCliente();
        this.articulo = pedido.getArticulo();
        this.cantidad = pedido.getCantidad();
        this.fechaHora = pedido.getFechaHora();
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
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
        this.fechaHora = LocalDateTime.now();
    }

    public boolean pedidoEnviado() {

        int tiempoPreparacion = articulo.getPreparacion()*cantidad;

        LocalDateTime actualTime = LocalDateTime.now();
        LocalDateTime dateTimePreparacion = getFechaHora().plusMinutes(tiempoPreparacion);

        return ( actualTime.isAfter(dateTimePreparacion) );

    }

    public float precioEnvio() {
        float precio = articulo.getGastosEnvio() * getCantidad() * (1f - cliente.descuentoEnv());
        return ((float)Math.round(precio * 100.0) / 100f);
    }

    public float getTotal() {
        float precio = precioEnvio() + (getCantidad() * articulo.getPvp());
        return ((float)Math.round(precio * 100.0) / 100f);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "Numero Pedido='" + num + '\'' +
                ", Cliente='" + cliente + '\'' +
                ", Articulo='" + articulo + '\'' +
                ", Cantidad='" + cantidad + '\'' +
                ", Fecha Pedido='" + fechaHora + '\'' + '}';
    }

    public Map<String, String> getDatosPedido() {

        Map<String, String> datosPedido = new LinkedHashMap<>();

        datosPedido.put("Num", Integer.toString(getNum()));
        datosPedido.put("Fecha y hora", getFechaHora().format(formatter));
        datosPedido.put("Cantidad", Integer.toString(getCantidad()));
        datosPedido.put("Precio envio", Float.toString(precioEnvio()));
        datosPedido.put("Precio total", Float.toString(getTotal()));
        if (pedidoEnviado()) {
            datosPedido.put("Estado", "Pedido enviado");
        } else {
            datosPedido.put("Estado", "Pedido en preparación");
        }
        datosPedido.put("Cliente", getCliente().getNif());
        datosPedido.put("Articulo", getArticulo().getDescripcion());

        return datosPedido;
    }
}