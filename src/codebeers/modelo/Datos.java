package codebeers.modelo;

import codebeers.Hibernate.Clientes_ORM;
import codebeers.exceptions.ElementoNoExiste;
import codebeers.exceptions.PedidoYaPreparado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
    public void addCliente_ORM(Cliente cliente) {
        try (SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes_ORM.class).buildSessionFactory()) {
            try (Session mySession = myFactory.openSession()) {
                mySession.beginTransaction();           //Comenzamos la transacción para guardar el objeto Clientes en la BBDD
                mySession.save(cliente);               //Guarda el objeto cliente en BBDD
                mySession.getTransaction().commit();    //Mediante el Commit se graba en la base de datos
                System.out.println("Registro insertado correcatamente");
                mySession.close();                      //Se cierra la sesion para liberar memoria
            }
        }

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
