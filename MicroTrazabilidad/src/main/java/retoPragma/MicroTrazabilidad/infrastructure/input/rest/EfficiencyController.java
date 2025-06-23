package retoPragma.MicroTrazabilidad.infrastructure.input.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retoPragma.MicroTrazabilidad.application.dto.EfficiencySummaryResponseDto;
import retoPragma.MicroTrazabilidad.application.mapper.IEfficiencyResponseMapper;
import retoPragma.MicroTrazabilidad.domain.api.IOrderTraceabilityEfficiencyServicePort;
import retoPragma.MicroTrazabilidad.domain.model.EfficiencySummaryModel;

@RestController
@RequestMapping("/efficiency")
@RequiredArgsConstructor
public class EfficiencyController {

    private final IOrderTraceabilityEfficiencyServicePort efficiencyServicePort;
    private final IEfficiencyResponseMapper responseMapper;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<EfficiencySummaryResponseDto> getEfficiencySummary(@PathVariable Long restaurantId) {
        EfficiencySummaryModel model = efficiencyServicePort.getOrderEfficiencySummary(restaurantId);
        return ResponseEntity.ok(responseMapper.toResponse(model));
    }
}
