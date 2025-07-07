package retoPragma.MicroTrazabilidad.infrastructure.output.entity;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor

public class TraceabilityTimestampEntity {
    private final int year;
    private final int month;
    private final int day;
    private final int hour;
    private final int minute;
    private final int second;
}
