package codebeers.exceptions;

public class PedidoYaPreparado extends OnlineStoreException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getError() {
        return "El pedido que está tratando de eliminar ya ha salido de los almacenes y no se puede cancelar.";
    }

}

