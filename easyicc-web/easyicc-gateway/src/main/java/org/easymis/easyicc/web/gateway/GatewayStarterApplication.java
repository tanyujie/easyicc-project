package org.easymis.easyicc.web.gateway;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
/*@EnableDiscoveryClient*/
@ComponentScan({"*.easymis.easysaas.*"})
public class GatewayStarterApplication {
	protected static final Logger logger = LoggerFactory.getLogger(GatewayStarterApplication.class);
	public static void main(String[] args) {
		logger.info("web开始加载");
		SpringApplication.run(GatewayStarterApplication.class, args);
		logger.info("web加载完毕");
	}

}
