package retoPragma.MicroTrazabilidad.infrastructure.input.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retoPragma.MicroTrazabilidad.application.dto.OrderTraceabilityResponseDto;
import retoPragma.MicroTrazabilidad.application.handler.IOrderTraceabilityHandler;
import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;

@RestController
@RequestMapping("/traceability")
@RequiredArgsConstructor
public class OrderTraceabilityController {

    private final IOrderTraceabilityHandler orderTraceabilityHandler;

    @GetMapping("/{orderId}")
    public ResponseEntity<CollectionModel<OrderTraceabilityResponseDto>> getTraceabilityByOrderId(
            @PathVariable Long orderId,
            @RequestHeader("client-id") Long clientId
    ) {
        CollectionModel<OrderTraceabilityResponseDto> traceabilityList =
                orderTraceabilityHandler.getOrderTraceabilityByClient(orderId, clientId);
        return ResponseEntity.ok(traceabilityList);
    }
}