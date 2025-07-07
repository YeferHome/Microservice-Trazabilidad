package retoPragma.MicroTrazabilidad.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class OrderTraceabilityRequestDto {
    private Long orderId;
    private Long clientId;
    private String previousStatus;
    private String newStatus;
    private TraceabilityTimestampDto timestamp;
}
