package retoPragma.MicroTrazabilidad.domain.spi;

import retoPragma.MicroTrazabilidad.domain.model.OrderClientTraceability;
import retoPragma.MicroTrazabilidad.domain.model.OrderTraceability;
import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;


public interface IOrderTraceabilityPersistencePort {

    void save(OrderTraceability traceability);
    CollectionModel<OrderTraceability> findAllByOrderId(Long orderId);
    OrderClientTraceability findClientTraceabilityByOrderId(Long orderId);
    CollectionModel<OrderTraceability> findDeliveredOrdersByRestaurant(Long restaurantId);
}
