package retoPragma.MicroTrazabilidad.domain.model;

public class OrderTraceability {

    private String id;
    private Long orderId;
    private Long clientId;
    private Long employeeId;
    private String previousStatus;
    private String newStatus;
    private Long restaurantId;
    private TraceabilityTimestamp timestamp;

    public OrderTraceability() {}

    public OrderTraceability(
            String id,
            Long orderId,
            Long clientId,
            Long employeeId,
            String previousStatus,
            TraceabilityTimestamp timestamp,
            String newStatus,
            Long restaurantId
    ) {
        this.id = id;
        this.orderId = orderId;
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.previousStatus = previousStatus;
        this.timestamp = timestamp;
        this.newStatus = newStatus;
        this.restaurantId = restaurantId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public String getPreviousStatus() { return previousStatus; }
    public void setPreviousStatus(String previousStatus) { this.previousStatus = previousStatus; }

    public String getNewStatus() { return newStatus; }
    public void setNewStatus(String newStatus) { this.newStatus = newStatus; }

    public TraceabilityTimestamp getTimestamp() { return timestamp; }
    public void setTimestamp(TraceabilityTimestamp timestamp) { this.timestamp = timestamp; }

    public Long getRestaurantId() { return restaurantId; }
    public void setRestaurantId(Long restaurantId) { this.restaurantId = restaurantId; }
}
