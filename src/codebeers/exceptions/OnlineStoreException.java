package codebeers.exceptions;

public abstract class OnlineStoreException extends Exception {

    private static final long serialVersionUID = 1L;

    public OnlineStoreException() {
    }

    public abstract String getError();
}
