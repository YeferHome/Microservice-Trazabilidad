package retoPragma.MicroTrazabilidad.domain.model;

public class OrderClientTraceability {

    private final Long clientId;
    private final CollectionModel<OrderTraceability> traceability;

    public OrderClientTraceability(Long clientId, CollectionModel<OrderTraceability> traceability) {
        this.clientId = clientId;
        this.traceability = traceability;
    }

    public Long getClientId() {
        return clientId;
    }

    public CollectionModel<OrderTraceability> getTraceability() {
        return traceability;
    }
}
