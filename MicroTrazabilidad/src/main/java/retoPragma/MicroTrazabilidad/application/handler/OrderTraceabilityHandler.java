package retoPragma.MicroTrazabilidad.application.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import retoPragma.MicroTrazabilidad.application.dto.OrderTraceabilityResponseDto;
import retoPragma.MicroTrazabilidad.application.mapper.IOrderTraceabilityResponseMapper;
import retoPragma.MicroTrazabilidad.domain.exception.OrderNotFoundException;
import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;
import retoPragma.MicroTrazabilidad.domain.model.OrderClientTraceability;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderTraceabilityPersistencePort;

@Service
@RequiredArgsConstructor
public class OrderTraceabilityHandler implements IOrderTraceabilityHandler {

    IOrderTraceabilityPersistencePort traceabilityPersistencePort;

    private final IOrderTraceabilityResponseMapper responseMapper;

    @Override
    public CollectionModel<OrderTraceabilityResponseDto> getOrderTraceabilityByClient(Long orderId, Long clientId) {
        OrderClientTraceability traceability = traceabilityPersistencePort.findClientTraceabilityByOrderId(orderId);

        if (traceability == null || !traceability.getClientId().equals(clientId)) {
            throw new OrderNotFoundException();
        }

        return responseMapper.toResponseModelCollection(traceability.getTraceability());
    }
}
