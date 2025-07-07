package retoPragma.MicroTrazabilidad.infrastructure.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retoPragma.MicroTrazabilidad.domain.api.IOrderTraceabilityServicePort;
import retoPragma.MicroTrazabilidad.domain.api.IOrderTraceabilityEfficiencyServicePort;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderTraceabilityPersistencePort;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderValidationPort;
import retoPragma.MicroTrazabilidad.domain.usecase.OrderTraceabilityUseCase;
import retoPragma.MicroTrazabilidad.domain.usecase.OrderEfficiencyUseCase;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IOrderTraceabilityPersistencePort orderTraceabilityPersistencePort;
    private final IOrderValidationPort orderValidationPort;

    @Bean
    public IOrderTraceabilityServicePort orderTraceabilityServicePort() {
        return new OrderTraceabilityUseCase(orderTraceabilityPersistencePort, orderValidationPort);
    }

    @Bean
    public IOrderTraceabilityEfficiencyServicePort orderTraceabilityEfficiencyServicePort() {
        return new OrderEfficiencyUseCase(orderTraceabilityPersistencePort);
    }
}
