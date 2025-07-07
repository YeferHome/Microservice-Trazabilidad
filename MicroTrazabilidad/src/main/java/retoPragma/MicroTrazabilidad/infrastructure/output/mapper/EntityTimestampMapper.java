package retoPragma.MicroTrazabilidad.infrastructure.output.mapper;

import org.springframework.stereotype.Component;
import retoPragma.MicroTrazabilidad.domain.model.TraceabilityTimestamp;
import retoPragma.MicroTrazabilidad.infrastructure.output.entity.TraceabilityTimestampEntity;

@Component
public class EntityTimestampMapper {

    public TraceabilityTimestamp toModel(TraceabilityTimestampEntity entity) {
        if (entity == null) return null;
        return new TraceabilityTimestamp(
                entity.getYear(),
                entity.getMonth(),
                entity.getDay(),
                entity.getHour(),
                entity.getMinute(),
                entity.getSecond()
        );
    }
}
