package codebeers.exceptions;

public class ElementoYaExiste extends OnlineStoreException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getError() {
        return "El elemento que está intentando añadir ya existe.";
    }
}

