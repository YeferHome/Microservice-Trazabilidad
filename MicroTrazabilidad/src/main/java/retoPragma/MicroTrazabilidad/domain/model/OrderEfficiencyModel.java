package retoPragma.MicroTrazabilidad.domain.model;

public class OrderEfficiencyModel {
    private Long orderId;
    private Long employeeId;
    private long secondsToComplete;

    public OrderEfficiencyModel(Long orderId, Long employeeId, long secondsToComplete) {
        this.orderId = orderId;
        this.employeeId = employeeId;
        this.secondsToComplete = secondsToComplete;
    }

    public Long getOrderId() { return orderId; }
    public Long getEmployeeId() { return employeeId; }
    public long getSecondsToComplete() { return secondsToComplete; }
}

