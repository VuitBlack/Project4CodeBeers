package codebeers.modelo;

import codebeers.exceptions.ElementoNoExiste;
import java.util.Iterator;

public class ListaArticulos extends Lista{

    public ListaArticulos() {

    }

    public boolean compruebaExistencia(String id) {
        Iterator<Articulo> iter = lista.iterator();

        while(iter.hasNext()) {
            if (iter.next().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Articulo getArticuloById(String id) throws ElementoNoExiste {
        Iterator<Articulo> iter = lista.iterator();

        while(iter.hasNext()) {
            Articulo articulo = iter.next();
            if (articulo.getId().equals(id)) {
                return articulo;
            }
        }

        throw new ElementoNoExiste();
    }
}