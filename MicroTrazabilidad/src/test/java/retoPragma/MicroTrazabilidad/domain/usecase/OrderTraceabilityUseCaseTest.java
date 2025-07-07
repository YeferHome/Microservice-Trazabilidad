package retoPragma.MicroTrazabilidad.domain.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retoPragma.MicroTrazabilidad.domain.exception.OrderNotFoundException;
import retoPragma.MicroTrazabilidad.domain.exception.UnauthorizedAccessException;
import retoPragma.MicroTrazabilidad.domain.model.*;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderTraceabilityPersistencePort;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderValidationPort;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderTraceabilityUseCaseTest {

    private IOrderTraceabilityPersistencePort persistencePort;
    private IOrderValidationPort validationPort;
    private OrderTraceabilityUseCase useCase;

    @BeforeEach
    void setUp() {
        persistencePort = mock(IOrderTraceabilityPersistencePort.class);
        validationPort = mock(IOrderValidationPort.class);
        useCase = new OrderTraceabilityUseCase(persistencePort, validationPort);
    }

    @Test
    void shouldReturnTraceabilityWhenOrderExistsAndClientMatches() {
        Long orderId = 1L;
        Long clientId = 10L;

        OrderTraceability trace = new OrderTraceability(
                "abc", orderId, clientId, 20L,
                "PENDIENTE", new TraceabilityTimestamp(2023, 1, 1, 10, 0, 0),
                "EN_PREPARACION", 99L
        );

        CollectionModel<OrderTraceability> traceList = new CollectionModel<>(Collections.singletonList(trace));
        OrderClientTraceability clientTrace = new OrderClientTraceability(clientId, traceList);

        when(validationPort.existsOrderById(orderId)).thenReturn(true);
        when(persistencePort.findClientTraceabilityByOrderId(orderId)).thenReturn(clientTrace);

        CollectionModel<OrderTraceability> result = useCase.getTraceabilityByOrderId(orderId, clientId);

        assertEquals(1, result.getItems().size());
        assertEquals(trace.getOrderId(), result.getItems().get(0).getOrderId());
    }

    @Test
    void shouldThrowOrderNotFoundExceptionIfOrderDoesNotExist() {
        Long orderId = 2L;
        Long clientId = 10L;

        when(validationPort.existsOrderById(orderId)).thenReturn(false);

        assertThrows(OrderNotFoundException.class, () -> {
            useCase.getTraceabilityByOrderId(orderId, clientId);
        });

        verify(persistencePort, never()).findClientTraceabilityByOrderId(anyLong());
    }

    @Test
    void shouldThrowUnauthorizedAccessExceptionIfClientDoesNotMatch() {
        Long orderId = 3L;
        Long clientId = 10L;
        Long otherClientId = 99L;

        CollectionModel<OrderTraceability> dummyList = new CollectionModel<>();
        OrderClientTraceability otherClientTrace = new OrderClientTraceability(otherClientId, dummyList);

        when(validationPort.existsOrderById(orderId)).thenReturn(true);
        when(persistencePort.findClientTraceabilityByOrderId(orderId)).thenReturn(otherClientTrace);

        assertThrows(UnauthorizedAccessException.class, () -> {
            useCase.getTraceabilityByOrderId(orderId, clientId);
        });
    }

    @Test
    void shouldThrowUnauthorizedAccessExceptionIfTraceabilityIsNull() {
        Long orderId = 4L;
        Long clientId = 10L;

        when(validationPort.existsOrderById(orderId)).thenReturn(true);
        when(persistencePort.findClientTraceabilityByOrderId(orderId)).thenReturn(null);

        assertThrows(UnauthorizedAccessException.class, () -> {
            useCase.getTraceabilityByOrderId(orderId, clientId);
        });
    }
}
