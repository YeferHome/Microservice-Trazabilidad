package retoPragma.MicroTrazabilidad.application.mapper;

import org.mapstruct.Mapper;
import retoPragma.MicroTrazabilidad.application.dto.TraceabilityTimestampDto;
import retoPragma.MicroTrazabilidad.domain.model.TraceabilityTimestamp;

@Mapper(componentModel = "spring")
public interface TimestampDtoMapper {

    default TraceabilityTimestamp toModel(TraceabilityTimestampDto dto) {
        if (dto == null) return null;
        return new TraceabilityTimestamp(
                dto.getYear(),
                dto.getMonth(),
                dto.getDay(),
                dto.getHour(),
                dto.getMinute(),
                dto.getSecond()
        );
    }
}
