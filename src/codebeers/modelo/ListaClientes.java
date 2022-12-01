package codebeers.modelo;

import codebeers.exceptions.ElementoNoExiste;
import java.util.ArrayList;
import java.util.Iterator;

public class ListaClientes extends Lista{

    public ListaClientes() {
    }

    public ArrayList<Cliente> getClientes(String filtro) {
        ArrayList<Cliente> clientesFiltrados = new ArrayList<>();
        Iterator<Cliente> iter = lista.iterator();

        while (iter.hasNext()){
            Cliente cliente = iter.next();
            if (filtro.equals("") || cliente.tipoCliente().equals(filtro)) {
                clientesFiltrados.add(cliente);
            }
        }
        return clientesFiltrados;
    }

    public boolean compruebaExistencia(String nif) {
        Iterator<Cliente> iter = lista.iterator();

        while (iter.hasNext()) {
            if (iter.next().getNif().equals(nif) ) {
                return true;
            }
        }
        return false;
    }

    public Cliente getClienteByNif(String nif) throws ElementoNoExiste{
        Iterator<Cliente> iter = lista.iterator();

        while (iter.hasNext()) {
            Cliente cliente = iter.next();
            if (cliente.getNif().equals(nif) ) {
                return cliente;
            }
        }
        throw new ElementoNoExiste();
    }
}