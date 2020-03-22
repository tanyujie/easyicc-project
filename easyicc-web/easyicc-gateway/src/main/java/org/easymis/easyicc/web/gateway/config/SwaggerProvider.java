package org.easymis.easyicc.web.gateway.config;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@Component
@Primary
@AllArgsConstructor
@Slf4j
public class SwaggerProvider implements SwaggerResourcesProvider {
	@Override
	public List<SwaggerResource> get() {
		// TODO Auto-generated method stub
		return null;
	}
}