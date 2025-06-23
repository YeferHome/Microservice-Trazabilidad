package retoPragma.MicroTrazabilidad.domain.util;

import retoPragma.MicroTrazabilidad.domain.model.*;

public class OrderEfficiencyUtil {

    public static CollectionModel<CollectionModel<OrderTraceability>> groupByOrderId(CollectionModel<OrderTraceability> all) {
        CollectionModel<CollectionModel<OrderTraceability>> result = new CollectionModel<>();

        for (OrderTraceability traceability : all.getItems()) {
            Long orderId = traceability.getOrderId();

            CollectionModel<OrderTraceability> group = null;
            for (CollectionModel<OrderTraceability> existingGroup : result.getItems()) {
                if (!existingGroup.getItems().isEmpty() &&
                        existingGroup.getItems().get(0).getOrderId().equals(orderId)) {
                    group = existingGroup;
                    break;
                }
            }

            if (group == null) {
                group = new CollectionModel<>();
                result.getItems().add(group);
            }

            group.getItems().add(traceability);
        }

        return result;
    }

    public static CollectionModel<OrderEfficiencyModel> calculateEfficiencies(CollectionModel<CollectionModel<OrderTraceability>> grouped) {
        CollectionModel<OrderEfficiencyModel> efficiencies = new CollectionModel<>();

        for (CollectionModel<OrderTraceability> group : grouped.getItems()) {
            OrderTraceability start = null;
            OrderTraceability end = null;

            for (OrderTraceability trace : group.getItems()) {
                String prev = trace.getPreviousStatus();
                String curr = trace.getNewStatus();

                if ("EN_PREPARACION".equals(prev) || "EN_PREPARACION".equals(curr)) {
                    start = trace;
                }
                if ("ENTREGADO".equals(curr)) {
                    end = trace;
                }
            }

            if (start == null || end == null) continue;

            long seconds = TraceabilityTimestamp.secondsBetween(start.getTimestamp(), end.getTimestamp());
            OrderEfficiencyModel model = new OrderEfficiencyModel(end.getOrderId(), end.getEmployeeId(), seconds);
            efficiencies.getItems().add(model);
        }

        return efficiencies;
    }

    public static CollectionModel<EmployeeRankingModel> calculateRanking(CollectionModel<OrderEfficiencyModel> efficiencies) {
        CollectionModel<EmployeeRankingModel> ranking = new CollectionModel<>();

        for (OrderEfficiencyModel efficiency : efficiencies.getItems()) {
            Long employeeId = efficiency.getEmployeeId();

            EmployeeRankingModel existing = null;
            for (EmployeeRankingModel model : ranking.getItems()) {
                if (model.getEmployeeId().equals(employeeId)) {
                    existing = model;
                    break;
                }
            }

            if (existing == null) {
                existing = new EmployeeRankingModel(employeeId, 0);
                ranking.getItems().add(existing);
            }

            existing.addTime(efficiency.getSecondsToComplete());
        }

        // Ordenar por tiempo promedio ascendente (bubble sort por compatibilidad)
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

        return ranking;
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
