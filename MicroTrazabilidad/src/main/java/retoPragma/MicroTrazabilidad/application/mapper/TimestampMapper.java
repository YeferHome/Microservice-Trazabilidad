package retoPragma.MicroTrazabilidad.application.mapper;

import org.mapstruct.Named;
import retoPragma.MicroTrazabilidad.domain.model.TraceabilityTimestamp;

import java.time.LocalDateTime;

public class TimestampMapper {

    @Named("toLocalDateTime")
    public static LocalDateTime toLocalDateTime(TraceabilityTimestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }

    @Named("toTimestamp")
    public static TraceabilityTimestamp toTimestamp(TraceabilityTimestamp timestamp) {
        return timestamp;
    }
}
