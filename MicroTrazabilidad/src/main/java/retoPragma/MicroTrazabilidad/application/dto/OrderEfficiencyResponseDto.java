package retoPragma.MicroTrazabilidad.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderEfficiencyResponseDto {
    private Long orderId;
    private Long employeeId;
    private long secondsToComplete;
}
