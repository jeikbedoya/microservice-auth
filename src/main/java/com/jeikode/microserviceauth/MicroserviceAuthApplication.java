package com.jeikode.microserviceauth;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.jeikode.microserviceauth.services.JwtService;

@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@ComponentScan({"com.jeikode.microserviceauth.dao",
	"com.jeikode.microserviceauth.model","com.jeikode.microserviceauth.services"}) 
public class MicroserviceAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceAuthApplication.class, args);
	}
	
	
	@Bean
	@ConfigurationProperties(prefix="datasource.db-test")
	public DataSource personDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	public JwtService JwtService() {
		return new JwtService();
	}
}
