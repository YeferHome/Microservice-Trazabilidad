package retoPragma.MicroTrazabilidad.domain.api;

import retoPragma.MicroTrazabilidad.domain.model.OrderTraceability;
import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;

public interface IOrderTraceabilityServicePort {
    CollectionModel<OrderTraceability> getTraceabilityByOrderId(Long orderId, Long clientId);
}