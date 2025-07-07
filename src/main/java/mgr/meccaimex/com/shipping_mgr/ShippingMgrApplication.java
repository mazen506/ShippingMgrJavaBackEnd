package mgr.meccaimex.com.shipping_mgr;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ShippingMgrApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShippingMgrApplication.class, args);
	}

	@RequestMapping("/SayHello")
	public String SayHello(){
		return "index.html";
	}

}
