package retoPragma.MicroTrazabilidad.domain.usecase;

import retoPragma.MicroTrazabilidad.domain.api.IOrderTraceabilityServicePort;
import retoPragma.MicroTrazabilidad.domain.exception.OrderNotFoundException;
import retoPragma.MicroTrazabilidad.domain.exception.UnauthorizedAccessException;
import retoPragma.MicroTrazabilidad.domain.model.OrderTraceability;
import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;
import retoPragma.MicroTrazabilidad.domain.model.OrderClientTraceability;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderTraceabilityPersistencePort;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderValidationPort;

public class OrderTraceabilityUseCase implements IOrderTraceabilityServicePort {

    private final IOrderTraceabilityPersistencePort persistencePort;
    private final IOrderValidationPort validationPort;

    public OrderTraceabilityUseCase(
            IOrderTraceabilityPersistencePort persistencePort,
            IOrderValidationPort validationPort
    ) {
        this.persistencePort = persistencePort;
        this.validationPort = validationPort;
    }

    @Override
    public CollectionModel<OrderTraceability> getTraceabilityByOrderId(Long orderId, Long clientId) {
        if (!validationPort.existsOrderById(orderId)) {
            throw new OrderNotFoundException();
        }

        OrderClientTraceability traceability = persistencePort.findClientTraceabilityByOrderId(orderId);

        if (traceability == null || !traceability.getClientId().equals(clientId)) {
            throw new UnauthorizedAccessException();
        }

        return traceability.getTraceability();
    }

}
