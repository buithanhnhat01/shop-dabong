package edu.poly.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.poly.shop.interceptor.AdminAuthenticationIntercreptor;
import edu.poly.shop.interceptor.SiteAuthenticationIntercreptor;

@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer{
	@Autowired
	private AdminAuthenticationIntercreptor adminAuthenticationIntercreptor;
	
	//@Autowired
	//private SiteAuthenticationIntercreptor siteAuthenticationIntercreptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminAuthenticationIntercreptor)
		.addPathPatterns("/admin/**");
	
		
		//registry.addInterceptor(siteAuthenticationIntercreptor)
		//.addPathPatterns("/site/**");
	}
	 
}
