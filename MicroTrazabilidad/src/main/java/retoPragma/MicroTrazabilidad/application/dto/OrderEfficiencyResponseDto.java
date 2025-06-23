package retoPragma.MicroTrazabilidad.application.dto;

public class OrderEfficiencyResponseDto {
    private Long orderId;
    private Long employeeId;
    private long secondsToComplete;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public long getSecondsToComplete() {
        return secondsToComplete;
    }

    public void setSecondsToComplete(long secondsToComplete) {
        this.secondsToComplete = secondsToComplete;
    }
}
