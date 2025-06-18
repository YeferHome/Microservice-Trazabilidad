package retoPragma.MicroTrazabilidad.domain.model;

import java.time.LocalDateTime;

public class OrderTraceability {

    private String id;
    private Long orderId;
    private Long clientId;
    private String previousStatus;
    private String newStatus;
    private LocalDateTime timestamp;

    public OrderTraceability() {}

    public OrderTraceability(String id, Long orderId, Long clientId, String previousStatus, LocalDateTime timestamp, String newStatus) {
        this.id = id;
        this.orderId = orderId;
        this.clientId = clientId;
        this.previousStatus = previousStatus;
        this.timestamp = timestamp;
        this.newStatus = newStatus;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public String getPreviousStatus() { return previousStatus; }
    public void setPreviousStatus(String previousStatus) { this.previousStatus = previousStatus; }

    public String getNewStatus() { return newStatus; }
    public void setNewStatus(String newStatus) { this.newStatus = newStatus; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
