package retoPragma.MicroTrazabilidad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroTrazabilidadApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroTrazabilidadApplication.class, args);
	}

}
