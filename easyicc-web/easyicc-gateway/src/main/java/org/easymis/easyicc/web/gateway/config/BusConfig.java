package org.easymis.easyicc.web.gateway.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
@Configuration
@Slf4j
public class BusConfig {

    private static final String EXCHANGE_NAME = "spring-boot-exchange";
    private static final String ROUTING_KEY = "gateway-route";

    @Value("${spring.application.name}")
    private String appName;

}
