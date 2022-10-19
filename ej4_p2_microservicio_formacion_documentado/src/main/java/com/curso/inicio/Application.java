package com.curso.inicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = {"com.curso.controller", "com.curso.service","com.curso.inicio"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}

	/*
	 * Si el servicio consumido estuviese securizado:
	 * @Bean
		public RestTemplate template() {
			BasicAuthenticationInterceptor interceptor = new BasicAuthenticationInterceptor("user3", "user3");
			RestTemplate template = new RestTemplate();
			template.getInterceptors().add(interceptor);
			return template;
		}
	 */
}
