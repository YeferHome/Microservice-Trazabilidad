package retoPragma.MicroTrazabilidad.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import retoPragma.MicroTrazabilidad.application.dto.OrderTraceabilityResponseDto;
import retoPragma.MicroTrazabilidad.domain.model.OrderTraceability;
import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = TimestampMapper.class)
public interface IOrderTraceabilityResponseMapper {

    @Mapping(source = "timestamp", target = "timestamp", qualifiedByName = "toLocalDateTime")
    OrderTraceabilityResponseDto toResponseDto(OrderTraceability traceability);

    default CollectionModel<OrderTraceabilityResponseDto> toResponseModelCollection(CollectionModel<OrderTraceability> traceabilities) {
        List<OrderTraceabilityResponseDto> dtoList = traceabilities.getItems().stream()
                .map(this::toResponseDto)
                .toList();

        return new CollectionModel<>(dtoList);
    }
}
