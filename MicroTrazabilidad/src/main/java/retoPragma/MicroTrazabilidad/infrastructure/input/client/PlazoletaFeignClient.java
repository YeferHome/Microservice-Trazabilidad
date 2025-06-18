package retoPragma.MicroTrazabilidad.infrastructure.input.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import retoPragma.MicroTrazabilidad.infrastructure.configuration.feing.FeignClientConfig;

@FeignClient(name = "ms-plazoleta", url = "http://localhost:8083/restaurantApp", configuration = FeignClientConfig.class)
public interface PlazoletaFeignClient {

    @GetMapping("/order/exists/{orderId}")
    Boolean existsById(@PathVariable("orderId") Long orderId);
}
