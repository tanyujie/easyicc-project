package org.easymis.easyicc.web.gateway;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;

@SpringBootApplication
@EnableDiscoveryClient
//启用feign REST访问
/*@EnableFeignClients(basePackages = "com.springboot.cloud.auth.client")*/
/*@EnableCircuitBreaker*/
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
