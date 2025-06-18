package retoPragma.MicroTrazabilidad.domain.spi;

public interface IOrderValidationPort {
    boolean existsOrderById(Long orderId);
}
