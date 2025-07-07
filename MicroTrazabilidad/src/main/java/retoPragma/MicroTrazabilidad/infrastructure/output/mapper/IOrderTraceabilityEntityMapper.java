package retoPragma.MicroTrazabilidad.infrastructure.output.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;
import retoPragma.MicroTrazabilidad.domain.model.OrderTraceability;
import retoPragma.MicroTrazabilidad.infrastructure.output.entity.OrderTraceabilityEntity;

@Mapper(
        componentModel = "spring",
        uses = EntityTimestampMapper.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IOrderTraceabilityEntityMapper {

    OrderTraceability toModel(OrderTraceabilityEntity entity);

    OrderTraceabilityEntity toEntity(OrderTraceability model);

    default CollectionModel<OrderTraceability> toModelCollection(CollectionModel<OrderTraceabilityEntity> entityList) {
        return new CollectionModel<>(
                entityList.getItems().stream()
                        .map(this::toModel)
                        .toList()
        );
    }
}
