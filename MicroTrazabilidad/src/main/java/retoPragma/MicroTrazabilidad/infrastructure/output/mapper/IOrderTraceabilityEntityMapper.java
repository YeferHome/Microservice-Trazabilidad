package retoPragma.MicroTrazabilidad.infrastructure.output.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import retoPragma.MicroTrazabilidad.domain.model.OrderTraceability;
import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;
import retoPragma.MicroTrazabilidad.infrastructure.output.entity.OrderTraceabilityEntity;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IOrderTraceabilityEntityMapper {

    OrderTraceabilityEntity toEntity(OrderTraceability model);
    CollectionModel<OrderTraceability> toModelCollection(CollectionModel<OrderTraceabilityEntity> entityList);

}
