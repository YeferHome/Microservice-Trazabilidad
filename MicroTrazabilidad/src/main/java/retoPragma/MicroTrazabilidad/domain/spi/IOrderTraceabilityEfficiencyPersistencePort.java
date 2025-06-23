package retoPragma.MicroTrazabilidad.domain.spi;

import retoPragma.MicroTrazabilidad.domain.model.CollectionModel;
import retoPragma.MicroTrazabilidad.domain.model.EmployeeRankingModel;
import retoPragma.MicroTrazabilidad.domain.model.OrderEfficiencyModel;

public interface IOrderTraceabilityEfficiencyPersistencePort {
    CollectionModel<OrderEfficiencyModel> getOrderEfficiencyByRestaurant(Long restaurantId);
    CollectionModel<EmployeeRankingModel> getEmployeeRankingByRestaurant(Long restaurantId);
}
