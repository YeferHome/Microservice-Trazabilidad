package retoPragma.MicroTrazabilidad.domain.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retoPragma.MicroTrazabilidad.domain.model.*;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderTraceabilityPersistencePort;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


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
    void getOrderEfficiencySummary_shouldReturnCorrectSummary() {
        Long restaurantId = 1L;

        TraceabilityTimestamp start = new TraceabilityTimestamp(2023, 1, 1, 10, 0, 0);
        TraceabilityTimestamp end = new TraceabilityTimestamp(2023, 1, 1, 10, 5, 0);

        OrderTraceability trace1 = new OrderTraceability("1", 100L, 10L, 20L, "PENDIENTE", start, "EN_PREPARACION", restaurantId);
        OrderTraceability trace2 = new OrderTraceability("2", 100L, 10L, 20L, "EN_PREPARACION", end, "ENTREGADO", restaurantId);

        CollectionModel<OrderTraceability> traces = new CollectionModel<>(Arrays.asList(trace1, trace2));

        when(persistencePort.findDeliveredOrdersByRestaurant(restaurantId)).thenReturn(traces);

        EfficiencySummaryModel summary = useCase.getOrderEfficiencySummary(restaurantId);

        assertEquals(1, summary.getOrders().getItems().size());
        assertEquals(1, summary.getEmployeeRankings().getItems().size());
        assertEquals(300.0, summary.getGlobalAverageTime(), 0.001);

        OrderEfficiencyModel efficiency = summary.getOrders().getItems().get(0);
        assertEquals(100L, efficiency.getOrderId());
        assertEquals(20L, efficiency.getEmployeeId());
        assertEquals(300L, efficiency.getSecondsToComplete());

        EmployeeRankingModel ranking = summary.getEmployeeRankings().getItems().get(0);
        assertEquals(20L, ranking.getEmployeeId());
        assertEquals(300.0, ranking.getAverageTime(), 0.001);
    }
}
