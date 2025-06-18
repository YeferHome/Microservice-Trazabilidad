package retoPragma.MicroTrazabilidad.infrastructure.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retoPragma.MicroTrazabilidad.domain.api.IOrderTraceabilityServicePort;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderValidationPort;
import retoPragma.MicroTrazabilidad.domain.usecase.OrderTraceabilityUseCase;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final retoPragma.MicroTrazabilidad.domain.spi.IOrderTraceabilityPersistencePort orderTraceabilityPersistencePort;
    private final IOrderValidationPort orderValidationPort;

    @Bean
    public IOrderTraceabilityServicePort orderTraceabilityServicePort() {
        return new OrderTraceabilityUseCase(orderTraceabilityPersistencePort, orderValidationPort);
    }
}
