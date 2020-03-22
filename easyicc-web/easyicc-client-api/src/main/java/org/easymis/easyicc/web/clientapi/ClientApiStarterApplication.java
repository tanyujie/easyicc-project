package org.easymis.easyicc.web.clientapi;




import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableDiscoveryClient
@ComponentScan({"*.easymis.easyicc.*"})
@EnableTransactionManagement(order = 2) // 设置事务执行顺序(需要在切换数据源之后，否则只走主库)
@MapperScan({"*.easymis.easyicc.**.mapper"})
@ServletComponentScan
@EnableSwagger2
public class ClientApiStarterApplication {
	protected static final Logger logger = LoggerFactory.getLogger(ClientApiStarterApplication.class);

	public static void main(String[] args) {
		logger.info("web开始加载");
		SpringApplication.run(ClientApiStarterApplication.class, args);
		logger.info("web加载完毕");
	}

}
