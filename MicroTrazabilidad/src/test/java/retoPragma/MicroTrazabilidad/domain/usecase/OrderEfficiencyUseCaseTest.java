package retoPragma.MicroTrazabilidad.domain.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retoPragma.MicroTrazabilidad.domain.model.*;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderTraceabilityPersistencePort;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OrderEfficiencyUseCaseTest {

    private IOrderTraceabilityPersistencePort persistencePort;
    private OrderEfficiencyUseCase useCase;

    @BeforeEach
    void setUp() {
        persistencePort = mock(IOrderTraceabilityPersistencePort.class);
        useCase = new OrderEfficiencyUseCase(persistencePort);
    }

    @Test
    void getOrderEfficiencySummary_shouldReturnSummary() {
        TraceabilityTimestamp startTime = new TraceabilityTimestamp(2024, 1, 1, 12, 0, 0);
        TraceabilityTimestamp endTime = new TraceabilityTimestamp(2024, 1, 1, 12, 10, 0);

        OrderTraceability startTrace = new OrderTraceability("1", 101L, 201L, 301L, "PENDIENTE", startTime, "EN_PREPARACION");
        OrderTraceability endTrace = new OrderTraceability("2", 101L, 201L, 301L, "EN_PREPARACION", endTime, "ENTREGADO");

        CollectionModel<OrderTraceability> traceList = new CollectionModel<>(Arrays.asList(startTrace, endTrace));
        when(persistencePort.findDeliveredOrdersByRestaurant(1L)).thenReturn(traceList);

        EfficiencySummaryModel result = useCase.getOrderEfficiencySummary(1L);

        assertEquals(1, result.getOrders().getItems().size()); 
        assertEquals(1, result.getEmployeeRankings().getItems().size());
        assertEquals(600, result.getGlobalAverageTime());
    }
}
