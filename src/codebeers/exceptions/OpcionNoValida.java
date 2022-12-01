package codebeers.exceptions;

public class OpcionNoValida extends OnlineStoreException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getError() {
        return "La opción seleccionada no es una opción válida.";
    }

}
