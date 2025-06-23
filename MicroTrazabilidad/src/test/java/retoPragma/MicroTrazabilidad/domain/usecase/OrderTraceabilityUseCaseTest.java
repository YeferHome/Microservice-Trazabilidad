package retoPragma.MicroTrazabilidad.domain.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retoPragma.MicroTrazabilidad.domain.exception.OrderNotFoundException;
import retoPragma.MicroTrazabilidad.domain.exception.UnauthorizedAccessException;
import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;
import retoPragma.MicroTrazabilidad.domain.model.OrderClientTraceability;
import retoPragma.MicroTrazabilidad.domain.model.OrderTraceability;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderTraceabilityPersistencePort;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderValidationPort;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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
    void shouldThrowOrderNotFoundException_whenOrderDoesNotExist() {
        Long orderId = 1L;
        Long clientId = 10L;

        when(validationPort.existsOrderById(orderId)).thenReturn(false);

        assertThrows(OrderNotFoundException.class, () -> {
            useCase.getTraceabilityByOrderId(orderId, clientId);
        });

        verify(validationPort).existsOrderById(orderId);
        verifyNoInteractions(persistencePort);
    }

    @Test
    void shouldThrowUnauthorizedAccessException_whenClientDoesNotOwnOrder() {
        Long orderId = 1L;
        Long clientId = 10L;
        Long otherClientId = 99L;

        when(validationPort.existsOrderById(orderId)).thenReturn(true);
        when(persistencePort.findClientTraceabilityByOrderId(orderId))
                .thenReturn(new OrderClientTraceability(otherClientId, new CollectionModel<>(Collections.emptyList())));

        assertThrows(UnauthorizedAccessException.class, () -> {
            useCase.getTraceabilityByOrderId(orderId, clientId);
        });

        verify(validationPort).existsOrderById(orderId);
        verify(persistencePort).findClientTraceabilityByOrderId(orderId);
    }

    @Test
    void shouldReturnTraceability_whenOrderExistsAndBelongsToClient() {
        Long orderId = 1L;
        Long clientId = 10L;

        OrderTraceability trace = new OrderTraceability("abc", orderId, clientId, "PENDIENTE", LocalDateTime.now(), "PREPARANDO");
        CollectionModel<OrderTraceability> collection = new CollectionModel<>(List.of(trace));
        OrderClientTraceability orderClientTraceability = new OrderClientTraceability(clientId, collection);

        when(validationPort.existsOrderById(orderId)).thenReturn(true);
        when(persistencePort.findClientTraceabilityByOrderId(orderId)).thenReturn(orderClientTraceability);

        CollectionModel<OrderTraceability> result = useCase.getTraceabilityByOrderId(orderId, clientId);

        assertNotNull(result);
        assertEquals(1, result.getItems().size());
        assertEquals("PREPARANDO", result.getItems().get(0).getNewStatus());

        verify(validationPort).existsOrderById(orderId);
        verify(persistencePort).findClientTraceabilityByOrderId(orderId);
    }
}
