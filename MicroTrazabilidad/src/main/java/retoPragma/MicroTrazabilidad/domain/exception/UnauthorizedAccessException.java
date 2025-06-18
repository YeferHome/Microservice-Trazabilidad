package retoPragma.MicroTrazabilidad.domain.exception;

public class UnauthorizedAccessException extends RuntimeException {
    public UnauthorizedAccessException() {
        super("No tienes acceso a este pedido.");
    }
}
