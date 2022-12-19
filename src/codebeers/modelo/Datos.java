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
    public ListaClientes clientes;
    public ListaArticulos articulos;
    public ListaPedidos pedidos;
    public Datos() {
    }

    public void addCliente(Cliente cliente) {
        try(SessionFactory myFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Clientes_ORM.class)
                .buildSessionFactory()
        ) {
            try (Session mySession = myFactory.openSession()) {
                Clientes_ORM clienteORM = new Clientes_ORM(
                        cliente.getEmail(),
                        cliente.getNombre(),
                        cliente.getDomicilio(),
                        cliente.getNif(),
                        cliente.tipoCliente()
                );
                mySession.beginTransaction();           //Comenzamos la transacción para guardar el objeto Clientes en la BBDD
                mySession.save(clienteORM);
                mySession.getTransaction().commit();
            }
        }
    }

    public ArrayList getClientes(String filtro) {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try(SessionFactory myFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Clientes_ORM.class)
                .buildSessionFactory()
        ) {
            try (Session mySession = myFactory.openSession()) {
                Query q;
                mySession.beginTransaction();
                if(filtro.equals("")){
                    q = mySession.createQuery("from Clientes_ORM");
                }
                else {
                    q = mySession.createQuery("from Clientes_ORM c where c.tipoCliente = :type");
                    q.setParameter("type", filtro);
                }
                List<Clientes_ORM> clientesORM = q.getResultList();
                for (Clientes_ORM cliORM : clientesORM) {
                    if (cliORM.getTipoCliente().equals("Estándar"))
                        clientes.add(
                                new Estandar(
                                        cliORM.getNombre(),
                                        cliORM.getDomicilio(),
                                        cliORM.getNif(),
                                        cliORM.getEmail()
                                )
                        );
                    else
                        clientes.add(
                                new Premium(
                                    cliORM.getNombre(),
                                    cliORM.getDomicilio(),
                                    cliORM.getNif(),
                                    cliORM.getEmail()
                                )
                        );
                }
            }
        }
        return clientes;
    }

    public void addArticulo(Articulo articulo){
        try(SessionFactory myFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Articulos_ORM.class)
                .buildSessionFactory()
        ) {
            try (Session mySession = myFactory.openSession()) {
                Articulos_ORM articuloORM = new Articulos_ORM(
                        articulo.getId(),
                        articulo.getDescripcion(),
                        articulo.getPvp(),
                        articulo.getGastosEnvio(),
                        articulo.getPreparacion()
                );
                mySession.beginTransaction();
                mySession.save(articuloORM);
                mySession.getTransaction().commit();
            }
        }
    }
    public ArrayList getArticulos() {
        ArrayList<Articulo> articulos = new ArrayList<>();
        try(SessionFactory myFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Articulos_ORM.class)
                .buildSessionFactory()
        ) {
            try (Session mySession = myFactory.openSession()) {
                mySession.beginTransaction();
//                Query q = mySession.createQuery("from Articulos");
                List<Articulos_ORM> articulosORM = mySession.createQuery("from Articulos_ORM").getResultList();
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
        try (SessionFactory myFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pedidos_ORM.class)
                .addAnnotatedClass(Articulos_ORM.class)
                .addAnnotatedClass(Clientes_ORM.class)
                .buildSessionFactory()
        ) {
            try (Session mySession = myFactory.openSession()) {
                Pedidos_ORM pedidoORM = new Pedidos_ORM(
                        pedido.getNum(),
                        new Clientes_ORM(pedido.getCliente()),
                        new Articulos_ORM(pedido.getArticulo()),
                        pedido.getCantidad(),
                        pedido.getFechaHora()
                );
                mySession.beginTransaction();
                mySession.save(pedidoORM);
                mySession.getTransaction().commit();
            }
        }
    }

    public ArrayList<Pedido> getPedidos(String filtro, boolean enviado) {

        ArrayList<Pedido> pedidos = new ArrayList<>();

        try (SessionFactory myFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pedidos_ORM.class)
                .addAnnotatedClass(Articulos_ORM.class)
                .addAnnotatedClass(Clientes_ORM.class)
                .buildSessionFactory()
        ) {
            try (Session mySession = myFactory.openSession()) {
                mySession.beginTransaction();
                Query q;
                if (filtro.equals("")) {
                    q = mySession.createQuery("from Pedidos_ORM");
                } else {
                    q = mySession.createQuery("from Pedidos_ORM p where p.cliente = :mail");
                    q.setParameter("mail", filtro);
                }
                List<Pedidos_ORM> pedidosORM = q.getResultList();
                for (Pedidos_ORM pedidoORM : pedidosORM) {
                    Clientes_ORM cliORM = pedidoORM.getCliente();
                    Cliente cliente;
                    if (cliORM.getTipoCliente().equals("Premium")) {
                        cliente = new Premium(
                                cliORM.getNombre(),
                                cliORM.getDomicilio(),
                                cliORM.getNif(),
                                cliORM.getEmail()
                        );
                    } else {
                        cliente = new Estandar(
                                cliORM.getNombre(),
                                cliORM.getDomicilio(),
                                cliORM.getNif(),
                                cliORM.getEmail()
                        );
                    }

                    Articulos_ORM artiORM = pedidoORM.getArticulo();
                    Articulo articulo = new Articulo(
                            artiORM.getId(),
                            artiORM.getDescripcion(),
                            artiORM.getPvp(),
                            artiORM.getGastosEnvio(),
                            artiORM.getPreparacion()
                    );

                    Pedido pedido = new Pedido(
                            pedidoORM.getNum(),
                            cliente,
                            articulo,
                            pedidoORM.getCantidad(),
                            pedidoORM.getFechaHora()
                    );
                    if (pedido.pedidoEnviado() == enviado) {
                        pedidos.add(pedido);
                    }
                }
            }
        }
        return pedidos;
    }

    public void deletePedido(int num) throws ElementoNoExiste, PedidoYaPreparado {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        try(SessionFactory myFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Pedidos_ORM.class)
                .addAnnotatedClass(Articulos_ORM.class)
                .addAnnotatedClass(Clientes_ORM.class)
                .buildSessionFactory()
        ) {
            try (Session mySession = myFactory.openSession()) {
                pedidos = getPedidos("", false);
                for(Pedido pedido : pedidos) {
                    if (num == pedido.getNum()) {
                        System.out.println("Eureka!");
                        Pedidos_ORM pedidoORM = new Pedidos_ORM();
                        pedidoORM.setNum(num);
                        mySession.beginTransaction();
                        mySession.delete(pedidoORM);
                        mySession.getTransaction().commit();
                    }
                }
            }
        }
    }

    public Cliente getClienteByNif(String nif) throws ElementoNoExiste {

        Cliente cliente = null;
        try(SessionFactory myFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Clientes_ORM.class)
                .buildSessionFactory()
        ) {
            try (Session mySession = myFactory.openSession()) {
                mySession.beginTransaction();
                Query q = mySession.createQuery("from Clientes_ORM c where c.nif = :nif");
                q.setParameter("nif", nif);
                Clientes_ORM clienteORM = (Clientes_ORM) q.getSingleResult();
                if (clienteORM.getTipoCliente().equals("Estándar"))
                    cliente = new Estandar(
                            clienteORM.getNombre(),
                            clienteORM.getDomicilio(),
                            clienteORM.getNif(),
                            clienteORM.getEmail()
                    );
                else
                    cliente = new Premium(
                            clienteORM.getNombre(),
                            clienteORM.getDomicilio(),
                            clienteORM.getNif(),
                            clienteORM.getEmail()
                    );
            }
        }
        return cliente;
    }
    public Articulo getArticuloById(String id) throws ElementoNoExiste {
        Articulo articulo = null;
        try(SessionFactory myFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Articulos_ORM.class)
                .buildSessionFactory()
        ) {
            try (Session mySession = myFactory.openSession()) {
                mySession.beginTransaction();
                Query q = mySession.createQuery("from Articulos_ORM a where a.id = :id");
                q.setParameter("id", id);
                Articulos_ORM articuloORM = (Articulos_ORM) q.getSingleResult();
                articulo = new Articulo(
                        articuloORM.getId(),
                        articuloORM.getDescripcion(),
                        articuloORM.getPvp(),
                        articuloORM.getGastosEnvio(),
                        articuloORM.getPreparacion()
                );
            }
        }
        return articulo;
    }

    public boolean compruebaExistenciaCliente(String nif) {
        try (SessionFactory myFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Clientes_ORM.class)
                .buildSessionFactory()
        ) {
            try (Session mySession = myFactory.openSession()) {
                Query query = mySession.createQuery("select 1 from Clientes_ORM c where c.nif = :nif");
                query.setParameter("nif", nif);
                return (query.uniqueResult() != null);
            }
        }
    }

    public boolean compruebaExistenciaArticulo(String id) {

        try (SessionFactory myFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Articulos_ORM.class)
                .buildSessionFactory()
        ) {
            try (Session mySession = myFactory.openSession()) {
                Query query = mySession.createQuery("select 1 from Articulos_ORM a where a.id = :id");
                query.setParameter("id", id);
                return (query.uniqueResult() != null);
            }
        }
    }

    public boolean compruebaExistenciaPedido(int num) {
        return pedidos.compruebaExistencia(num);
    }
}
