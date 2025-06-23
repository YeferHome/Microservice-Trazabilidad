package retoPragma.MicroTrazabilidad.domain.model;

public class EfficiencySummaryModel {
    private double globalAverageTime;
    private CollectionModel<EmployeeRankingModel> employeeRankings;
    private CollectionModel<OrderEfficiencyModel> orders;

    public EfficiencySummaryModel(double globalAverageTime, CollectionModel<EmployeeRankingModel> employeeRankings, CollectionModel<OrderEfficiencyModel> orders) {
        this.globalAverageTime = globalAverageTime;
        this.employeeRankings = employeeRankings;
        this.orders = orders;
    }

    public double getGlobalAverageTime() { return globalAverageTime; }
    public CollectionModel<EmployeeRankingModel> getEmployeeRankings() { return employeeRankings; }
    public CollectionModel<OrderEfficiencyModel> getOrders() { return orders; }
}