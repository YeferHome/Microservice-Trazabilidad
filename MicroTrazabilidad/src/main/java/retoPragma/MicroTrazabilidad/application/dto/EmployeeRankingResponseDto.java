package retoPragma.MicroTrazabilidad.application.dto;

public class EmployeeRankingResponseDto {
    private Long employeeId;
    private long averageTime;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public long getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(long averageTime) {
        this.averageTime = averageTime;
    }
}
