package org.easymis.easyicc.web.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	 public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(new BasePathInterceptor()).addPathPatterns("/**");
	    }
}
