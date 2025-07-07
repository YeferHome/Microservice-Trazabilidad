package retoPragma.MicroTrazabilidad.domain.util;

import retoPragma.MicroTrazabilidad.domain.model.*;

public class OrderEfficiencyUtil {

    private OrderEfficiencyUtil() {
    }

    public static CollectionModel<CollectionModel<OrderTraceability>> groupByOrderId(CollectionModel<OrderTraceability> all) {
        CollectionModel<CollectionModel<OrderTraceability>> result = new CollectionModel<>();

        for (OrderTraceability traceability : all.getItems()) {
            Long orderId = traceability.getOrderId();

            CollectionModel<OrderTraceability> group = findExistingGroup(result, orderId);
            if (group == null) {
                group = new CollectionModel<>();
                result.getItems().add(group);
            }

            group.getItems().add(traceability);
        }

        return result;
    }

    private static CollectionModel<OrderTraceability> findExistingGroup(CollectionModel<CollectionModel<OrderTraceability>> groups, Long orderId) {
        for (CollectionModel<OrderTraceability> existingGroup : groups.getItems()) {
            if (!existingGroup.getItems().isEmpty() &&
                    existingGroup.getItems().get(0).getOrderId().equals(orderId)) {
                return existingGroup;
            }
        }
        return null;
    }

    public static CollectionModel<OrderEfficiencyModel> calculateEfficiencies(CollectionModel<CollectionModel<OrderTraceability>> grouped) {
        CollectionModel<OrderEfficiencyModel> efficiencies = new CollectionModel<>();

        for (CollectionModel<OrderTraceability> group : grouped.getItems()) {
            OrderEfficiencyModel model = calculateEfficiencyForGroup(group);
            if (model != null) {
                efficiencies.getItems().add(model);
            }
        }

        return efficiencies;
    }

    private static OrderEfficiencyModel calculateEfficiencyForGroup(CollectionModel<OrderTraceability> group) {
        OrderTraceability start = findStartTrace(group);
        OrderTraceability end = findEndTrace(group, start);

        if (start == null || end == null) return null;

        long seconds = TraceabilityTimestamp.secondsBetween(start.getTimestamp(), end.getTimestamp());
        if (seconds <= 0) return null;

        return new OrderEfficiencyModel(end.getOrderId(), end.getEmployeeId(), seconds);
    }

    private static OrderTraceability findStartTrace(CollectionModel<OrderTraceability> group) {
        for (OrderTraceability trace : group.getItems()) {
            if ("EN_PREPARACION".equals(trace.getPreviousStatus()) || "EN_PREPARACION".equals(trace.getNewStatus())) {
                return trace;
            }
        }
        return null;
    }

    private static OrderTraceability findEndTrace(CollectionModel<OrderTraceability> group, OrderTraceability start) {
        if (start == null) return null;

        for (OrderTraceability trace : group.getItems()) {
            if ("ENTREGADO".equals(trace.getNewStatus()) &&
                    trace.getTimestamp().toLocalDateTime().isAfter(start.getTimestamp().toLocalDateTime())) {
                return trace;
            }
        }

        return null;
    }

    public static CollectionModel<EmployeeRankingModel> calculateRanking(CollectionModel<OrderEfficiencyModel> efficiencies) {
        CollectionModel<EmployeeRankingModel> ranking = new CollectionModel<>();

        for (OrderEfficiencyModel efficiency : efficiencies.getItems()) {
            Long employeeId = efficiency.getEmployeeId();

            EmployeeRankingModel existing = findEmployeeRanking(ranking, employeeId);
            if (existing == null) {
                existing = new EmployeeRankingModel(employeeId, 0);
                ranking.getItems().add(existing);
            }

            existing.addTime(efficiency.getSecondsToComplete());
        }

        sortRankingByAverageTime(ranking);
        return ranking;
    }

    private static EmployeeRankingModel findEmployeeRanking(CollectionModel<EmployeeRankingModel> ranking, Long employeeId) {
        for (EmployeeRankingModel model : ranking.getItems()) {
            if (model.getEmployeeId().equals(employeeId)) {
                return model;
            }
        }
        return null;
    }

    private static void sortRankingByAverageTime(CollectionModel<EmployeeRankingModel> ranking) {
        int n = ranking.getItems().size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                EmployeeRankingModel a = ranking.getItems().get(j);
                EmployeeRankingModel b = ranking.getItems().get(j + 1);
                if (a.getAverageTime() > b.getAverageTime()) {
                    ranking.getItems().set(j, b);
                    ranking.getItems().set(j + 1, a);
                }
            }
        }
    }

    public static double calculateGlobalAverage(CollectionModel<OrderEfficiencyModel> efficiencies) {
        if (efficiencies.getItems().isEmpty()) return 0;

        long total = 0;
        for (OrderEfficiencyModel model : efficiencies.getItems()) {
            total += model.getSecondsToComplete();
        }

        return (double) total / efficiencies.getItems().size();
    }
}
