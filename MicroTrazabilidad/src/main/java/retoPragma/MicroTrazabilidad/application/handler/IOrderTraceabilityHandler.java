package retoPragma.MicroTrazabilidad.application.handler;

import retoPragma.MicroTrazabilidad.application.dto.OrderTraceabilityResponseDto;
import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;

public interface IOrderTraceabilityHandler {
    CollectionModel<OrderTraceabilityResponseDto> getOrderTraceabilityByClient(Long orderId, Long clientId);
}
