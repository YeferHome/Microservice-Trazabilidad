package retoPragma.MicroTrazabilidad.infrastructure.output.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderValidationPort;
import retoPragma.MicroTrazabilidad.infrastructure.input.client.PlazoletaFeignClient;

@Component
@RequiredArgsConstructor
public class OrderValidationAdapter implements IOrderValidationPort {

    private final PlazoletaFeignClient orderFeignClient;

    @Override
    public boolean existsOrderById(Long orderId) {
        return orderFeignClient.existsById(orderId);
    }
}
