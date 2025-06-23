package retoPragma.MicroTrazabilidad.domain.usecase;

import retoPragma.MicroTrazabilidad.domain.api.IOrderTraceabilityEfficiencyServicePort;
import retoPragma.MicroTrazabilidad.domain.model.*;
import retoPragma.MicroTrazabilidad.domain.spi.IOrderTraceabilityPersistencePort;
import retoPragma.MicroTrazabilidad.domain.util.OrderEfficiencyUtil;

public class OrderEfficiencyUseCase implements IOrderTraceabilityEfficiencyServicePort {

    private final IOrderTraceabilityPersistencePort persistencePort;

    public OrderEfficiencyUseCase(IOrderTraceabilityPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public EfficiencySummaryModel getOrderEfficiencySummary(Long restaurantId) {
        CollectionModel<OrderTraceability> all = persistencePort.findDeliveredOrdersByRestaurant(restaurantId);

        CollectionModel<CollectionModel<OrderTraceability>> groupedByOrder = OrderEfficiencyUtil.groupByOrderId(all);

        CollectionModel<OrderEfficiencyModel> efficiencies = OrderEfficiencyUtil.calculateEfficiencies(groupedByOrder);

        CollectionModel<EmployeeRankingModel> rankings = OrderEfficiencyUtil.calculateRanking(efficiencies);

        double globalAverage = OrderEfficiencyUtil.calculateGlobalAverage(efficiencies);

        return new EfficiencySummaryModel(globalAverage, rankings, efficiencies);
    }
}
