package retoPragma.MicroTrazabilidad.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import retoPragma.MicroTrazabilidad.application.dto.*;
import retoPragma.MicroTrazabilidad.domain.model.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEfficiencyResponseMapper {

    EfficiencySummaryResponseDto toResponse(EfficiencySummaryModel model);
    EmployeeRankingResponseDto toDto(EmployeeRankingModel model);
    OrderEfficiencyResponseDto toDto(OrderEfficiencyModel model);
}
