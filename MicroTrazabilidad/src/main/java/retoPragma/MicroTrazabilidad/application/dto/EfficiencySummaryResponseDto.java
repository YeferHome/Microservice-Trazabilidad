package retoPragma.MicroTrazabilidad.application.dto;

import java.util.List;

public class EfficiencySummaryResponseDto {
    private double globalAverageTime;
    private List<EmployeeRankingResponseDto> employeeRankings;
    private List<OrderEfficiencyResponseDto> orders;

    public double getGlobalAverageTime() {
        return globalAverageTime;
    }

    public void setGlobalAverageTime(double globalAverageTime) {
        this.globalAverageTime = globalAverageTime;
    }

    public List<EmployeeRankingResponseDto> getEmployeeRankings() {
        return employeeRankings;
    }

    public void setEmployeeRankings(List<EmployeeRankingResponseDto> employeeRankings) {
        this.employeeRankings = employeeRankings;
    }

    public List<OrderEfficiencyResponseDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEfficiencyResponseDto> orders) {
        this.orders = orders;
    }
}
