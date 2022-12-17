package codebeers.vista;

import codebeers.controlador.Controlador;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import codebeers.exceptions.*;

public class GestionOS{

    private Controlador controlador;
    Scanner teclado = new Scanner(System.in);

    public GestionOS(){
        controlador = new Controlador();
    }

    public void inicio() {
/*
        try {
            loadSeeders();
        }catch(OnlineStoreException e){
            System.out.println(e.getError());
        }
 */

        boolean salir = false;
        char opcion;

        do {
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║          ONLINE STORE          ║");
            System.out.println("║                                ║");
            System.out.println("║ 1. Gestión Artículos           ║");
            System.out.println("║ 2. Gestión Clientes            ║");
            System.out.println("║ 3. Gestión Pedidos             ║");
            System.out.println("║ 0. Salir                       ║");
            System.out.println("║                                ║");
            System.out.println("╚═════════════════ by CodeBeers ═╝");
            opcion = pedirOpcion();
            try{
                switch (opcion){
                    case '1':
                        gestionarMenuArticulos();
                        break;
                    case '2':
                        gestionarMenuClientes();
                        break;
                    case '3':
                        gestionarMenuPedidos();
                        break;
                    case '0':
                        salir = true;
                        break;
                    default:
                        throw new OpcionNoValida();
                }
            }
            catch(OnlineStoreException e){
                System.out.println(e.getError());
            }
        }while(!salir);
    }

    char pedirOpcion(){
        String resp;

        resp = teclado.nextLine();
        if(resp.isEmpty()){
            resp = " ";
        }
        return resp.charAt(0);
    }

    public void gestionarMenuArticulos() throws OpcionNoValida, ListaEstaVacia, ElementoYaExiste {

        char opcion;

        System.out.println("1. Añadir Artículo");
        System.out.println("2. Mostrar Artículos");
        opcion = pedirOpcion();

        switch (opcion) {
            case '1':
                addArticulo();
                break;
            case '2':
                mostrarLista(controlador.getArticulos());
                break;
            default:
                throw new OpcionNoValida();
        }
    }

    public void gestionarMenuClientes() throws OpcionNoValida, ElementoYaExiste, ListaEstaVacia{

        char opcion;

        System.out.println("1. Añadir Cliente");
        System.out.println("2. Mostrar Clientes");
        System.out.println("3. Mostrar Clientes Estándar");
        System.out.println("4. Mostrar Clientes Premium");
        opcion = pedirOpcion();

        switch (opcion) {
            case '1':
                String nif;
                System.out.println("NIF del cliente: ");
                nif = teclado.nextLine();
//                if(controlador.clienteExiste(nif))
//                    throw new ElementoYaExiste();
                addCliente(nif);
                break;
            case '2':
                mostrarLista(controlador.getClientes(""));
                break;
            case '3':
                mostrarLista(controlador.getClientes("Estándar"));
                break;
            case '4':
                mostrarLista(controlador.getClientes("Premium"));
                break;
            default:
                throw new OpcionNoValida();
        }
    }

    public void gestionarMenuPedidos() throws OpcionNoValida, ElementoNoExiste, ListaEstaVacia, ElementoYaExiste, PedidoYaPreparado {

        char opcion;
        int num;

        String nif;
        System.out.println("1. Añadir Pedido");
        System.out.println("2. Eliminar Pedido");
        System.out.println("3. Mostrar pedidos pendientes de envío");
        System.out.println("4. Mostrar pedidos enviados");
        opcion = pedirOpcion();

        switch (opcion) {
            case '1':
                addPedido();
                break;
            case '2':
                System.out.println("Introduce el id del pedido");
                num = Integer.parseInt(teclado.nextLine());
                controlador.deletePedido(num);
                break;
            case '3':
                System.out.println("Indica el nif del cliente, deja en blanco para ver todos los pedidos");
                nif = teclado.nextLine();
                mostrarLista(controlador.getPedidos(nif, false));
                break;
            case '4':
                System.out.println("Indica el nif del cliente, deja en blanco para ver todos los pedidos");
                nif = teclado.nextLine();
                mostrarLista(controlador.getPedidos(nif, true));
                break;
            default:
                throw new OpcionNoValida();
        }
    }

