package codebeers.exceptions;

public class ListaEstaVacia extends OnlineStoreException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getError() {
        return "Aún no se ha añadido ningún elemento a la lista que está intentando visualizar";
    }

}