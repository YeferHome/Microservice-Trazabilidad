package retoPragma.MicroTrazabilidad.domain.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
        super("Orden no existe");
    }
}
