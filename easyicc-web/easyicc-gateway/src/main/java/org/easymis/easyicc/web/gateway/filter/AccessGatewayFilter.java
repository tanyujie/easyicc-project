package org.easymis.easyicc.web.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 请求url权限校验
 */
@Configuration
@ComponentScan(basePackages = "org.easymis.easyicc.auth.client")
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// 请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 响应对象
        ServerHttpResponse response = exchange.getResponse();
        
     // 获取请求地址
        String beforePath = request.getPath().pathWithinApplication().value();
        System.out.print("beforePath");
		return null;
	}
}
