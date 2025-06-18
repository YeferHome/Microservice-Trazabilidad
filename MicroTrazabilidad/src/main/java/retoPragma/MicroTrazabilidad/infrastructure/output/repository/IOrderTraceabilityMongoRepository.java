package retoPragma.MicroTrazabilidad.infrastructure.output.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import retoPragma.MicroTrazabilidad.infrastructure.output.entity.OrderTraceabilityEntity;

import java.util.List;

public interface IOrderTraceabilityMongoRepository extends MongoRepository<OrderTraceabilityEntity, String> {

    List<OrderTraceabilityEntity> findByOrderId(Long orderId);
    List<OrderTraceabilityEntity> findAllByOrderId(Long orderId);
}