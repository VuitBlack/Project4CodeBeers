package codebeers.modelo;

import codebeers.Hibernate.Articulos_ORM;
import codebeers.Hibernate.Clientes_ORM;
import codebeers.Hibernate.Pedidos_ORM;
import codebeers.exceptions.ElementoNoExiste;
import codebeers.exceptions.PedidoYaPreparado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class Datos {

    public Datos() {
    }

    public void addCliente(Cliente cliente) {
        try(SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes_ORM.class).buildSessionFactory()) {
            try (Session mySession = myFactory.openSession()) {
                Clientes_ORM clienteORM = new Clientes_ORM(cliente.getEmail(), cliente.getNombre(), cliente.getDomicilio(), cliente.getNif(), cliente.tipoCliente());
                mySession.beginTransaction();           //Comenzamos la transacción para guardar el objeto Clientes en la BBDD
                mySession.save(clienteORM);
                mySession.getTransaction().commit();
            }
        }
    }

    public ArrayList getClientes(String filtro) {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try(SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Clientes_ORM.class).buildSessionFactory()) {
            try (Session mySession = myFactory.openSession()) {
                Query q;
                mySession.beginTransaction();
                if(filtro == ""){
                    q = mySession.createQuery("from Clientes");
                }
                else {
                    q = mySession.createQuery("from Clientes c where c.tipoCliente = ':0'");
                    q.setParameter(0, filtro);
                }
                List<Clientes_ORM> clientesORM = q.getResultList();
                for (Clientes_ORM cliORM : clientesORM) {
                    if (cliORM.getTipoCliente() == "Estándar")
                        clientes.add(new Estandar(cliORM.getNombre(), cliORM.getDomicilio(), cliORM.getNif(), cliORM.getEmail()));
                    else
                        clientes.add(new Premium(cliORM.getNombre(), cliORM.getDomicilio(), cliORM.getNif(), cliORM.getEmail()));
                }
            }
        }
        return clientes;
    }

    public void addArticulo(Articulo articulo){
        try(SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulos_ORM.class).buildSessionFactory()) {
            try (Session mySession = myFactory.openSession()) {
                Articulos_ORM articuloORM = new Articulos_ORM(articulo.getId(), articulo.getDescripcion(), articulo.getPvp(), articulo.getGastosEnvio(), articulo.getPreparacion());
                mySession.beginTransaction();
                mySession.save(articuloORM);
                mySession.getTransaction().commit();
            }
        }
    }
    public ArrayList getArticulos() {
        ArrayList<Articulo> articulos = new ArrayList<>();
        try(SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulos_ORM.class).buildSessionFactory()) {
            try (Session mySession = myFactory.openSession()) {
                mySession.beginTransaction();
                Query q = mySession.createQuery("from Articulos");
                List<Articulos_ORM> articulosORM = q.getResultList();
                for (Articulos_ORM articuloORM : articulosORM) {
                    Articulo articulo = new Articulo(
                            articuloORM.getId(),
                            articuloORM.getDescripcion(),
                            articuloORM.getPvp(),
                            articuloORM.getGastosEnvio(),
                            articuloORM.getPreparacion()
                    );
                    articulos.add(articulo);
                }
            }
        }
        return articulos;
    }

    public void addPedido(Pedido pedido) {
        try(SessionFactory myFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedidos_ORM.class).buildSessionFactory()) {
            try (Session mySession = myFactory.openSession()) {
                Pedidos_ORM pedidoORM = new Pedidos_ORM(pedido.getNum(), new Clientes_ORM(pedido.getCliente()), new Articulos_ORM(pedido.getArticulo()), pedido.getCantidad(), pedido.getFechaHora());
                mySession.beginTransaction();
                mySession.save(pedidoORM);
                mySession.getTransaction().commit();
            }
        }
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
