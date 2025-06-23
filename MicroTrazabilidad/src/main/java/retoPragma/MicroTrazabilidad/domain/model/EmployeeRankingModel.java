package retoPragma.MicroTrazabilidad.domain.model;

public class EmployeeRankingModel {
    private Long employeeId;
    private double totalTime;
    private int count;

    public EmployeeRankingModel(Long employeeId, double initialTime) {
        this.employeeId = employeeId;
        this.totalTime = initialTime;
        this.count = initialTime > 0 ? 1 : 0;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public double getAverageTime() {
        return count == 0 ? 0 : totalTime / count;
    }

    public void addTime(long seconds) {
        this.totalTime += seconds;
        this.count++;
    }
}
