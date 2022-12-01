package codebeers.modelo;

import codebeers.exceptions.ElementoNoExiste;
import codebeers.exceptions.OnlineStoreException;
import codebeers.exceptions.PedidoYaPreparado;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class ListaPedidos extends Lista{

    private Lista<Pedido> pedidos;

    public ListaPedidos() { }

    public ArrayList<Pedido> getPedidos(String filtro, boolean enviado) {
        ArrayList<Pedido> pedidosFiltrados = new ArrayList<>();
        Iterator<Pedido> iter = lista.iterator();

        while (iter.hasNext()) {
            Pedido pedido = iter.next();
            if ((pedido.getCliente().getNif().equals(filtro) || filtro.equals("")) &&
                    pedido.pedidoEnviado() == enviado) {
                pedidosFiltrados.add(pedido);
            }
        }
        return pedidosFiltrados;
    }

    public Pedido getPedidoByNum(int num) throws ElementoNoExiste {

        Iterator<Pedido> iter = lista.iterator();

        while (iter.hasNext()) {
            Pedido pedido = iter.next();
            if (pedido.getNum() == num) {
                return pedido;
            }
        }
        throw new ElementoNoExiste();

    }

    public void deletePedido(int num) throws ElementoNoExiste, PedidoYaPreparado {
        Pedido pedido = getPedidoByNum(num);
        if (pedido.pedidoEnviado()) {
            throw new PedidoYaPreparado();
        } else {
            borrar(pedido);
        }
    }

    public boolean compruebaExistencia(int num) {
        Iterator<Pedido> iter = lista.iterator();

        while (iter.hasNext()) {
            Pedido pedido = iter.next();
            if (pedido.getNum() == num) {
                return true;
            }
        }
        return false;
    }
}