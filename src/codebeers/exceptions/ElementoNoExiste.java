package codebeers.exceptions;

public class ElementoNoExiste extends OnlineStoreException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getError() {
        return "El elemento seleccionado no existe.";
    }

}
