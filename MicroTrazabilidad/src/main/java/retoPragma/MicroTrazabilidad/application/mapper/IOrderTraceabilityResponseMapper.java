package retoPragma.MicroTrazabilidad.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import retoPragma.MicroTrazabilidad.application.dto.OrderTraceabilityResponseDto;
import retoPragma.MicroTrazabilidad.domain.model.OrderTraceability;
import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IOrderTraceabilityResponseMapper {

    OrderTraceabilityResponseDto toResponseDto(OrderTraceability traceability);

    default CollectionModel<OrderTraceabilityResponseDto> toResponseModelCollection(CollectionModel<OrderTraceability> traceabilities) {
        List<OrderTraceabilityResponseDto> dtoList = traceabilities.getItems().stream()
                .map(this::toResponseDto)
                .toList();

        return new CollectionModel<>(dtoList);
    }
}
