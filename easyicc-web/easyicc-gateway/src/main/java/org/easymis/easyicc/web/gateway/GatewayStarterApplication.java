package org.easymis.easyicc.web.gateway;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;

@SpringBootApplication
@EnableDiscoveryClient
//启用feign REST访问
@EnableFeignClients(basePackages = "org.easymis.easyicc.auth.client")
@EnableCircuitBreaker
@EnableMethodCache(basePackages = "org.easymis.easyicc")
@EnableCreateCacheAnnotation
public class GatewayStarterApplication {
	//protected static final Logger logger = LoggerFactory.getLogger(GatewayStarterApplication.class);
	public static void main(String[] args) {
		//logger.info("web开始加载");
		SpringApplication.run(GatewayStarterApplication.class, args);
		//logger.info("web加载完毕");
	}

}
