package codebeers.modelo;

import codebeers.exceptions.ElementoNoExiste;
import codebeers.exceptions.PedidoYaPreparado;

import java.util.ArrayList;


public class Datos {

    private ListaClientes clientes;
    private ListaArticulos articulos;
    private ListaPedidos pedidos;

    public Datos() {
        this.clientes = new ListaClientes();
        this.articulos = new ListaArticulos();
        this.pedidos = new ListaPedidos();
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public ArrayList getClientes(String filtro) {
        return clientes.getClientes(filtro);
    }

    public void addArticulo(Articulo articulo){
        articulos.add(articulo);
    }
    public ArrayList getArticulos() {
        return articulos.getArrayList();
    }

    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public ArrayList getPedidos(String filtro, boolean enviado) {
        return pedidos.getPedidos(filtro, enviado);
    }

    public void deletePedido(int num) throws ElementoNoExiste, PedidoYaPreparado {
        pedidos.deletePedido(num);
    }

    public Cliente getClienteByNif(String nif) throws ElementoNoExiste {
        return clientes.getClienteByNif(nif);
    }
    public Articulo getArticuloById(String id) throws ElementoNoExiste {
        return articulos.getArticuloById(id);
    }

    public boolean compruebaExistenciaCliente(String nif) {
        return clientes.compruebaExistencia(nif);
    }

    public boolean compruebaExistenciaArticulo(String id) {
        return articulos.compruebaExistencia(id);
    }

    public boolean compruebaExistenciaPedido(int num) {
        return pedidos.compruebaExistencia(num);
    }
}
