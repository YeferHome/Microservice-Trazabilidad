package retoPragma.MicroTrazabilidad.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import retoPragma.MicroTrazabilidad.application.dto.EfficiencySummaryResponseDto;
import retoPragma.MicroTrazabilidad.application.dto.EmployeeRankingResponseDto;
import retoPragma.MicroTrazabilidad.application.dto.OrderEfficiencyResponseDto;
import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;
import retoPragma.MicroTrazabilidad.domain.model.EfficiencySummaryModel;
import retoPragma.MicroTrazabilidad.domain.model.EmployeeRankingModel;
import retoPragma.MicroTrazabilidad.domain.model.OrderEfficiencyModel;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEfficiencyResponseMapper {

    @Mapping(target = "employeeRankings", source = "employeeRankings", qualifiedByName = "mapEmployeeRankingList")
    @Mapping(target = "orders", source = "orders", qualifiedByName = "mapOrderEfficiencyList")
    EfficiencySummaryResponseDto toResponse(EfficiencySummaryModel model);

    EmployeeRankingResponseDto toDto(EmployeeRankingModel model);
    OrderEfficiencyResponseDto toDto(OrderEfficiencyModel model);

    @Named("mapEmployeeRankingList")
    default List<EmployeeRankingResponseDto> mapEmployeeRankingList(CollectionModel<EmployeeRankingModel> model) {
        return model == null ? null : model.getItems().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Named("mapOrderEfficiencyList")
    default List<OrderEfficiencyResponseDto> mapOrderEfficiencyList(CollectionModel<OrderEfficiencyModel> model) {
        return model == null ? null : model.getItems().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}