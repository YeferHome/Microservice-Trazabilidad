package retoPragma.MicroTrazabilidad.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import retoPragma.MicroTrazabilidad.application.dto.OrderTraceabilityRequestDto;
import retoPragma.MicroTrazabilidad.domain.model.OrderTraceability;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IOrderTraceabilityRequestMapper {
    OrderTraceability toOrderTraceability(OrderTraceabilityRequestDto dto);
}
