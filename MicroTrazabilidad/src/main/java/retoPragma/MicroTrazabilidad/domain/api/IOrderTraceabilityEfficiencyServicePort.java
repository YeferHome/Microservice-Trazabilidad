package retoPragma.MicroTrazabilidad.domain.api;

import retoPragma.MicroTrazabilidad.domain.model.EfficiencySummaryModel;

public interface IOrderTraceabilityEfficiencyServicePort {
    EfficiencySummaryModel getOrderEfficiencySummary(Long restaurantId);
}