    public void addCliente(String nif) throws ElementoYaExiste, OpcionNoValida {

        String nombre, domicilio, email, tipoCliente;

        System.out.println("Nombre del cliente: ");
        nombre = teclado.nextLine();
        System.out.println("Domicilio del cliente: ");
        domicilio = teclado.nextLine();
        System.out.println("Email del cliente: ");
        email = teclado.nextLine();
        System.out.println("Tipo de cliente: ");
        tipoCliente = teclado.nextLine();

        controlador.addCliente(nombre, domicilio, nif, email, tipoCliente);
    }

    public void addArticulo() throws ElementoYaExiste{

        String id, descripcion;
        float pvp, gastosEnvio;
        int preparacion;

        System.out.println("ID del artículo: ");
        id = teclado.nextLine();
        if(controlador.articuloExiste(id))
            throw new ElementoYaExiste();
        System.out.println("Descripción del artículo: ");
        descripcion = teclado.nextLine();
        System.out.println("PVP del artículo: ");
        pvp = Float.valueOf(teclado.nextLine());
        System.out.println("Gastos de envío del artículo: ");
        gastosEnvio = Float.valueOf(teclado.nextLine());
        System.out.println("Tiempo de preparación del artículo: ");
        preparacion = Integer.valueOf(teclado.nextLine());

        controlador.addArticulo(id, descripcion, pvp, gastosEnvio, preparacion);
    }

    public void addPedido() throws ElementoYaExiste, OpcionNoValida, ElementoNoExiste {

        int cantidad;
        String nif, id;

        System.out.println("NIF del cliente: ");
        nif = teclado.nextLine();
        if(!controlador.clienteExiste(nif))
            addCliente(nif);
        System.out.println("ID del artículo: ");
        id = teclado.nextLine();
        if(!controlador.articuloExiste(id))
            throw new ElementoNoExiste();
        System.out.println("Cantidad de artículos: ");
        cantidad = Integer.valueOf(teclado.nextLine());

        controlador.addPedido(nif, id, cantidad, LocalDateTime.now());
    }

    public void mostrarLista(ArrayList<HashMap<String,String>> elementos) {
        System.out.println("==========================================================================");
        for (HashMap<String, String> elemento : elementos) {
            Set<String> campos = elemento.keySet();

            for (String campo : campos) {
                String valor = elemento.get(campo);
                System.out.println(campo + ": " + valor);
            }
            System.out.println("==========================================================================");
        }
    }

    public void loadSeeders() throws OpcionNoValida, ElementoNoExiste{
        // Add articles
        controlador.addArticulo("1", "Artículo 1", 11.11f, 6.66f, 1);
        controlador.addArticulo("2", "Artículo 2", 22.22f, 7.77f, 30);
        controlador.addArticulo("3", "Artículo 3", 33.33f, 8.88f, 40);
        controlador.addArticulo("4", "Artículo 4", 44.44f, 9.99f, 50);
        controlador.addArticulo("5", "Artículo 5", 55.55f, 1.11f, 1);

        // Add clients
        controlador.addCliente("Cliente 1", "Casa de cliente 1", "11", "cliente1@mail.com", "Estandar");
        controlador.addCliente("Cliente 2", "Casa de cliente 2", "22", "cliente2@mail.com", "Premium");
        controlador.addCliente("Cliente 3", "Casa de cliente 3", "33", "cliente3@mail.com", "Estandar");
        controlador.addCliente("Cliente 4", "Casa de cliente 4", "44", "cliente4@mail.com", "Premium");
        controlador.addCliente("Cliente 5", "Casa de cliente 5", "55", "cliente5@mail.com", "Estandar");

        // Add orders
        controlador.addPedido("11", "5", 6, LocalDateTime.now());
        controlador.addPedido("22", "4", 7, LocalDateTime.now());
        controlador.addPedido("33", "3", 8, LocalDateTime.now());
        controlador.addPedido("44", "2", 9, LocalDateTime.now());
        controlador.addPedido("55", "1", 1, LocalDateTime.now());
    }
}