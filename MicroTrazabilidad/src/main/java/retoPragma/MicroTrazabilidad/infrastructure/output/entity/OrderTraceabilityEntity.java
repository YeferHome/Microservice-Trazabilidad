package retoPragma.MicroTrazabilidad.infrastructure.output.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "order_traceabilities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderTraceabilityEntity {

    @Id
    private String id;
    private Long orderId;
    private Long clientId;
    private String previousStatus;
    private String newStatus;
    private LocalDateTime timestamp;
}
