package retoPragma.MicroTrazabilidad.infrastructure.output.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retoPragma.MicroTrazabilidad.domain.model.OrderTraceability;
import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;
import retoPragma.MicroTrazabilidad.domain.model.OrderClientTraceability;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderTraceabilityPersistencePort;
import retoPragma.MicroTrazabilidad.infrastructure.output.entity.OrderTraceabilityEntity;
import retoPragma.MicroTrazabilidad.infrastructure.output.mapper.IOrderTraceabilityEntityMapper;
import retoPragma.MicroTrazabilidad.infrastructure.output.repository.IOrderTraceabilityMongoRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderTraceabilityAdapter implements IOrderTraceabilityPersistencePort {

    private final IOrderTraceabilityMongoRepository repository;
    private final IOrderTraceabilityEntityMapper mapper;

    @Override
    public void save(OrderTraceability traceability) {
        OrderTraceabilityEntity entity = mapper.toEntity(traceability);
        repository.save(entity);
    }


    @Override
    public CollectionModel<OrderTraceability> findAllByOrderId(Long orderId) {
        List<OrderTraceabilityEntity> entityList = repository.findAllByOrderId(orderId);
        CollectionModel<OrderTraceabilityEntity> entityCollection = new CollectionModel<>(entityList);
        return mapper.toModelCollection(entityCollection);
    }

    @Override
    public OrderClientTraceability findClientTraceabilityByOrderId(Long orderId) {
        List<OrderTraceabilityEntity> entities = repository.findAllByOrderId(orderId);

        if (entities.isEmpty()) {
            return null;
        }

        Long clientId = entities.get(0).getClientId();
        CollectionModel<OrderTraceabilityEntity> entityCollection = new CollectionModel<>(entities);
        CollectionModel<OrderTraceability> traceabilities = mapper.toModelCollection(entityCollection);

        return new OrderClientTraceability(clientId, traceabilities);
    }
}
