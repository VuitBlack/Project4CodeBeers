package codebeers.controlador;

import codebeers.Hibernate.Clientes_ORM;
import codebeers.modelo.*;
import codebeers.exceptions.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class Controlador {

    private Datos datos;

    public Controlador() {
        datos = new Datos();
    }

    public void addCliente(String nombre, String domicilio, String nif, String email, String tipoCliente) throws OpcionNoValida {
        Cliente cliente;
        switch (tipoCliente) {
            case "Estandar":
            case "Estándar":
            case "estandar":
            case "estándar":
                cliente = new Estandar(nombre, domicilio, nif, email);
                break;
            case "Premium":
            case "premium":
                cliente = new Premium(nombre, domicilio, nif, email);
                break;
            default:
                throw new OpcionNoValida();
        }
        datos.addCliente(cliente);
    }

    public ArrayList getClientes(String filtro) {

        ArrayList<Cliente> clientes = datos.getClientes(filtro);
        ArrayList<Map<String, String>> datosClientes = new ArrayList<>();

        for (Cliente cliente: clientes) {
            datosClientes.add(cliente.getDatosCliente());
        }

        return datosClientes;
    }

    public void addArticulo(String id, String descripcion, float pvp, float gastosEnvio, int tiempoPreparacion) {
        Articulo articulo = new Articulo(id, descripcion, pvp, gastosEnvio, tiempoPreparacion);
        datos.addArticulo(articulo);
    }

    public ArrayList getArticulos() {

        ArrayList<Articulo> articulos = datos.getArticulos();
        ArrayList<Map<String, String>> datosArticulos = new ArrayList<>();

        for (Articulo articulo: articulos) {
            datosArticulos.add(articulo.getDatosArticulo());
        }

        return datosArticulos;
    }

    public void addPedido(String nif, String id, int unidades, LocalDateTime fechaHora) throws ElementoNoExiste{
        Cliente cliente = datos.getClienteByNif(nif);
        Articulo articulo = datos.getArticuloById(id);
        Pedido pedido = new Pedido(cliente, articulo, unidades, fechaHora);
        datos.addPedido(pedido);
    }

    public ArrayList getPedidos(String filtro, boolean enviado) {

        ArrayList<Pedido> pedidos = datos.getPedidos(filtro, enviado);
        ArrayList<Map<String, String>> datosPedidos = new ArrayList<>();

        for (Pedido pedido: pedidos) {
            datosPedidos.add(pedido.getDatosPedido());
        }

        return datosPedidos;
    }

    public void deletePedido(int num) throws ElementoNoExiste, PedidoYaPreparado {
        datos.deletePedido(num);
    }

    public boolean clienteExiste(String nif) {
        return datos.compruebaExistenciaCliente(nif);
    }

    public boolean articuloExiste(String id) {
        return datos.compruebaExistenciaArticulo(id);
    }
}
